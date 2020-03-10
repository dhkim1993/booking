package kr.team.ticketing.core.domain.product.repository;


import kr.team.ticketing.core.domain.product.InfoByDate;
import kr.team.ticketing.core.domain.product.Product;
import kr.team.ticketing.core.domain.product.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustom {
    List<InfoByDate> getInfoByDatesByProductId(Long productId);

    Page<Product> searchAll(Pageable pageable);//장르상관없이 전부

    Page<Product> searchByDynamicCondition(SearchCondition searchCondition);

}
