package kr.team.ticketing.domain.member;

import kr.team.ticketing.core.domain.coupon.Coupon;
import kr.team.ticketing.core.domain.coupon.CouponRepository;
import kr.team.ticketing.core.domain.generic.money.Money;
import kr.team.ticketing.core.domain.generic.money.Ratio;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CouponTest {

    @Autowired
    CouponRepository couponRepository;

    @Before
    public void setUp() {
        couponRepository.save(Coupon.builder()
                .name("2월 할인 3000")
                .discount(Ratio.valueOf(3000))
                .expirationDate(LocalDate.now().plusMonths(2))
                .month(2)
                .build());

        couponRepository.save(Coupon.builder()
                .name("2월 할인 10%")
                .discount(Ratio.valueOf(0.1))
                .month(2)
                .build());

        couponRepository.save(Coupon.builder()
                .name("깜짝 할인 10000")
                .discount(Ratio.valueOf(10000))
                .build());

    }
    @After
    public void clearUp() {
        couponRepository.deleteAll();
    }


    @Test
    public void 쿠폰적용테스트_숫자() {
        Coupon byName = couponRepository.findByName("깜짝 할인 10000");
        Ratio ratio = byName.getDiscount();
        Money price = Money.wons(20000);
        assertThat(ratio.of(price).toString()).isEqualTo("10000원");
    }

    @Test
    public void 쿠폰적용테스트_퍼센트() {
        Coupon byName = couponRepository.findByName("2월 할인 10%");
        Ratio ratio = byName.getDiscount();
        Money price = Money.wons(20000);
        assertThat(ratio.of(price).toString()).isEqualTo("18000원");
    }
}