package kr.team.ticketing.core.domain.product;

import kr.team.ticketing.web.product.dto.ProductSaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "options_id")
    private Long id;

    private String optionName;
    private int price;
    private int count;

    public static List<Option> createOptions(ProductSaveDto productSaveDto) {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder().optionName(productSaveDto.getOption1()).price(productSaveDto.getPrice1()).count(0).build());
        list.add(Option.builder().optionName(productSaveDto.getOption2()).price(productSaveDto.getPrice2()).count(0).build());
        list.add(Option.builder().optionName(productSaveDto.getOption3()).price(productSaveDto.getPrice3()).count(0).build());
        return list;
    }

    public static void updateOptions(List<Option> options, ProductSaveDto productSaveDto) {
        Option option = options.get(0);
        option.optionName = productSaveDto.getOption1();
        option.price = productSaveDto.getPrice1();
        option.count = 0;

        Option option2 = options.get(1);
        option2.optionName = productSaveDto.getOption2();
        option2.price = productSaveDto.getPrice2();
        option2.count = 0;

        Option option3 = options.get(2);
        option3.optionName = productSaveDto.getOption3();
        option3.price = productSaveDto.getPrice3();
        option3.count = 0;
    }

    @Builder
    public Option(String optionName, int price, int count) {
        this.optionName = optionName;
        this.price = price;
        this.count = count;
    }
}
