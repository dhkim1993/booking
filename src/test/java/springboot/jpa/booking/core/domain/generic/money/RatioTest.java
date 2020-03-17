package springboot.jpa.booking.core.domain.generic.money;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class RatioTest {

    @Test
    public void discount() {
        //given
        Ratio ratio1 = Ratio.valueOf(0.2);
        Ratio ratio2 = Ratio.valueOf(2000);
        int p = 15000;
        Money price = Money.wons(p);
        //then
        assertThat(ratio1.of(price).toString()).isEqualTo("12000원");
        assertThat(ratio2.of(price).toString()).isEqualTo("13000원");
    }
}