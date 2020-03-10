package kr.team.ticketing.web.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationSaveDto {

    private Long selectedDataId;
    private Long couponId;
    private Long memberId;
    private Long finalPrice;
}
