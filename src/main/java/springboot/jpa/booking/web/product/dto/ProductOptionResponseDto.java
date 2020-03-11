package springboot.jpa.booking.web.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductOptionResponseDto {

    private Long id;
    private String optionName;
    private int price;
    private int count;
}
