package kr.team.ticketing.domain.generic.money;

import kr.team.ticketing.core.domain.coupon.CouponRepository;
import kr.team.ticketing.core.domain.generic.money.Money;
import kr.team.ticketing.core.domain.generic.money.Ratio;
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
public class RatioTest {

    @Autowired
    CouponRepository couponRepository;

    @Test
    public void discount() {
        Ratio ratio1 = Ratio.valueOf(0.2);
        Ratio ratio2 = Ratio.valueOf(2000);
        int p = 15000;
        Money price = Money.wons(p);
        assertThat(ratio1.of(price).toString()).isEqualTo("12000원");
        assertThat(ratio2.of(price).toString()).isEqualTo("13000원");
    }
}
