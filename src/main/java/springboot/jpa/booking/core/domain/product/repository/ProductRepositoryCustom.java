package springboot.jpa.booking.core.domain.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import springboot.jpa.booking.core.domain.product.InfoByDate;
import springboot.jpa.booking.core.domain.product.Product;
import springboot.jpa.booking.core.domain.product.SearchCondition;

import java.util.List;

public interface ProductRepositoryCustom {
    List<InfoByDate> getInfoByDatesByProductId(Long productId);

    Page<Product> searchAll(Pageable pageable);//장르상관없이 전부

    Page<Product> searchByDynamicCondition(SearchCondition searchCondition);

}
