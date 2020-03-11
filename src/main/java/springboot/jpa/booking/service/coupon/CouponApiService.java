package springboot.jpa.booking.service.coupon;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.booking.core.domain.coupon.Coupon;
import springboot.jpa.booking.core.domain.coupon.CouponRepository;
import springboot.jpa.booking.core.validator.CouponValidator;
import springboot.jpa.booking.service.converter.CouponConverter;
import springboot.jpa.booking.web.coupon.dto.CouponRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponApiService {

    private final CouponRepository couponRepository;
    private final CouponValidator couponValidator;
    private final CouponConverter couponConverter;


    public String addThisMonthCoupons(Long memberId) {
        List<Coupon> coupons = couponRepository.getThisMonthCoupons();
        if (couponValidator.checkListSize(coupons)) {
            for (Coupon coupon : coupons) {
                coupon.addMember(memberId);
            }
            return "쿠폰 받기 성공!";
        }
        return "이달의 쿠폰이 없습니다.";
    }



    public Long isCanUseCoupon(Long id, int totalPrice) {
        Coupon coupon = couponRepository.findById(id).get();
        return couponValidator.applyDiscount(coupon, totalPrice);
    }

    // ================= CRUD =============== //
    public Long save(CouponRequestDto couponRequestDto) {
        return couponRepository.save(couponRequestDto.toEntity()).getId();
    }

    public Long update(Long id, CouponRequestDto couponRequestDto) {
        Coupon coupon = couponRepository.findById(id).get();
        coupon.update(couponRequestDto);
        return id;
    }

    public Long delete(Long id) {
        couponRepository.deleteById(id);
        return id;
    }
}
