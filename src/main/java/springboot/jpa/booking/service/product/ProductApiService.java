package springboot.jpa.booking.service.product;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.booking.core.domain.product.InfoByDate;
import springboot.jpa.booking.core.domain.product.Option;
import springboot.jpa.booking.core.domain.product.Product;
import springboot.jpa.booking.core.domain.product.SearchCondition;
import springboot.jpa.booking.core.domain.product.repository.ProductRepository;
import springboot.jpa.booking.service.converter.ProductConverter;
import springboot.jpa.booking.web.product.dto.ProductResponseDto;
import springboot.jpa.booking.web.product.dto.ProductSaveDto;

import java.util.List;
import java.util.stream.Collectors;

import static springboot.jpa.booking.core.domain.product.InfoByDate.updateInfoByDate;
import static springboot.jpa.booking.core.domain.product.Option.createOptions;
import static springboot.jpa.booking.core.domain.product.Option.updateOptions;


@Service
@RequiredArgsConstructor
public class ProductApiService {

    private final ProductRepository productRepository;
    private final InfoByDateService infoByDateService;
    private final ProductConverter productConverter;

    @Transactional
    public List<ProductResponseDto> searchByDynamicCondition(SearchCondition searchCondition) {
        List<Product> products = productRepository.searchByDynamicCondition(searchCondition).getContent();
        return products.stream().map(productConverter::convert).collect(Collectors.toList());
    }

    @Transactional
    public Long saveProduct(ProductSaveDto productSaveDto) {
        List<Option> options = createOptions(productSaveDto);
        //전시회면 InfoByDate 생성 안함
        if (productSaveDto.getCategoryId() == 4) {
            return productRepository.save(productSaveDto.toEntity(options)).getId();
        }
        List<InfoByDate> infoByDates = infoByDateService.createInfoByDate(productSaveDto);
        return productRepository.save(productSaveDto.toEntity(options, infoByDates)).getId();
    }

    @Transactional
    public void updateProduct(Long productId, ProductSaveDto productSaveDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + productId));
        updateOptions(product.getOptions(), productSaveDto);
        updateInfoByDate(product.getInfoByDates(), productSaveDto);
        product.update(productSaveDto);
    }

    @Transactional
    public Long deleteProduct(Long id) {
        productRepository.deleteById(id);
        return id;
    }
}
