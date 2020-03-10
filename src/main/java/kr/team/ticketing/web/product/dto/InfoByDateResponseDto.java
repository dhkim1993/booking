package kr.team.ticketing.web.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class InfoByDateResponseDto {

    private Long id;
    private LocalDate date;
    private String firstTime;
    private String secondTime;
    private String thirdTime;
    private String fourthTime;
    private int quantity1;
    private int quantity2;
    private int quantity3;
    private int quantity4;
}
