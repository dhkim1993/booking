package kr.team.ticketing.core.domain.comment;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Before
    public void setUp() {
        for (int i = 0; i < 10; i++) {
            Comment comment = Comment.builder()
                    .productId(1L)
                    .grade(4)
                    .review("꿀잼")
                    .build();
            commentRepository.save(comment);
        }
        for (int i = 0; i < 10; i++) {
            Comment comment = Comment.builder()
                    .productId(1L)
                    .grade(2)
                    .review("꿀잼")
                    .build();
            commentRepository.save(comment);
        }

    }
    @After
    public void cleanUp() {
        commentRepository.deleteAll();
    }

    @Test
    public void avgTest() throws Exception {
        //given

        //when
        Double avg = commentRepository.getAvg(1L);
        //then
        System.out.println();
        System.out.println(avg);
        assertThat(avg).isEqualTo(3);

    }
}