package springboot.jpa.booking.service.converter;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import springboot.jpa.booking.core.domain.category.Category;
import springboot.jpa.booking.web.product.dto.CategoryResponseDto;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    private final ModelMapper modelMapper;

    public CategoryResponseDto convert(Category category) {
        return modelMapper.map(category, CategoryResponseDto.class);
    }
}