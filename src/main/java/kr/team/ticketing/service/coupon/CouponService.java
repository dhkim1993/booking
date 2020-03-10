package kr.team.ticketing.service.coupon;

import kr.team.ticketing.core.domain.coupon.Coupon;
import kr.team.ticketing.core.domain.coupon.CouponRepository;
import kr.team.ticketing.service.converter.CouponConverter;
import kr.team.ticketing.web.coupon.dto.CouponResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
