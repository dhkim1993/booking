package springboot.jpa.booking.core.domain.coupon;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.booking.core.domain.generic.money.Ratio;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static springboot.jpa.booking.core.domain.coupon.QCoupon.coupon;

@SpringBootTest
@Transactional
class CouponRepositoryTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @Autowired
    CouponRepository couponRepository;

    @BeforeEach
    public void before() {

        queryFactory = new JPAQueryFactory(em);

        for (int i = 0; i < 10; i++) {
            em.persist(Coupon.builder()
                    .discount(Ratio.valueOf(0.5))
                    .expirationDate(LocalDate.now().plusDays(10))
                    .month(3)
                    .name("3월 쿠폰")
                    .build());

            em.persist(Coupon.builder()
                    .discount(Ratio.valueOf(0.5))
                    .expirationDate(LocalDate.now().minusDays(10))
                    .month(5)
                    .name("3월 쿠폰")
                    .build());
        }
    }

    @AfterEach
    public void after() {
        /*em.flush();
        em.clear();
        em.close();*/
    }

    @Test
    public void getNotUsedCouponsByMemberIdTest() throws Exception {
        //given
        //when
        List<Coupon> coupons = queryFactory
                .selectFrom(coupon)
                .where(coupon.couponStatus.eq(CouponStatus.BEFORE)
                        .and(coupon.expirationDate.goe(LocalDate.now())))
                .fetch();
        //then
        assertThat(coupons.size()).isEqualTo(10);
    }

    @Test
    public void getThisMonthCouponsTest() throws Exception {
        //given
        //when
        List<Coupon> coupons = queryFactory
                .selectFrom(coupon)
                .where(coupon.month.eq(getThisMonth()))
                .fetch();
        //then
        assertThat(coupons.size()).isEqualTo(10);
    }

    private int getThisMonth() {
        return LocalDate.now().getMonth().getValue();
    }
}