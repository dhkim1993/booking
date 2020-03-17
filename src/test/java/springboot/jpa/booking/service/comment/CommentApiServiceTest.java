package springboot.jpa.booking.service.comment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.jpa.booking.config.auth.dto.SessionUser;
import springboot.jpa.booking.core.domain.comment.Comment;
import springboot.jpa.booking.core.domain.comment.CommentRepository;
import springboot.jpa.booking.web.comment.dto.CommentRequestDto;
import springboot.jpa.booking.web.comment.dto.CommentResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommentApiServiceTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;

    @BeforeEach
    public void before() {
        for (int i = 0; i < 3; i++) {
            CommentRequestDto commentRequestDto =  CommentRequestDto.builder()
                    .grade(5)
                    .productId(1L)
                    .review("졸잼")
                    .build();
            SessionUser user = new SessionUser(1L, "kim", "aa@naver.com", "asdasd");
            commentRepository.save(commentRequestDto.toEntity(user)).getId();
        }
    }

    @AfterEach
    public void after() {
        commentRepository.deleteAll();
    }

    @Test
    public void findAllDtoByProductId() throws Exception {
        //given
        Long productId = 1L;
        //when
        List<CommentResponseDto> allDtoByProductId = commentService.findAllDtoByProductId(productId);
        CommentResponseDto commentResponseDto = allDtoByProductId.get(0);
        //then
        System.out.println(allDtoByProductId.size());
        assertThat(commentResponseDto.getGrade()).isEqualTo(5);
        assertThat(commentResponseDto.getId()).isEqualTo(10);
        assertThat(commentResponseDto.getMemberId()).isEqualTo(1L);
        assertThat(commentResponseDto.getMemberName()).isEqualTo("kim");
    }

    @Test
    public void save() throws Exception {
        //given
        //when
        Comment comment = commentRepository.findAll().get(0);
        //then
        assertThat(comment.getGrade()).isEqualTo(5);
        assertThat(comment.getMemberId()).isEqualTo(1L);
        assertThat(comment.getMemberName()).isEqualTo("kim");

    }
    @Test
    public void update() throws Exception {
        //given
        Comment comment = commentRepository.findAll().get(0);
        //when
        comment.update(3,"노잼");
        commentRepository.save(comment);
        //then
        Comment afterUpdate = commentRepository.findAll().get(0);
        assertThat(afterUpdate.getReview()).isEqualTo("노잼");
        assertThat(afterUpdate.getGrade()).isEqualTo(3);

    }
    @Test
    public void delete() throws Exception {
        //given
        Comment comment = commentRepository.findAll().get(0);
        //when
        commentRepository.delete(comment);
        List<Comment> all = commentRepository.findAll();
        //then
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    public Long update(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.update(commentRequestDto.getGrade(), commentRequestDto.getReview());
        return commentId;
    }
    @Test
    public Long delete(Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }

}