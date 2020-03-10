package kr.team.ticketing.service.product;

import kr.team.ticketing.core.domain.category.CategoryRepository;
import kr.team.ticketing.core.domain.product.InfoByDate;
import kr.team.ticketing.core.domain.product.Option;
import kr.team.ticketing.core.domain.product.Product;
import kr.team.ticketing.core.domain.product.repository.ProductRepository;
import kr.team.ticketing.service.converter.ProductConverter;
import kr.team.ticketing.web.product.dto.CategoryResponseDto;
import kr.team.ticketing.web.product.dto.ProductResponseDto;
import kr.team.ticketing.web.product.dto.ProductSaveDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    ProductService productService;
    @Autowired
    InfoByDateService infoByDateService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductConverter converter;


    @Before
    public void setUp() {
        for (int i = 20; i >=1; i--) {
            ProductSaveDto dto = ProductSaveDto.builder()
                    .categoryId(1L)
                    .description("공연이다")
                    .endDate(LocalDate.now().plusDays(30 - i))
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
            productRepository.save(dto.toEntity(options, infoByDate));
        }
    }
    @After
    public void cleanUp() {
        productRepository.deleteAll();
    }

    @Test
    public void searchAllServiceTest() throws Exception {
        //given
        PageRequest pageRequest = PageRequest.of(0, 10);
        //when
        List<ProductResponseDto> productResponseDtos = productService.searchAll(pageRequest);
        //then
        assertThat(productResponseDtos.size()).isEqualTo(10);
    }

    @Test
    public void productsConverterTest() {
        List<ProductResponseDto> allProductResponseDto = productService.findAllProductResponseDto();
        assertThat(allProductResponseDto.get(0).getCategoryId()).isEqualTo(1L);
        assertThat(allProductResponseDto.get(0).getCount()).isEqualTo(50);
        assertThat(allProductResponseDto.get(0).getEndDate()).isEqualTo(LocalDate.of(2020,4,4));
    }
    @Test
    public void converterTest() {
        List<CategoryResponseDto> categoryResponseDtos = productService.getCategoriesResponseDto();
        assertThat(categoryResponseDtos.get(0).getName()).isEqualTo("뮤지컬");
        assertThat(categoryResponseDtos.get(1).getName()).isEqualTo("전통공연");
        assertThat(categoryResponseDtos.get(2).getName()).isEqualTo("연극");
        assertThat(categoryResponseDtos.get(3).getName()).isEqualTo("전시회");
    }

    @Test
    public void optionTest() {
        List<Product> allProduct = productService.findAllProduct();
        List<Option> options = allProduct.get(0).getOptions();
        for (Option option : options) {
            System.out.println(option.getOptionName());
        }
    }

    @Test
    public void 날짜차이계산테스트() {
        int count = 40;
        LocalDateTime startDate = LocalDate.now().atStartOfDay();
        LocalDateTime endDate = LocalDate.of(2020, 3, 5).atStartOfDay();
        Duration between = Duration.between(startDate, endDate);
        long l = between.toDays();
        System.out.println(l);
    }
}
