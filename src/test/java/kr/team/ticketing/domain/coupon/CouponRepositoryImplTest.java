package kr.team.ticketing.domain.coupon;

import kr.team.ticketing.core.domain.coupon.Coupon;
import kr.team.ticketing.core.domain.coupon.CouponRepository;
import kr.team.ticketing.core.domain.generic.money.Ratio;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by kimdonghyun on 02/03/2020.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class CouponRepositoryImplTest {

    @Autowired
    CouponRepository couponRepository;

    @Before
    public void setUp() {
        for (int i = 1; i < 5; i++) {
            couponRepository.save(Coupon.builder()
                    .name("3월 초특가 할인쿠폰")
                    .discount(Ratio.valueOf(0.5))
                    .month(3)
                    .expirationDate(LocalDate.now().plusMonths(1))
                    .build());
        }
        for (int i = 5; i < 10; i++) {
            couponRepository.save(Coupon.builder()
                    .name("3월 초특가 할인쿠폰")
                    .discount(Ratio.valueOf(0.5))
                    .month(3)
                    .expirationDate(LocalDate.of(2020, 3, 2))
                    .build());
        }
    }

    @Test
    public void couponSelectTest() throws Exception {
        //given
        List<Coupon> coupons = couponRepository.getNotUsedCouponsByMemberId(1L);
        //when
        for (Coupon coupon : coupons) {
            System.out.println(coupon.getName());
        }
        //then
    }
    @Test
    public void selectTest() throws Exception {
        //given
        List<Coupon> all = couponRepository.findAll();
        for (Coupon coupon : all) {
            System.out.println(coupon.getName());
        }
        //when

        //then
    }
}