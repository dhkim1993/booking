package springboot.jpa.booking.core.domain.generic.money;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class Ratio {
    private double rate;

    public static Ratio valueOf(double rate) {
        return new Ratio(rate);
    }

    Ratio(double rate) {
        this.rate = rate;
    }

    private Money getDiscountPrice(){
        return new Money(BigDecimal.valueOf(rate));
    }

    public Money of(Money price) {
        if (rate < 1) {
            Money discount = price.times(rate);
            return price.minus(discount);
        } else {
            Money discount = getDiscountPrice();
            return price.minus(discount);
        }
    }
}
