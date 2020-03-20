package springboot.jpa.booking.web.reservation.dto;



import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springboot.jpa.booking.core.domain.generic.Email;
import springboot.jpa.booking.core.domain.reservation.Reservation;
import springboot.jpa.booking.core.domain.reservation.ReservationOption;

import java.util.List;

import static springboot.jpa.booking.support.utils.DateTimeUtils.formatToLocalDate;


@Getter
@NoArgsConstructor
public class SelectedDataDto {

    private Long reservationId;
    private Long productId;
    private Long memberId;

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

    @Builder
    public SelectedDataDto(Long reservationId, Long productId, Long memberId, String productName, String memberName, String email, String tel, String selectDate, String selectTime, String location, String optionName1, String optionName2, String optionName3, int optionCount1, int optionCount2, int optionCount3, int totalCnt, int totalPrice, Long finalPrice) {
        this.reservationId = reservationId;
        this.productId = productId;
        this.memberId = memberId;
        this.productName = productName;
        this.memberName = memberName;
        this.email = email;
        this.tel = tel;
        this.selectDate = selectDate;
        this.selectTime = selectTime;
        this.location = location;
        this.optionName1 = optionName1;
        this.optionName2 = optionName2;
        this.optionName3 = optionName3;
        this.optionCount1 = optionCount1;
        this.optionCount2 = optionCount2;
        this.optionCount3 = optionCount3;
        this.totalCnt = totalCnt;
        this.totalPrice = totalPrice;
        this.finalPrice = finalPrice;
    }
}
