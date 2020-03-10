package kr.team.ticketing.support.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateTimeUtils {
    public static final String PLAYING_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String ONLY_DATE_FORMAT = "yyyy-MM-dd";
    public static final String H_M = "HH:mm";

    public static LocalDateTime createDateTime(String dateTime) {
        return createDateTime(dateTime, PLAYING_TIME_FORMAT);
    }
    public static LocalDate formatToLocalDate(String date) {
        if (date==null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ONLY_DATE_FORMAT);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDateTime createDateTime(String dateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static String format(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PLAYING_TIME_FORMAT);
        return dateTime.format(formatter);
    }

    public static List<Integer> fromNowOnFourMonth() {
        List<Integer> months = new ArrayList<>();
        int now = LocalDate.now().getMonth().getValue();
        for (int i = now; i < now+4; i++) {
            months.add(i);
        }
        return months;
    }
}
