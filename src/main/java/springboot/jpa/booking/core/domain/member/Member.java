package springboot.jpa.booking.core.domain.member;


import lombok.*;
import springboot.jpa.booking.core.domain.coupon.Coupon;
import springboot.jpa.booking.core.domain.generic.Email;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "MEMBERS")
@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column
    private String name;

    @Column
    private String picture;

    @Embedded
    private Email email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @OneToMany
    @JoinColumn(name = "member_id")
    private List<Coupon> couponList = new ArrayList<>();


    //=== 비즈니스로직 ===//

    public void addCoupon(Coupon coupon) {
        this.couponList.add(coupon);
    }

    public void addMonthCoupon(List<Coupon> coupons) {
        this.couponList.addAll(coupons);
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public Member update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    @Builder
    public Member(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = new Email(email);
        this.picture = picture;
        this.role = role;
    }
}
