package kr.team.ticketing.core.domain.product.repository;

import kr.team.ticketing.core.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> , ProductRepositoryCustom{
    List<Product> findAllByName(String name);
}
