package kr.team.ticketing.domain.product;

import kr.team.ticketing.core.domain.product.repository.ProductRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class CountByDateTest {

    @Autowired
    ProductRepository productRepository;

/*
    @Before
    public void setUp() {

        Option option = Option.builder()
                .option1("VIP")
                .option2("R")
                .option3("S")
                .price1(100000)
                .price2(50000)
                .price3(30000)
                .build();

        List<CountByDate> list = new ArrayList<>();
        LocalDateTime start = LocalDate.of(2020, 2, 8).atStartOfDay();
        LocalDateTime ent = LocalDate.of(2020, 3, 8).atStartOfDay();
        long runtime = Duration.between(start, ent).toDays();
        for (int i = 0; i < runtime; i++) {
            list.add(new CountByDate(20));
        }

        Product build = Product.builder()
                .name("초콜릿")
                .category(new Category("아트"))
                .location("서울")
                .startDate(LocalDate.of(2020,2,8))
                .endDate(LocalDate.of(2020,3,8))
                .count(20)
                .description("asdasd")
                .countByDates(list)
                .option(option)
                .build();
        productRepository.save(build);

    }*/

    /*@Test
    public void countByDateTest() {
        List<Product> list = productRepository.findAllByName("지킬");
        if (!list.isEmpty()) {
            List<CountByDate> countByDates = list.get(0).getCountByDates();
            for (CountByDate countByDate : countByDates) {
                System.out.println(countByDate.getId()+" "+countByDate.getQuantity());
            }
        }else throw new IllegalStateException("세이브안됨@@@@@@@@@@@@");
    }*/
}