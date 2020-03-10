package kr.team.ticketing.service.converter;

import kr.team.ticketing.core.domain.category.Category;
import kr.team.ticketing.web.product.dto.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    private final ModelMapper modelMapper;

    public CategoryResponseDto convert(Category category) {
        return modelMapper.map(category, CategoryResponseDto.class);
    }
}