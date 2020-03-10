package kr.team.ticketing.service.product;

import kr.team.ticketing.core.domain.category.Category;
import kr.team.ticketing.core.domain.category.CategoryRepository;
import kr.team.ticketing.core.domain.product.InfoByDate;
import kr.team.ticketing.core.domain.product.Option;
import kr.team.ticketing.core.domain.product.Product;
import kr.team.ticketing.core.domain.product.repository.ProductRepository;
import kr.team.ticketing.service.converter.CategoryConverter;
import kr.team.ticketing.service.converter.InfoByDateConverter;
import kr.team.ticketing.service.converter.ProductConverter;
import kr.team.ticketing.web.product.dto.CategoryResponseDto;
import kr.team.ticketing.web.product.dto.InfoByDateResponseDto;
import kr.team.ticketing.web.product.dto.ProductOptionResponseDto;
import kr.team.ticketing.web.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final ProductConverter productConverter;
    private final InfoByDateConverter infoByDateConverter;


    public List<InfoByDateResponseDto> getInfoByDatesByProductId(Long productId) {
        List<InfoByDate> infoByDates = productRepository.getInfoByDatesByProductId(productId);
        return infoByDates.stream().map(infoByDateConverter::convert).collect(Collectors.toList());
    }

    public List<ProductResponseDto> searchAll(Pageable pageable) {
        Page<Product> products = productRepository.searchAll(pageable);
        return products.getContent().stream().map(productConverter::convert).collect(Collectors.toList());
    }

    public List<ProductOptionResponseDto> optionResponseDtos (Long productId) {
        List<Option> options = productRepository.findById(productId).get().getOptions();
        return options.stream().map(productConverter::optionConvert).collect(Collectors.toList());
    }

    public ProductResponseDto findByIdResponseDto(Long id) {
        return productConverter.convert(productRepository.findById(id).get());
    }

    public List<ProductResponseDto> findAllProductResponseDto() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productConverter::convert).collect(Collectors.toList());
    }

    public List<CategoryResponseDto> getCategoriesResponseDto() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryConverter::convert).collect(Collectors.toList());
    }

    // ===============================================================================================================//

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }
}
