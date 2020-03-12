package springboot.jpa.booking.core.domain.comment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kimdonghyun on 12/03/2020.
 */
@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    public void setUp() {
        List<Comment> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Comment.builder()
                    .memberId(1L)
                    .memberName("포스틱")
                    .grade(5)
                    .productId(1L)
                    .review("재밋음")
                    .build());

            list.add(Comment.builder()
                    .memberId(2L)
                    .memberName("코로나")
                    .grade(4)
                    .productId(1L)
                    .review("재밋음")
                    .build());
        }
        commentRepository.saveAll(list);
    }

    @Test
    public void searchByGradeCommentsTest() throws Exception {
        //given
        int grade = 5;
        Long productId = 1L;
        //when
        Long gradeCnt = commentRepository.searchByGradeComments(grade, productId);
        //then
        assertThat(gradeCnt).isEqualTo(10);
    }

    @Test
    public void getTotalCntByProductIdTest() throws Exception {
        //given
        Long productId = 1L;
        //when
        Long totalCnt = commentRepository.getTotalCntByProductId(productId);
        //then
        assertThat(totalCnt).isEqualTo(20);
    }

    @Test
    public void getAvgTest() throws Exception {
        //given
        Long productId = 1L;
        //when
        Double avg = commentRepository.getAvg(1L);
        //then
        assertThat(avg.doubleValue()).isEqualTo(4.5);
    }
}