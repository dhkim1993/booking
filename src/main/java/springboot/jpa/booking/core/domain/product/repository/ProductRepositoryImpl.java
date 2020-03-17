package springboot.jpa.booking.core.domain.product.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import springboot.jpa.booking.core.domain.product.*;

import javax.persistence.EntityManager;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.List;

import static springboot.jpa.booking.core.domain.product.QInfoByDate.infoByDate;
import static springboot.jpa.booking.core.domain.product.QProduct.product;


public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Product> searchByDynamicCondition (SearchCondition searchCondition) {
        Pageable pageable = makePageRequest(searchCondition);
        List<Product> content = queryFactory
                .selectFrom(product)
                .where(eqId(searchCondition.getCategoryId()),
                        eqLocation(searchCondition.getLocation()),
                        eqMonth(searchCondition.getMonth()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Product> countQuery = queryFactory
                .select(product)
                .from(product)
                .where(eqId(searchCondition.getCategoryId()),
                        eqLocation(searchCondition.getLocation()),
                        eqMonth(searchCondition.getMonth()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    @Override
    public List<InfoByDate> getInfoByDatesByProductId(Long productId) {
        return queryFactory
                .select(infoByDate)
                .from(product)
                .where(product.id.eq (productId))
                .fetch();
    }

    @Override
    public Page<Product> searchAll(Pageable pageable) {
        QueryResults<Product> results = queryFactory
                .selectFrom(product)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(product.id.desc())
                .fetchResults();

        List<Product> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content,pageable, total);
    }

    // ==============================================================//

    private Pageable makePageRequest(SearchCondition searchCondition) {
        return PageRequest.of(searchCondition.getPage(), searchCondition.getSize());
    }

    private BooleanExpression eqId(Long categoryId) {
        if (StringUtils.isEmpty(categoryId)) {
            return null;
        }
        return product.categoryId.eq(categoryId);
    }
    private BooleanExpression eqLocation(String location) {
        if (StringUtils.isEmpty(location)) {
            return null;
        }
        return product.location.eq(location);
    }
    private BooleanExpression eqMonth(int month) {
        if (month == 0) {
            return null;
        }
        return checkRun(month);
    }

    private long expirationDateCheck() {
        return Duration.between(LocalDate.now(), (Temporal)product.endDate).toDays();
    }

    private BooleanExpression checkRun(int month) {
        NumberExpression<Integer> startMonth = product.startDate.month();
        NumberExpression<Integer> endMonth = product.endDate.month();
        return startMonth.loe(month).and(endMonth.goe(month));
    }
}
