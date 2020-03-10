package kr.team.ticketing.domain.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class OptionTest {


    @Test
    public void asds() {
        LocalDateTime localDateTime = LocalDate.of(2020, 2, 10).atStartOfDay();
        System.out.println("                "+ Duration.between(LocalDateTime.now(),localDateTime ).toDays());
    }
}