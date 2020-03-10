package kr.team.ticketing.core.domain.reservation;

import kr.team.ticketing.core.domain.product.Option;
import kr.team.ticketing.web.reservation.dto.SelectedDataDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationOption {

    @Id
    @GeneratedValue
    private Long id;

    private String optionName;
    private int price;
    private int count;

    public void optionUpdate(SelectedDataDto selectedDataDto) {

    }

    public static List<ReservationOption> createReservationOptions(List<Option> options, SelectedDataDto selectedDataDto) {
        List<ReservationOption> reservationOptions = new ArrayList<>();
        Option option1 = options.get(0);
        Option option2 = options.get(1);
        Option option3 = options.get(2);
        if (selectedDataDto.getOptionCount1() != 0) {
            reservationOptions.add(ReservationOption.builder()
                    .optionName(option1.getOptionName())
                    .price(option1.getPrice())
                    .count(selectedDataDto.getOptionCount1())
                    .build());
        }if (selectedDataDto.getOptionCount2() != 0) {
            reservationOptions.add(ReservationOption.builder()
                    .optionName(option2.getOptionName())
                    .price(option2.getPrice())
                    .count(selectedDataDto.getOptionCount2())
                    .build());
        }if (selectedDataDto.getOptionCount3() != 0) {
            reservationOptions.add(ReservationOption.builder()
                    .optionName(option3.getOptionName())
                    .price(option3.getPrice())
                    .count(selectedDataDto.getOptionCount3())
                    .build());
        }
        return reservationOptions;
    }

    @Builder
    public ReservationOption(String optionName, int price, int count) {
        this.optionName = optionName;
        this.price = price;
        this.count = count;
    }
}
