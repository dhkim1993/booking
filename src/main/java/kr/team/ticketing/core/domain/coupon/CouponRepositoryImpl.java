package kr.team.ticketing.core.domain.coupon;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static kr.team.ticketing.core.domain.coupon.QCoupon.coupon;


public class CouponRepositoryImpl implements CouponRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CouponRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Coupon> getNotUsedCouponsByMemberId(Long memberId) {
        return queryFactory
                .selectFrom(coupon)
                .where(coupon.couponStatus.eq(CouponStatus.BEFORE)
                .and(coupon.expirationDate.goe(LocalDate.now()))
                .and(coupon.memberId.eq(memberId)))
                .fetch();
    }

    @Override
    public List<Coupon> getThisMonthCoupons() {
        return queryFactory
                .selectFrom(coupon)
                .where(coupon.month.eq(getThisMonth()))
                .fetch();
    }

    private int getThisMonth() {
        return LocalDate.now().getMonth().getValue();
    }
}
