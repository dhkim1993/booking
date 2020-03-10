package kr.team.ticketing.domain.product;

import kr.team.ticketing.core.domain.product.Product;
import kr.team.ticketing.core.domain.product.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class ProductTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveTest() {

        Product build = Product.builder()
                .name("반고흐")
                .categoryId(1L)
                .location("서울")
                .startDate(LocalDate.of(2020,2,8))
                .endDate(LocalDate.of(2020,3,8))
                .count(100)
                .description("asdasd")
                .build();

        productRepository.save(build);
    }



}