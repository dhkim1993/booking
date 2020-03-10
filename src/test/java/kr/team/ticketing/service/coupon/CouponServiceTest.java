package kr.team.ticketing.service.coupon;

import kr.team.ticketing.core.domain.coupon.CouponStatus;
import kr.team.ticketing.service.converter.CouponConverter;
import kr.team.ticketing.web.coupon.dto.CouponResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CouponServiceTest {

    @Autowired
    CouponConverter couponConverter;
    @Autowired
    CouponService couponService;

    @Test
    public void converterTest() {
        //given
        CouponResponseDto byIdResponseDto = couponService.findByIdResponseDto(1L);
        //then
        assertThat(byIdResponseDto.getId()).isEqualTo(1L);
        assertThat(byIdResponseDto.getDiscount().getRate()).isEqualTo(0.5);
        assertThat(byIdResponseDto.getCouponStatus()).isEqualTo(CouponStatus.AFTER);
        assertThat(byIdResponseDto.getMonth()).isEqualTo(3);
    }
}