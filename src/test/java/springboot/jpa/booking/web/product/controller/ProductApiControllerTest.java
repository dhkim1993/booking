package springboot.jpa.booking.web.product.controller;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import springboot.jpa.booking.core.domain.category.CategoryRepository;
import springboot.jpa.booking.core.domain.product.InfoByDate;
import springboot.jpa.booking.core.domain.product.Option;
import springboot.jpa.booking.core.domain.product.SearchCondition;
import springboot.jpa.booking.core.domain.product.repository.ProductRepository;
import springboot.jpa.booking.service.product.InfoByDateService;
import springboot.jpa.booking.support.utils.ControllerTestSupporter;
import springboot.jpa.booking.web.product.dto.ProductSaveDto;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductApiControllerTest extends ControllerTestSupporter {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    InfoByDateService infoByDateService;

    private final String url = "http://localhost"+port+"/api/v2/product";
    private final String adminUrl = "http://localhost"+port+"/api/v3/product";

    @BeforeEach
    public void setUp(){
        for (int i = 0; i < 15; i++) {
            ProductSaveDto dto = ProductSaveDto.builder()
                    .categoryId(1L)
                    .description("공연이다")
                    .endDate(LocalDate.now().plusDays(i))
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
    }

    @AfterEach
    public void cleanUp(){
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("컨디션_검색")
    public void searchByDynamicCondition() throws Exception {
        //given
        Long categoryId = 1L;
        SearchCondition searchCondition = SearchCondition.builder()
                .categoryId(categoryId)
                .location("서울")
                .month(3)
                .page(0)
                .size(10)
                .build();
        //when & then
        mockMvc.perform(post("/api/v1/product/list")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(searchCondition)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("product/search",
                        requestFields(
                                fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리id"),
                                fieldWithPath("location").type(JsonFieldType.STRING).description("공연위치"),
                                fieldWithPath("month").type(JsonFieldType.NUMBER).description("월"),
                                fieldWithPath("page").type(JsonFieldType.NUMBER).description("page"),
                                fieldWithPath("size").type(JsonFieldType.NUMBER).description("size")
                        )
                ));
    }


    @Test
    @DisplayName("상품생성")
    @WithMockUser(roles = "ADMIN")
    public void productSave() throws Exception {
        //given
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
        //when & then
        mockMvc.perform(post(adminUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("product/save",
                        requestFields(
                                fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리id"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("공연상세설명"),
                                fieldWithPath("endDate").type(JsonFieldType.STRING).description("공연종료날짜"),
                                fieldWithPath("startDate").type(JsonFieldType.STRING).description("공연시작날짜"),
                                fieldWithPath("location").type(JsonFieldType.STRING).description("공연위치"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("공연이름"),
                                fieldWithPath("count").type(JsonFieldType.NUMBER).description("좌석수"),
                                fieldWithPath("option1").type(JsonFieldType.STRING).description("option1"),
                                fieldWithPath("option2").type(JsonFieldType.STRING).description("option2"),
                                fieldWithPath("option3").type(JsonFieldType.STRING).description("option3"),
                                fieldWithPath("price1").type(JsonFieldType.NUMBER).description("price1"),
                                fieldWithPath("price2").type(JsonFieldType.NUMBER).description("price2"),
                                fieldWithPath("price3").type(JsonFieldType.NUMBER).description("price3"),
                                fieldWithPath("firstTime").type(JsonFieldType.STRING).description("1회차"),
                                fieldWithPath("secondTime").type(JsonFieldType.STRING).description("2회차"),
                                fieldWithPath("thirdTime").type(JsonFieldType.STRING).description("3회차"),
                                fieldWithPath("fourthTime").type(JsonFieldType.STRING).description("4회차")
                        )
                ));
    }


    @Test
    @DisplayName("상품수정")
    @WithMockUser(roles = "ADMIN")
    public void productUpdate() throws Exception {
        //given
        ProductSaveDto dto = ProductSaveDto.builder()
                .categoryId(1L)
                .description("이 공연 꼭봐라!!")
                .endDate(LocalDate.now().plusMonths(1))
                .startDate(LocalDate.now())
                .location("서울")
                .name("맨오브라만차")
                .count(20)
                .option1("성인")
                .option2("학생할인")
                .option3("미취학무료(이벤트)")
                .price1(100000)
                .price2(50000)
                .price3(0)
                .firstTime("10:00")
                .secondTime("13:00")
                .thirdTime("17:00")
                .fourthTime("20:00")
                .build();

        Long id = productRepository.findAll().get(0).getId();
        //when & then
        mockMvc.perform(put(adminUrl+"/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("product/update",
                        requestFields(
                                fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리id"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("공연상세설명"),
                                fieldWithPath("endDate").type(JsonFieldType.STRING).description("공연종료날짜"),
                                fieldWithPath("startDate").type(JsonFieldType.STRING).description("공연시작날짜"),
                                fieldWithPath("location").type(JsonFieldType.STRING).description("공연위치"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("공연이름"),
                                fieldWithPath("count").type(JsonFieldType.NUMBER).description("좌석수"),
                                fieldWithPath("option1").type(JsonFieldType.STRING).description("option1"),
                                fieldWithPath("option2").type(JsonFieldType.STRING).description("option2"),
                                fieldWithPath("option3").type(JsonFieldType.STRING).description("option3"),
                                fieldWithPath("price1").type(JsonFieldType.NUMBER).description("price1"),
                                fieldWithPath("price2").type(JsonFieldType.NUMBER).description("price2"),
                                fieldWithPath("price3").type(JsonFieldType.NUMBER).description("price3"),
                                fieldWithPath("firstTime").type(JsonFieldType.STRING).description("1회차"),
                                fieldWithPath("secondTime").type(JsonFieldType.STRING).description("2회차"),
                                fieldWithPath("thirdTime").type(JsonFieldType.STRING).description("3회차"),
                                fieldWithPath("fourthTime").type(JsonFieldType.STRING).description("4회차")
                        )
                ));
    }


    @Test
    @DisplayName("상품삭제")
    @WithMockUser(roles = "ADMIN")
    public void productDelete() throws Exception {
        //given
        Long id = productRepository.findAll().get(0).getId();
        //when & then
        mockMvc.perform(delete(adminUrl+"/"+id))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("product/update"));
    }
}