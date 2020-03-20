package springboot.jpa.booking.web.reservation.controller;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import springboot.jpa.booking.core.domain.coupon.Coupon;
import springboot.jpa.booking.core.domain.coupon.CouponRepository;
import springboot.jpa.booking.core.domain.generic.money.Ratio;
import springboot.jpa.booking.core.domain.product.InfoByDate;
import springboot.jpa.booking.core.domain.product.Option;
import springboot.jpa.booking.core.domain.product.Product;
import springboot.jpa.booking.core.domain.product.repository.ProductRepository;
import springboot.jpa.booking.core.domain.reservation.Reservation;
import springboot.jpa.booking.core.domain.reservation.ReservationOption;
import springboot.jpa.booking.core.domain.reservation.ReservationRepository;
import springboot.jpa.booking.service.product.InfoByDateService;
import springboot.jpa.booking.support.utils.ControllerTestSupporter;
import springboot.jpa.booking.web.product.dto.ProductSaveDto;
import springboot.jpa.booking.web.reservation.dto.ReservationSaveDto;
import springboot.jpa.booking.web.reservation.dto.SelectedDataDto;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static springboot.jpa.booking.core.domain.reservation.ReservationOption.createReservationOptions;


class ReservationApiControllerTest extends ControllerTestSupporter {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InfoByDateService infoByDateService;
    @Autowired
    CouponRepository couponRepository;

    private final String url = "http://localhost:"+port+"/api/v2";

    @BeforeEach
    public void setUp(){
        ProductSaveDto dto = ProductSaveDto.builder()
                .categoryId(1L)
                .description("공연이다")
                .endDate(LocalDate.now().plusMonths(2))
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
        Long productId = productRepository.save(dto.toEntity(options, infoByDate)).getId();

        SelectedDataDto selectedDataDto = SelectedDataDto.builder()
                .productId(productId)
                .memberId(1L)
                .productName("맨오브라만차")
                .memberName("dong")
                .email("qasdzxc@naver.com")
                .tel("01012341234")
                .selectDate("2020-03-30")
                .selectTime("17:00")
                .location("서울")
                .optionName1("평일할인")
                .optionName2("주말할인")
                .optionName3("이벤트가")
                .optionCount1(1)
                .optionCount2(1)
                .optionCount3(1)
                .totalCnt(6)
                .totalPrice(35000)
                .finalPrice(null)
                .reservationId(null)
                .build();
        List<ReservationOption> reservationOptions = createReservationOptions(options, selectedDataDto);
        reservationRepository.save(selectedDataDto.toEntity(reservationOptions));

        couponRepository.save(Coupon.builder()
                .discount(Ratio.valueOf(0.5))
                .expirationDate(LocalDate.of(2020, 5,10))
                .month(3)
                .name("이번달이벤트쿠폰")
                .build());
    }

