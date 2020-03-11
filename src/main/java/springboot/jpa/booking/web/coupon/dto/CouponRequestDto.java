package springboot.jpa.booking.web.coupon.dto;

import lombok.Getter;
import springboot.jpa.booking.core.domain.coupon.Coupon;
import springboot.jpa.booking.core.domain.generic.money.Ratio;

import java.time.LocalDate;


@Getter
public class CouponRequestDto {

    private String name;
    private int month;
    private double rate;
    private LocalDate expirationDate;

    public Coupon toEntity() {
        return Coupon.builder()
                .name(name)
                .month(month)
                .discount(Ratio.valueOf(rate))
                .expirationDate(expirationDate)
                .build();
    }
}
