package kr.team.ticketing.domain.generic.money;

import kr.team.ticketing.core.domain.generic.money.Money;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    @Test
    public void insert(){
        Money money = Money.wons(13000);
        assertThat(money.getAmount()).isEqualTo("13000");
        assertThat(money.toString()).isEqualTo("13000원");
    }

    @Test
    public void plus(){
        Money money = Money.wons(15000);
        assertThat(money.plus(Money.wons(13000)).getAmount()).isEqualTo("28000");
    }

    @Test
    public void minus(){
        Money money = Money.wons(15000);
        assertThat(money.minus(Money.wons(13000)).getAmount()).isEqualTo("2000");
    }

    @Test
    public void times(){
        Money money = Money.wons(15000);
        assertThat(money.times(2.0).getAmount()).isEqualTo("30000.0");
    }

    @Test
    public void divide(){
        Money money = Money.wons(314);
        assertThat(money.divide(100).getAmount()).isEqualTo("3.14");
    }
}