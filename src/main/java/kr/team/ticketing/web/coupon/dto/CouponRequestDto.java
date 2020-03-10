package kr.team.ticketing.web.coupon.dto;

import kr.team.ticketing.core.domain.coupon.Coupon;
import kr.team.ticketing.core.domain.generic.money.Ratio;
import lombok.Getter;

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
