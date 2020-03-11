package springboot.jpa.booking.core.domain.coupon;


import java.util.List;


public interface CouponRepositoryCustom {
    List<Coupon> getNotUsedCouponsByMemberId(Long memberId);
    List<Coupon> getThisMonthCoupons();
}
