package springboot.jpa.booking.web.product.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.jpa.booking.core.domain.product.SearchCondition;
import springboot.jpa.booking.service.product.ProductApiService;
import springboot.jpa.booking.web.product.dto.ProductResponseDto;
import springboot.jpa.booking.web.product.dto.ProductSaveDto;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductApiService productApiService;


    @PostMapping("/api/v1/product/list")
    public List<ProductResponseDto> searchByDynamicCondition(@RequestBody SearchCondition searchCondition) {
        return productApiService.searchByDynamicCondition(searchCondition);
    }

    @PostMapping("/api/v3/product")
    public String save(@RequestBody ProductSaveDto productSaveDto) {
        productApiService.saveProduct(productSaveDto);
        return "save success!";
    }

    @PutMapping("/api/v3/product/{id}")
    public String update(@PathVariable("id") Long productId, @RequestBody ProductSaveDto productSaveDto) {
        productApiService.updateProduct(productId, productSaveDto);
        return "update success!";
    }

    @DeleteMapping("/api/v3/product/{id}")
    public String delete(@PathVariable("id") Long id) {
        productApiService.deleteProduct(id);
        return "delete success!";
    }
}
