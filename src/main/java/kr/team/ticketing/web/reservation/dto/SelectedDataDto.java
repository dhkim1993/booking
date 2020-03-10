package kr.team.ticketing.web.reservation.dto;

import kr.team.ticketing.core.domain.generic.Email;
import kr.team.ticketing.core.domain.reservation.Reservation;
import kr.team.ticketing.core.domain.reservation.ReservationOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static kr.team.ticketing.support.utils.DateTimeUtils.formatToLocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectedDataDto {

    private Long reservationId;
    private Long productId;
    private Long memberId;
    private Long couponId;

    private String productName;

    private String memberName;
    private String email;
    private String tel;

    private String selectDate;
    private String selectTime;

    private String location;

    private String optionName1;
    private String optionName2;
    private String optionName3;
    private int optionCount1;
    private int optionCount2;
    private int optionCount3;

    private int totalCnt;
    private int totalPrice;
    private Long finalPrice;

    public Reservation toEntity(List<ReservationOption> options) {
        if (selectDate == null) {
            return Reservation.builder()
                    .memberId(memberId)
                    .productId(productId)
                    .productName(productName)
                    .memberName(memberName)
                    .email(new Email(email))
                    .tel(tel)
                    .location(location)
                    .reservationOptions(options)
                    .totalCnt(totalCnt)
                    .totalPrice(totalPrice)
                    .build();
        } else {
            return Reservation.builder()
                    .memberId(memberId)
                    .productId(productId)
                    .productName(productName)
                    .memberName(memberName)
                    .email(new Email(email))
                    .tel(tel)
                    .selectDate(formatToLocalDate(selectDate))
                    .selectTime(selectTime)
                    .location(location)
                    .reservationOptions(options)
                    .totalCnt(totalCnt)
                    .totalPrice(totalPrice)
                    .build();
        }
    }
}
