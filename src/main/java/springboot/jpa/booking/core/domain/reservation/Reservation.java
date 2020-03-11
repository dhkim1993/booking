package springboot.jpa.booking.core.domain.reservation;

import lombok.*;
import springboot.jpa.booking.core.domain.generic.Email;
import springboot.jpa.booking.core.domain.generic.entity.BaseTimeEntity;
import springboot.jpa.booking.web.reservation.dto.SelectedDataDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RESERVATIONS")
@Getter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "product_id")
    private Long productId;

    private String productName;
    private String memberName;
    @Embedded
    private Email email;
    private String tel;
    private LocalDate selectDate;
    private String selectTime;
    private String location;
    private int totalCnt;
    private int totalPrice;
    private Long finalPrice;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "RESERVATION_ID")
    private List<ReservationOption> reservationOptions = new ArrayList<>();

    // ================================================================================================== //


    public void addFinalPrice(Long finalPrice) {
        this.finalPrice = finalPrice;
    }
    public void addMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public void changeToAfter() {
        this.reservationStatus = ReservationStatus.AFTER;
    }
    public void cancel() {
        this.reservationStatus = ReservationStatus.CANCELED;
    }

    public Long update(SelectedDataDto selectedDataDto, List<ReservationOption> options) {
        this.memberName = selectedDataDto.getMemberName();
        this.email = new Email(selectedDataDto.getEmail());
        this.tel = selectedDataDto.getTel();
        this.totalCnt= selectedDataDto.getTotalCnt();
        this.totalPrice = selectedDataDto.getTotalPrice();
        List<ReservationOption> reservationOptions = this.reservationOptions;
        this.reservationOptions = options;
        reservationOptions.remove(this);
        return this.id;
    }

    @Builder
    public Reservation(Long memberId, String productName, String memberName, Email email, String tel, LocalDate selectDate, String selectTime, String location, int totalCnt, int totalPrice, Long finalPrice,  Long productId, List<ReservationOption> reservationOptions) {
        this.memberId = memberId;
        this.productName = productName;
        this.memberName = memberName;
        this.email = email;
        this.tel = tel;
        this.selectDate = selectDate;
        this.selectTime = selectTime;
        this.location = location;
        this.totalCnt = totalCnt;
        this.totalPrice = totalPrice;
        this.finalPrice = finalPrice;
        this.reservationStatus = ReservationStatus.BEFORE;
        this.productId = productId;
        this.reservationOptions = reservationOptions;
    }
}
