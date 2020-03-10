package kr.team.ticketing.core.domain.coupon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoupon is a Querydsl query type for Coupon
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCoupon extends EntityPathBase<Coupon> {

    private static final long serialVersionUID = -1217048239L;

    public static final QCoupon coupon = new QCoupon("coupon");

    public final EnumPath<CouponStatus> couponStatus = createEnum("couponStatus", CouponStatus.class);

    public final SimplePath<kr.team.ticketing.core.domain.generic.money.Ratio> discount = createSimple("discount", kr.team.ticketing.core.domain.generic.money.Ratio.class);

    public final DatePath<java.time.LocalDate> expirationDate = createDate("expirationDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final StringPath name = createString("name");

    public QCoupon(String variable) {
        super(Coupon.class, forVariable(variable));
    }

    public QCoupon(Path<? extends Coupon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoupon(PathMetadata metadata) {
        super(Coupon.class, metadata);
    }

}

