package kr.team.ticketing.support.utils;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static kr.team.ticketing.support.utils.DateTimeUtils.formatToLocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeUtilsTest {
    @Test
    public void createDateTime(){
        LocalDateTime dateTime = DateTimeUtils.createDateTime("2020-02-07 12:22");
        assertThat(dateTime.getYear()).isEqualTo(2020);
        assertThat(dateTime.getMonthValue()).isEqualTo(2);
        assertThat(dateTime.getDayOfMonth()).isEqualTo(7);
        assertThat(dateTime.getHour()).isEqualTo(12);
        assertThat(dateTime.getMinute()).isEqualTo(22);
    }
    @Test
    public void stringToLocalDateTest() throws Exception {
        //given
        String now = "2020-03-03";
        //when
        LocalDate localDate = formatToLocalDate(now);
        //then
        System.out.println(localDate);
        assertThat(localDate).isEqualTo(LocalDate.of(2020, 3, 3));
    }
}