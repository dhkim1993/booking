package springboot.jpa.booking.web.comment.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import springboot.jpa.booking.config.auth.dto.SessionUser;
import springboot.jpa.booking.core.domain.comment.CommentRepository;
import springboot.jpa.booking.support.utils.ControllerTestSupporter;
import springboot.jpa.booking.web.comment.dto.CommentRequestDto;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommentApiControllerTest extends ControllerTestSupporter {

    private final String url = "http://localhost:" + port + "/api/v2/comment";

    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 2; i++) {
            SessionUser user = SessionUser.builder()
                    .email("abc")
                    .id(3L)
                    .name("kim")
                    .picture("qwe.jpg")
                    .build();
            CommentRequestDto dto = CommentRequestDto.builder()
                    .review("너무재밌다")
                    .productId(1L)
                    .grade(5)
                    .build();
            commentRepository.save(dto.toEntity(user));
        }
    }

    @AfterEach
    public void cleanUp() {
        commentRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("코멘트 수정")
    @Order(1)
    public void commentUpdate() throws Exception {
        //given
        Long commentId = commentRepository.findAll().get(0).getId();
        CommentRequestDto dto = CommentRequestDto.builder()
                .review("너무재밌다")
                .productId(1L)
                .grade(5)
                .build();

        //when & then
        mockMvc.perform(put(url + "/" + commentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("comment/update",
                        requestFields(
                                fieldWithPath("review").type(JsonFieldType.STRING).description("한줄평"),
                                fieldWithPath("productId").type(JsonFieldType.NUMBER).description("상품id"),
                                fieldWithPath("grade").type(JsonFieldType.NUMBER).description("평점")
                        )
                ));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("코멘트 삭제")
    @Order(2)
    public void commentDelete() throws Exception {
        //given
        Long commentId = commentRepository.findAll().get(0).getId();
        //when & then
        mockMvc.perform(delete(url + "/" + commentId))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("comment/delete"));
    }
}

/*
@Test
    @WithMockUser(roles="USER")
    @DisplayName("코멘트 생성")
    public void commentSave() throws Exception {
        //given
        SessionUser user = SessionUser.builder()
                .email("abc")
                .id(3L)
                .name("kim")
                .picture("qwe.jpg")
                .build();
        CommentRequestDto dto = CommentRequestDto.builder()
                .review("너무재밌다")
                .productId(1L)
                .grade(5)
                .build();

        //when & then
        mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("comment/save",
                        requestFields(
                                fieldWithPath("review").type(JsonFieldType.STRING).description("한줄평"),
                                fieldWithPath("productId").type(JsonFieldType.NUMBER).description("상품id"),
                                fieldWithPath("grade").type(JsonFieldType.NUMBER).description("평점"),
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원id"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("picture").type(JsonFieldType.STRING).description("프사")
                        )
                ));
    }
 */