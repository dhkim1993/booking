package kr.team.ticketing.service.converter;


import kr.team.ticketing.core.domain.coupon.Coupon;
import kr.team.ticketing.web.coupon.dto.CouponResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponConverter {

    private final ModelMapper modelMapper;

    public CouponResponseDto convert(Coupon coupon) {
        return modelMapper.map(coupon, CouponResponseDto.class);
    }

}