/*
package kr.team.ticketing.domain.product;

import kr.team.ticketing.domain.product.repository.ProductRepository;
import kr.team.ticketing.web.product.dto.ProductSaveDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static kr.team.ticketing.domain.product.InfoByDate.createInfoByDate;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class InformationByDateTest {

    @Autowired
    private ProductRepository productRepository;



    @Before
    public void setUp() {

    }

    @Test
    public void createInfoByDateTest() throws Exception {
        //given
        ProductSaveDto pp =  ProductSaveDto.builder()
                .categoryId(3)
                .name("돌다리")
                .description("asdasd")
                .startDate(LocalDate.of(2020, 3, 8))
                .endDate(LocalDate.of(2020, 3, 27))
                .location("서울")
                .option1("VIP")
                .option2("R")
                .option3("S")
                .price1(50000)
                .price2(40000)
                .price3(30000)
                .count(50)
                .build();

        List<InfoByDate> infoByDate = createInfoByDate(pp);
        for (InfoByDate byDate : infoByDate) {
            DayOfWeek dayOfWeek = byDate.getDate().getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.SUNDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                System.out.println(byDate.getDate()+": weekend!!");
            }else System.out.println(byDate.getDate());
        }
        //when
        */
/*long runtime = 30L;
        int count = productSaveDto.getCount();
        LocalDate startDate = productSaveDto.getStartDate();
        for (int i = 0; i < runtime; i++) {
            DayOfWeek dayOfWeek = startDate.getDayOfWeek();
            System.out.print(startDate+" : "+dayOfWeek);
            if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
                System.out.println(" weekend!!!");
            } else {
                System.out.println("-_-");
            }
            startDate = startDate.plusDays(1);
        }*//*


        //then
    }
}*/
