package kr.team.ticketing.core.domain.comment;


import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static kr.team.ticketing.core.domain.comment.QComment.comment;

public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Long searchByGradeComments(int grade, Long productId) {
        return queryFactory
                .select(comment.count())
                .from(comment)
                .where(comment.grade.eq(grade)
                        .and(comment.productId.eq(productId)))
                .fetchOne();
    }

    @Override
    public Long getTotalCntByProductId(Long productId) {
        return queryFactory
                .select(comment.count())
                .from(comment)
                .where(comment.productId.eq(productId))
                .fetchOne();
    }

    @Override
    public Double getAvg(Long productId) {
        return queryFactory
                .select(comment.grade.avg())
                .from(comment)
                .where(comment.productId.eq(productId))
                .fetchOne();
    }
}
