package springboot.jpa.booking.core.domain.coupon;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springboot.jpa.booking.core.domain.generic.money.Ratio;
import springboot.jpa.booking.web.coupon.dto.CouponRequestDto;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue
    @Column(name = "COUPON_ID")
    private Long id;

    private String name;

    private int month;

    private Ratio discount;

    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    private CouponStatus couponStatus;

    @Column(name = "member_id")
    private Long memberId;

    // ========= 비즈니스로직 ==================== //

    public void changeCouponStatus() {
        this.couponStatus = CouponStatus.AFTER;
    }

    public void cancelThenStatusChangeToBefore() {
        this.couponStatus = CouponStatus.BEFORE;
    }

    public void update(CouponRequestDto couponRequestDto) {
        this.name = couponRequestDto.getName();
        this.month = couponRequestDto.getMonth();
        this.discount = Ratio.valueOf(couponRequestDto.getRate());
        this.expirationDate = couponRequestDto.getExpirationDate();
    }

    public void addMember(Long memberId) {
        this.memberId = memberId;
    }

    @Builder
    public Coupon(String name, int month, Ratio discount, LocalDate expirationDate) {
        this.discount= discount;
        this.name=name;
        this.month= month;
        this.expirationDate=expirationDate;
        this.couponStatus=CouponStatus.BEFORE;
    }
}
