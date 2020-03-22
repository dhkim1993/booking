package springboot.jpa.booking.service.coupon;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.booking.core.domain.coupon.Coupon;
import springboot.jpa.booking.core.domain.coupon.CouponRepository;
import springboot.jpa.booking.service.converter.CouponConverter;
import springboot.jpa.booking.web.coupon.dto.CouponResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponConverter couponConverter;

    public List<CouponResponseDto> getNotUsedCoupons(Long memberId) {
        List<Coupon> coupons = couponRepository.getNotUsedCouponsByMemberId(memberId);
        return coupons.stream().map(couponConverter::convert).collect(Collectors.toList());
    }

    public CouponResponseDto findByIdResponseDto(Long id) {
        return couponConverter.convert(couponRepository.findById(id).get());
    }

    public List<CouponResponseDto> findAllCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        return coupons.stream().map(couponConverter::convert).collect(Collectors.toList());
    }

    public Coupon findById(Long id) {
        return couponRepository.findById(id).get();
    }
    public Coupon findByName(String name) {
        return couponRepository.findByName(name);
    }
    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }
}
