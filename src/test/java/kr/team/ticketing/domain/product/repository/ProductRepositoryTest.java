package kr.team.ticketing.domain.product.repository;

import kr.team.ticketing.core.domain.product.InfoByDate;
import kr.team.ticketing.core.domain.product.Option;
import kr.team.ticketing.core.domain.product.Product;
import kr.team.ticketing.core.domain.product.SearchCondition;
import kr.team.ticketing.core.domain.product.repository.ProductRepository;
import kr.team.ticketing.service.product.InfoByDateService;
import kr.team.ticketing.service.product.ProductService;
import kr.team.ticketing.web.product.dto.ProductSaveDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    InfoByDateService infoByDateService;


    @Before
    public void setUp() {
        /*ProductSaveDto dto = ProductSaveDto.builder()
                .categoryId(1L)
                .description("공연이다")
                .endDate(LocalDate.now().plusDays(10))
                .startDate(LocalDate.now())
                .location("서울")
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
        for (int i = 0; i < 30; i++) {
            productRepository.save(dto.toEntity(options, infoByDate));
        }*/
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

    @After
    public void cleanUp() {
        productRepository.deleteAll();
    }

    @Test
    public void searchByDynamicConditionTest() throws Exception {
        //given
        int page = 0;
        int size = 0;
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
        PageRequest pageRequest = PageRequest.of(2, 10);
        Page<Product> products = productRepository.searchAll(pageRequest);
        assertThat(products.getContent().size()).isEqualTo(10);
        assertThat(products.getTotalPages()).isEqualTo(4);
        assertThat(products.getTotalElements()).isEqualTo(32);
    }

    @Test
    public void getInfoByDatesByProductIdTest() throws Exception {
        List<InfoByDate> infoByDatesByProductId = productRepository.getInfoByDatesByProductId(1L);
        InfoByDate infoByDate = infoByDatesByProductId.get(0);
        System.out.println(infoByDate.getDate());
        System.out.println(infoByDate.getThirdTime());
    }
}