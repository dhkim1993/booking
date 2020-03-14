package springboot.jpa.booking.core.domain.comment;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kimdonghyun on 12/03/2020.
 */

@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    EntityManager em;

    JPQLQueryFactory queryFactory;

    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    public void before() {

        queryFactory = new JPAQueryFactory(em);

        for (int i = 0; i < 10; i++) {
            em.persist(Comment.builder()
                    .memberId(1L)
                    .memberName("포스틱")
                    .grade(5)
                    .productId(1L)
                    .review("재밋음")
                    .build());

            em.persist(Comment.builder()
                    .memberId(2L)
                    .memberName("코로나")
                    .grade(4)
                    .productId(1L)
                    .review("재밋음")
                    .build());
        }
    }

    @AfterEach
    public void after() {
        em.flush();
        em.clear();
        em.close();
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