    @AfterEach
    public void cleanUp(){
        reservationRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("선택한_날짜_데이터_응답")
    public void infoByDateResponse() throws Exception {
        //given
        String date = "2020-03-20";
        Long productId = productRepository.findAll().get(0).getId();
        //when & then
        mockMvc.perform(get(url+"/calendar/"+date+"/"+productId))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("reservation/calendar"));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("최종결제전_선택한_데이터_저장")
    @Order(1)
    public void selectDataSaveAndThenPayPage() throws Exception {
        //given
        Long productId = productRepository.findAll().get(0).getId();
        SelectedDataDto dto = SelectedDataDto.builder()
                .productId(productId)
                .memberId(null)
                .productName("맨오브라만차")
                .memberName("dong")
                .email("zxc@naver.com")
                .tel("01012341234")
                .selectDate("2020-03-30")
                .selectTime("17:00")
                .location("서울")
                .optionName1("평일할인")
                .optionName2("주말할인")
                .optionName3("이벤트가")
                .optionCount1(1)
                .optionCount2(1)
                .optionCount3(1)
                .totalCnt(6)
                .totalPrice(35000)
                .finalPrice(null)
                .reservationId(null)
                .build();
        //when & then
        mockMvc.perform(post(url + "/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("reservation/select-data-save",
                        requestFields(
                                fieldWithPath("productId").type(JsonFieldType.NUMBER).description("상품id"),
                                fieldWithPath("memberId").type(JsonFieldType.NULL).description("회원id"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("email"),
                                fieldWithPath("tel").type(JsonFieldType.STRING).description("tel"),
                                fieldWithPath("selectDate").type(JsonFieldType.STRING).description("선택날짜"),
                                fieldWithPath("selectTime").type(JsonFieldType.STRING).description("선택회차"),
                                fieldWithPath("productName").type(JsonFieldType.STRING).description("공연이름"),
                                fieldWithPath("memberName").type(JsonFieldType.STRING).description("회원이름"),
                                fieldWithPath("location").type(JsonFieldType.STRING).description("공연장소"),
                                fieldWithPath("optionName1").type(JsonFieldType.STRING).description("option1"),
                                fieldWithPath("optionName2").type(JsonFieldType.STRING).description("option2"),
                                fieldWithPath("optionName3").type(JsonFieldType.STRING).description("option3"),
                                fieldWithPath("optionCount1").type(JsonFieldType.NUMBER).description("optionCnt1"),
                                fieldWithPath("optionCount2").type(JsonFieldType.NUMBER).description("optionCnt2"),
                                fieldWithPath("optionCount3").type(JsonFieldType.NUMBER).description("optionCnt3"),
                                fieldWithPath("totalCnt").type(JsonFieldType.NUMBER).description("총 예약 티켓수"),
                                fieldWithPath("totalPrice").type(JsonFieldType.NUMBER).description("총 가격"),
                                fieldWithPath("reservationId").type(JsonFieldType.NULL).description("예약id"),
                                fieldWithPath("finalPrice").type(JsonFieldType.NULL).description("할인후 최종결제가격")
                        )
                ));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("선택한_데이터_수정")
    public void selectedDataUpdate() throws Exception {
        //given
        Product product = productRepository.findAll().get(0);
        Reservation reservation = reservationRepository.findAll().get(0);
        SelectedDataDto dto = SelectedDataDto.builder()
                .productId(product.getId())
                .memberId(1L)
                .productName("맨오브라만차")
                .memberName("dong")
                .email("qasdzxc@naver.com")
                .tel("01012341234")
                .selectDate("2020-03-30")
                .selectTime("17:00")
                .location("서울")
                .optionName1("평일할인")
                .optionName2("주말할인")
                .optionName3("이벤트가")
                .optionCount1(1)
                .optionCount2(1)
                .optionCount3(1)
                .totalCnt(6)
                .totalPrice(35000)
                .finalPrice(null)
                .reservationId(reservation.getId())
                .build();

        //when & then
        mockMvc.perform(put(url + "/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("reservation/select-data-update",
                        requestFields(
                                fieldWithPath("productId").type(JsonFieldType.NUMBER).description("상품id"),
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원id"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("email"),
                                fieldWithPath("tel").type(JsonFieldType.STRING).description("tel"),
                                fieldWithPath("selectDate").type(JsonFieldType.STRING).description("선택날짜"),
                                fieldWithPath("selectTime").type(JsonFieldType.STRING).description("선택회차"),
                                fieldWithPath("productName").type(JsonFieldType.STRING).description("공연이름"),
                                fieldWithPath("memberName").type(JsonFieldType.STRING).description("회원이름"),
                                fieldWithPath("location").type(JsonFieldType.STRING).description("공연장소"),
                                fieldWithPath("optionName1").type(JsonFieldType.STRING).description("option1"),
                                fieldWithPath("optionName2").type(JsonFieldType.STRING).description("option2"),
                                fieldWithPath("optionName3").type(JsonFieldType.STRING).description("option3"),
                                fieldWithPath("optionCount1").type(JsonFieldType.NUMBER).description("optionCnt1"),
                                fieldWithPath("optionCount2").type(JsonFieldType.NUMBER).description("optionCnt2"),
                                fieldWithPath("optionCount3").type(JsonFieldType.NUMBER).description("optionCnt3"),
                                fieldWithPath("totalCnt").type(JsonFieldType.NUMBER).description("총 예약 티켓수"),
                                fieldWithPath("totalPrice").type(JsonFieldType.NUMBER).description("총 가격"),
                                fieldWithPath("reservationId").type(JsonFieldType.NUMBER).description("예약id"),
                                fieldWithPath("finalPrice").type(JsonFieldType.NULL).description("할인후 최종결제가격")
                        )
                ));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("쿠폰할인후_최종예약")
    public void reservation() throws Exception {
        //given
        Reservation reservation = reservationRepository.findAll().get(0);
        Long couponId = couponRepository.findAll().get(0).getId();
        ReservationSaveDto dto = ReservationSaveDto.builder()
                .couponId(couponId)
                .finalPrice(100000L)
                .memberId(1L)
                .selectedDataId(reservation.getId())
                .build();
        //when & then
        mockMvc.perform(post(url + "/reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("reservation/reservation",
                        requestFields(
                                fieldWithPath("couponId").type(JsonFieldType.NUMBER).description("쿠폰id"),
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원id"),
                                fieldWithPath("finalPrice").type(JsonFieldType.NUMBER).description("쿠폰적용후 최종가격"),
                                fieldWithPath("selectedDataId").type(JsonFieldType.NUMBER).description("최종예약전 데이터 id")
                        )
                ));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("예약취소")
    public void cancel() throws Exception {
        //given
        Long reservationId = reservationRepository.findAll().get(0).getId();

        //when & then
        mockMvc.perform(put(url + "/reservation/"+reservationId))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("reservation/cancel"));
    }

}