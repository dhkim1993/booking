package kr.team.ticketing;

import kr.team.ticketing.core.domain.category.Category;
import kr.team.ticketing.core.domain.category.CategoryRepository;
import kr.team.ticketing.core.domain.comment.Comment;
import kr.team.ticketing.core.domain.comment.CommentRepository;
import kr.team.ticketing.core.domain.coupon.Coupon;
import kr.team.ticketing.core.domain.coupon.CouponRepository;
import kr.team.ticketing.core.domain.generic.money.Ratio;
import kr.team.ticketing.core.domain.product.InfoByDate;
import kr.team.ticketing.core.domain.product.Option;
import kr.team.ticketing.core.domain.product.repository.ProductRepository;
import kr.team.ticketing.service.product.InfoByDateService;
import kr.team.ticketing.web.product.dto.ProductSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

import static kr.team.ticketing.core.domain.product.Option.createOptions;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        /*initService.category();
        initService.coupon();
        initService.product();
        initService.comment();*/
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService {

        private final CategoryRepository categoryRepository;
        private final ProductRepository productRepository;
        private final CouponRepository couponRepository;
        private final InfoByDateService infoByDateService;
        private final CommentRepository commentRepository;


        public void category() {
            categoryRepository.save(new Category("뮤지컬"));
            categoryRepository.save(new Category("전통공연"));
            categoryRepository.save(new Category("연극"));
            categoryRepository.save(new Category("전시회"));
        }

        public void product() {
            for (int i = 0; i < 10; i++) {
                ProductSaveDto productSaveDto = ProductSaveDto.builder()
                        .categoryId(1L)
                        .name("레베카")
                        .description("최고의명작")
                        .startDate(LocalDate.of(2020, 3, 3))
                        .endDate(LocalDate.of(2020, 5, 10))
                        .count(50)
                        .location("서울")
                        .option1("VIP")
                        .option2("R")
                        .option3("S")
                        .price1(100000)
                        .price2(80000)
                        .price3(50000)
                        .firstTime("12:00")
                        .secondTime("15:00")
                        .thirdTime("18:00")
                        .fourthTime("21:00")
                        .build();
                List<Option> options = createOptions(productSaveDto);
                List<InfoByDate> infoByDates = infoByDateService.createInfoByDate(productSaveDto);

                productRepository.save(productSaveDto.toEntity(options, infoByDates));


                ProductSaveDto productSaveDto2 = ProductSaveDto.builder()
                        .categoryId(4L)
                        .name("반고흐 전시회")
                        .description("천재의 전시회")
                        .startDate(LocalDate.of(2020, 3, 3))
                        .endDate(LocalDate.of(2020, 4, 10))
                        .location("대학로")
                        .option1("성인")
                        .option2("대학생")
                        .option3("학생")
                        .price1(20000)
                        .price2(10000)
                        .price3(5000)
                        .build();
                List<Option> options2 = createOptions(productSaveDto2);
                productRepository.save(productSaveDto2.toEntity(options2));

                ProductSaveDto productSaveDto3 = ProductSaveDto.builder()
                        .categoryId(2L)
                        .name("풍물놀이")
                        .description("전통극!")
                        .startDate(LocalDate.of(2020, 5, 3))
                        .endDate(LocalDate.of(2020, 6, 3))
                        .location("부산")
                        .option1("성인")
                        .option2("대학생")
                        .option3("학생")
                        .price1(20000)
                        .price2(10000)
                        .price3(5000)
                        .build();
                List<Option> options3 = createOptions(productSaveDto3);
                productRepository.save(productSaveDto3.toEntity(options3));
            }
        }

        public void coupon() {
            for (int i = 0; i < 10; i++) {
                couponRepository.save(Coupon.builder()
                        .name("3월 초특가 할인쿠폰")
                        .discount(Ratio.valueOf(0.5))
                        .month(3)
                        .expirationDate(LocalDate.now().plusMonths(1))
                        .build());
            }
        }

        public void comment() {
            for (int i = 0; i < 10; i++) {
                commentRepository.save(Comment.builder()
                        .productId(1L)
                        .memberId(1L)
                        .memberName("김동현")
                        .grade(5)
                        .review("명작")
                        .build());
            }
            for (int i = 0; i < 6; i++) {
                commentRepository.save(Comment.builder()
                        .productId(1L)
                        .memberId(2L)
                        .memberName("망나니")
                        .grade(4)
                        .review("명작")
                        .build());
            }for (int i = 0; i < 3; i++) {
                commentRepository.save(Comment.builder()
                        .productId(1L)
                        .memberId(1L)
                        .memberName("김동현")
                        .grade(3)
                        .review("명작")
                        .build());
            }
        }
    }
}
