package springboot.jpa.booking.service.converter;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import springboot.jpa.booking.core.domain.product.Option;
import springboot.jpa.booking.core.domain.product.Product;
import springboot.jpa.booking.web.product.dto.ProductOptionResponseDto;
import springboot.jpa.booking.web.product.dto.ProductResponseDto;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final ModelMapper modelMapper;

    public ProductResponseDto convert(Product product) {
        return modelMapper.map(product, ProductResponseDto.class);
    }

    public ProductOptionResponseDto optionConvert(Option option) {
        return modelMapper.map(option, ProductOptionResponseDto.class);
    }


}
