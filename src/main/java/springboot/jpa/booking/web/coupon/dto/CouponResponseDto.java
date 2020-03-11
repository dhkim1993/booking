package springboot.jpa.booking.web.coupon.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springboot.jpa.booking.core.domain.coupon.CouponStatus;
import springboot.jpa.booking.core.domain.generic.money.Ratio;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CouponResponseDto {

    private Long id;
    private String name;
    private int month;
    private Ratio discount;
    private LocalDate expirationDate;
    private CouponStatus couponStatus;

}
