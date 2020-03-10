/*
package kr.team.ticketing.service.reservation;


import kr.team.ticketing.domain.category.Category;
import kr.team.ticketing.domain.category.CategoryRepository;
import kr.team.ticketing.domain.common.Email;
import kr.team.ticketing.domain.coupon.Coupon;
import kr.team.ticketing.domain.coupon.CouponRepository;
import kr.team.ticketing.domain.generic.money.Money;
import kr.team.ticketing.domain.generic.money.Ratio;
import kr.team.ticketing.domain.member.Member;
import kr.team.ticketing.domain.member.MemberRepository;
import kr.team.ticketing.domain.member.Role;
import kr.team.ticketing.domain.product.InformationByDate;
import kr.team.ticketing.domain.product.Option;
import kr.team.ticketing.domain.product.Product;
import kr.team.ticketing.domain.product.repository.ProductRepository;
import kr.team.ticketing.domain.reservation.ReservationOption;
import kr.team.ticketing.domain.reservation.ReservationRepository;
import kr.team.ticketing.service.product.ProductService;
import kr.team.ticketing.web.product.dto.ProductSaveDto;
import kr.team.ticketing.web.reservation.dto.ReservationSaveDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static kr.team.ticketing.domain.product.CountByDate.createCountByDates;
import static kr.team.ticketing.domain.product.InformationByDate.createInformationByDate;
import static kr.team.ticketing.domain.product.Option.createOptions;
import static kr.team.ticketing.domain.reservation.ReservationOption.*;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class ReservationServiceTest {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProductService productService;
    @Autowired
    CouponRepository couponRepository;

    @Autowired
    ReservationService reservationService;

    @Autowired
    CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        Category category = categoryRepository.save(new Category("연극"));

        ProductSaveDto productSaveDto =  ProductSaveDto.builder()
                .categoryId(3L)
                .name("돌다리")
                .description("asdasd")
                .startDate(LocalDate.of(2020, 2, 10))
                .endDate(LocalDate.of(2020, 3, 8))
                .count(50)
                .location("서울")
                .option1("VIP")
                .option2("R")
                .option3("S")
                .price1(50000)
                .price2(40000)
                .price3(30000)
                .build();

        List<Option> options = createOptions(productSaveDto);
        List<InformationByDate> informationByDates = createInformationByDate(productSaveDto);
        productRepository.save(productSaveDto.toEntity(category, options, informationByDates));

        couponRepository.save(Coupon.builder()
                .name("2월 할인 3000")
                .discount(Ratio.valueOf(3000))
                .expirationDate(LocalDate.now().plusMonths(2))
                .month(2)
                .build());

        memberRepository.save(new Member("kim", new Email("zxczxc@naver.com"),"qwerqwer", Role.USER));
    }


    @Test
    public void saveTest() {
        Product product = productRepository.findAll().get(0);
        Long couponId = couponRepository.findAll().get(0).getId();
        Long memberId = memberRepository.findAll().get(0).getId();

        ReservationSaveDto reservationSaveDto = ReservationSaveDto.builder()
                .couponId(couponId)
                .email("zxczxc@naver.com")
                .memberId(memberId)
                .optionCount1(2)
                .optionCount2(1)
                .optionCount3(3)
                .productId(product.getId())
                .selectedDate(LocalDateTime.now())
                .tel("01012341234")
                .build();

        List<Option> options = product.getOptions();
        List<ReservationOption> reservationOptions = createReservationOptions(options, reservationSaveDto);

        int totalCount = getTotalCount(reservationOptions);
        minusQuantity(product, reservationSaveDto.getSelectedDate(), totalCount);

        Coupon coupon = couponRepository.findById(couponId).get();
        Ratio discount = coupon.getDiscount();
        int totalPrice = getTotalPrice(reservationOptions);
        Long finalPrice = discount.of(Money.wons(totalPrice)).longValue();
        reservationRepository.save(reservationSaveDto.toEntity(product, reservationOptions, finalPrice));

    }



    @Transactional
    public void minusQuantity(Product product, LocalDateTime selectedDate, int totalCount) {
        LocalDateTime start = product.getStartDate().atStartOfDay();
        int idx = (int) Duration.between(start, selectedDate).toDays()-1;
        CountByDate countByDate = product.getCountByDates().get(idx);
        countByDate.minusQuantity(totalCount);
    }
}*/
