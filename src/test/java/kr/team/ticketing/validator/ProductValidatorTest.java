package kr.team.ticketing.validator;

import kr.team.ticketing.core.domain.product.Product;
import kr.team.ticketing.core.domain.reservation.Reservation;
import kr.team.ticketing.core.domain.reservation.ReservationRepository;
import kr.team.ticketing.core.validator.ProductValidator;
import kr.team.ticketing.service.product.ProductService;
import kr.team.ticketing.service.reservation.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductValidatorTest {

    @Autowired
    ProductValidator productValidator;
    @Autowired
    ProductService productService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ReservationRepository reservationRepository;


    @Before
    public void setUp() {
    }
    @Test
    public void betweenTest() {
        LocalDate start = LocalDate.of(2020,2,1);
        LocalDate end = LocalDate.of(2020,2,10);
        LocalDate target = LocalDate.of(2020,2,1);

        if(target.isAfter(start.minusDays(1))) System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        else System.out.println("44123412342134123124123412224112442124214124");
        /*assertThat(start.minusDays(1).isAfter(target)).isEqualTo(true);*/
    }

    @Test
    public void toStringTest() throws Exception {
        //given
        System.out.println(LocalDate.now().toString());
        //when
        //then
    }

    @Test
    public void minusQuantityTest() throws Exception {
        //given
        Product product = productService.findById(1L);
        Reservation reservation = reservationRepository.findById(2L).get();
        //when
        productValidator.checkQuantity(product, reservation);
        LocalDate selectDate = reservation.getSelectDate();
        String selectTime = reservation.getSelectTime();
        int totalCnt = reservation.getTotalCnt();
        //then



    }
}