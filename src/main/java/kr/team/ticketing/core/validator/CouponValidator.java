package kr.team.ticketing.core.validator;

import kr.team.ticketing.core.domain.coupon.Coupon;
import kr.team.ticketing.core.domain.coupon.CouponStatus;
import kr.team.ticketing.core.domain.generic.money.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CouponValidator {

    public boolean checkListSize(List<Coupon> coupons) {
        if(coupons.size()==0) throw new IllegalArgumentException("쿠폰이 없습니다.");
        return true;
    }

    public void changeCouponStatus(Coupon coupon) {
        coupon.changeCouponStatus();
    }

    public boolean checkDiscount(Long CouponId) {
        return CouponId != null;
    }

    public Long applyDiscount(Coupon coupon, int totalPrice) {
        if (checkExpirationDate(coupon) && checkUsed(coupon)) {
            return coupon.getDiscount().of(Money.wons(totalPrice)).longValue();
        }
        throw new IllegalArgumentException("사용이 불가한 쿠폰입니다.");
    }

    public boolean checkUsed(Coupon coupon) {
        if (coupon.getCouponStatus().equals(CouponStatus.BEFORE)) return true;
        else throw new IllegalArgumentException("이미 사용한 쿠폰입니다!");
    }

    public boolean checkExpirationDate(Coupon coupon) {
        if (LocalDate.now().isBefore(coupon.getExpirationDate().plusDays(1))) return true;
        else throw new IllegalArgumentException("사용기한이 만료되었습니다!");
    }
}
