package kr.team.ticketing.core.domain.coupon;


import java.util.List;


public interface CouponRepositoryCustom {
    List<Coupon> getNotUsedCouponsByMemberId(Long memberId);
    List<Coupon> getThisMonthCoupons();
}
