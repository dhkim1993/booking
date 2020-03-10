package kr.team.ticketing.service.member;

import kr.team.ticketing.core.domain.coupon.Coupon;
import kr.team.ticketing.core.domain.coupon.CouponRepository;
import kr.team.ticketing.core.domain.generic.money.Ratio;
import kr.team.ticketing.core.domain.member.Member;
import kr.team.ticketing.core.domain.member.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    MemberService memberService;

    @Before
    public void setUp() {

        couponRepository.save(Coupon.builder()
                .name("2월 할인 3000")
                .discount(Ratio.valueOf(3000))
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

        memberRepository.save(Member.builder()
                .name("김철수")
                .email("chulsu@naver.com")
                .build());
    }

    @Test
    public void addOneTest() {
        memberService.addCoupon(1L, "깜짝 할인 10000");
        Coupon coupon = memberRepository.findById(1L).get().getCouponList().get(0);
        Ratio discount = coupon.getDiscount();
        assertThat(discount.getRate()).isEqualTo(10000);
    }


}