package kr.team.ticketing.web.coupon.dto;


import kr.team.ticketing.core.domain.coupon.CouponStatus;
import kr.team.ticketing.core.domain.generic.money.Ratio;
import lombok.*;

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
