package kr.team.ticketing.service.converter;

import kr.team.ticketing.core.domain.product.Option;
import kr.team.ticketing.core.domain.product.Product;
import kr.team.ticketing.web.product.dto.ProductOptionResponseDto;
import kr.team.ticketing.web.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
