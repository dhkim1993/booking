package springboot.jpa.booking.core.domain.product.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import springboot.jpa.booking.core.domain.product.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom{
    List<Product> findAllByName(String name);
}
