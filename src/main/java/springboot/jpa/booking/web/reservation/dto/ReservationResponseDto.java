package springboot.jpa.booking.web.reservation.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springboot.jpa.booking.core.domain.generic.Email;
import springboot.jpa.booking.core.domain.reservation.ReservationOption;
import springboot.jpa.booking.core.domain.reservation.ReservationStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponseDto {
    private Long id;
    private Long memberId;
    private Long productId;
    private String productName;
    private String memberName;
    private Email email;
    private String tel;
    private LocalDate selectDate;
    private String selectTime;
    private String location;
    private int totalCnt;
    private int totalPrice;
    private Long finalPrice;
    private ReservationStatus reservationStatus;
    private List<ReservationOption> reservationOptions = new ArrayList<>();
}
