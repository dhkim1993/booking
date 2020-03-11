package springboot.jpa.booking.service.converter;



import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import springboot.jpa.booking.core.domain.coupon.Coupon;
import springboot.jpa.booking.web.coupon.dto.CouponResponseDto;

@Component
@RequiredArgsConstructor
public class CouponConverter {

    private final ModelMapper modelMapper;

    public CouponResponseDto convert(Coupon coupon) {
        return modelMapper.map(coupon, CouponResponseDto.class);
    }

}