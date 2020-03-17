package springboot.jpa.booking.core.domain.product.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.booking.core.domain.product.InfoByDate;
import springboot.jpa.booking.core.domain.product.Option;
import springboot.jpa.booking.core.domain.product.Product;
import springboot.jpa.booking.core.domain.product.SearchCondition;
import springboot.jpa.booking.service.product.InfoByDateService;
import springboot.jpa.booking.service.product.ProductService;
import springboot.jpa.booking.web.product.dto.ProductSaveDto;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    InfoByDateService infoByDateService;

    @BeforeEach
    public void before() {
        for (int i = 20; i >=1; i--) {
            ProductSaveDto dto = ProductSaveDto.builder()
                    .categoryId(1L)
                    .description("공연이다")
                    .endDate(LocalDate.now().plusDays(30 - i))
                    .startDate(LocalDate.now())
                    .location("서울")
                    .name("햄릿")
                    .count(40)
                    .option1("평일할인")
                    .option2("주말할인")
                    .option3("이벤트가")
                    .price1(10000)
                    .price2(20000)
                    .price3(5000)
                    .firstTime("10:00")
                    .secondTime("13:00")
                    .thirdTime("17:00")
                    .fourthTime("20:00")
                    .build();
            List<Option> options = Option.createOptions(dto);
            List<InfoByDate> infoByDate = infoByDateService.createInfoByDate(dto);
            productRepository.save(dto.toEntity(options, infoByDate));
        }
        for (int i = 1; i < 10; i++) {
            ProductSaveDto dto = ProductSaveDto.builder()
                    .categoryId(3L)
                    .description("공연이다")
                    .endDate(LocalDate.now().plusMonths(i))
                    .startDate(LocalDate.now())
                    .location("부산")
                    .name("햄릿")
                    .option1("평일할인")
                    .option2("주말할인")
                    .option3("이벤트가")
                    .price1(10000)
                    .price2(20000)
                    .price3(5000)
                    .count(30)
                    .firstTime("10:00")
                    .secondTime("13:00")
                    .thirdTime("17:00")
                    .fourthTime("20:00")
                    .build();
            List<Option> options = Option.createOptions(dto);
            List<InfoByDate> infoByDate = infoByDateService.createInfoByDate(dto);
            productRepository.save(dto.toEntity(options, infoByDate));
        }
    }

    @AfterEach
    public void after() {
        productRepository.deleteAll();
    }

    @Test
    public void searchByDynamicConditionTest() throws Exception {
        //given
        int page = 0;
        int size = 10;
        Long id = null;
        String location = "부산";
        int month = 4;
        SearchCondition searchCondition = SearchCondition.builder()
                .page(page)
                .size(size)
                .categoryId(id)
                .location(location)
                .month(month)
                .build();
        //when
        Page<Product> products = productRepository.searchByDynamicCondition(searchCondition);
        long totalElements = products.getTotalElements();
        //then
        assertThat(totalElements).isEqualTo(9);
    }

    @Test
    public void searchAllProductPagingTest() throws Exception {
        //given
        PageRequest pageRequest = PageRequest.of(2, 10);
        //when
        Page<Product> products = productRepository.searchAll(pageRequest);
        //then
        assertThat(products.getContent().size()).isEqualTo(9);
        assertThat(products.getTotalPages()).isEqualTo(3);
        assertThat(products.getTotalElements()).isEqualTo(29);
    }
}