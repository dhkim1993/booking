package kr.team.ticketing.web.product.controller;

import kr.team.ticketing.core.domain.product.SearchCondition;
import kr.team.ticketing.service.product.ProductApiService;
import kr.team.ticketing.web.product.dto.ProductResponseDto;
import kr.team.ticketing.web.product.dto.ProductSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductApiService productApiService;


    @PostMapping("/api/v1/product/list")
    public List<ProductResponseDto> searchByDynamicCondition(@RequestBody SearchCondition searchCondition) {
        return productApiService.searchByDynamicCondition(searchCondition);
    }

    @PostMapping("/api/v2/product")
    public String save(@RequestBody ProductSaveDto productSaveDto) {
        productApiService.saveProduct(productSaveDto);
        return "save success!";
    }

    @PutMapping("/api/v2/product/{id}")
    public String update(@PathVariable("id") Long productId, @RequestBody ProductSaveDto productSaveDto) {
        productApiService.updateProduct(productId, productSaveDto);
        return "update success!";
    }

    @DeleteMapping("/api/v2/product/{id}")
    public String delete(@PathVariable("id") Long id) {
        productApiService.deleteProduct(id);
        return "delete success!";
    }
}
