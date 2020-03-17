package springboot.jpa.booking.web.coupon.controller;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import springboot.jpa.booking.core.domain.coupon.Coupon;
import springboot.jpa.booking.core.domain.coupon.CouponRepository;
import springboot.jpa.booking.core.domain.generic.money.Ratio;
import springboot.jpa.booking.support.utils.ControllerTestSupporter;
import springboot.jpa.booking.web.coupon.dto.CouponRequestDto;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CouponApiControllerTest extends ControllerTestSupporter {

    private final String url = "http://localhost:" + port + "/api/v2/coupon";

    @Autowired
    CouponRepository couponRepository;

    @BeforeEach
    public void setUp() {
        couponRepository.save(Coupon.builder()
                            .name("3월마지막할인쿠폰")
                            .month(3)
                            .expirationDate(LocalDate.of(2020, 3, 30))
                            .discount(Ratio.valueOf(5000))
                            .build());
    }

    @AfterEach
    public void cleanUp() {
        couponRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("쿠폰유효기간체크")
    @Order(2)
    public void checkCouponExpirationDate() throws Exception {
        //given
        Long couponId = couponRepository.findAll().get(0).getId();
        int totalPrice = 100000;
        //when & then
        mockMvc.perform(post(url+"/"+couponId+"/"+totalPrice))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("coupon/check"));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("쿠폰 생성")
    @Order(1)
    public void couponSave() throws Exception {
        //given
        CouponRequestDto dto = CouponRequestDto.builder()
                .expirationDate(LocalDate.now().plusDays(10))
                .month(3)
                .name("3월 마지막 할인 쿠폰")
                .rate(10000)
                .build();
        //when & then
        mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("coupon/save",
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("쿠폰이름"),
                                fieldWithPath("month").type(JsonFieldType.NUMBER).description("달"),
                                fieldWithPath("rate").type(JsonFieldType.NUMBER).description("할인률"),
                                fieldWithPath("expirationDate").type(JsonFieldType.STRING).description("사용기한날짜")
                        )
                ));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("쿠폰 수정")
    @Order(3)
    public void couponUpdate() throws Exception {
        //given
        CouponRequestDto dto = CouponRequestDto.builder()
                .expirationDate(LocalDate.now().plusDays(10))
                .month(8)
                .name("8월 마지막 할인 쿠폰")
                .rate(8888)
                .build();
        Long couponId = couponRepository.findAll().get(0).getId();
        //when & then
        mockMvc.perform(put(url+"/"+couponId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("coupon/update",
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("쿠폰이름"),
                                fieldWithPath("month").type(JsonFieldType.NUMBER).description("달"),
                                fieldWithPath("rate").type(JsonFieldType.NUMBER).description("할인률"),
                                fieldWithPath("expirationDate").type(JsonFieldType.STRING).description("사용기한날짜")
                        )
                ));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("쿠폰 삭제")
    @Order(4)
    public void couponDelete() throws Exception {
        //given
        Long couponId = couponRepository.findAll().get(0).getId();
        //when & then
        mockMvc.perform(delete(url+"/"+couponId))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("coupon/delete"));
    }
}