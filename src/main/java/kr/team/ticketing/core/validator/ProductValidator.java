package kr.team.ticketing.core.validator;

import kr.team.ticketing.core.domain.product.InfoByDate;
import kr.team.ticketing.core.domain.product.Option;
import kr.team.ticketing.core.domain.product.Product;
import kr.team.ticketing.core.domain.reservation.Reservation;
import kr.team.ticketing.core.domain.reservation.ReservationOption;
import kr.team.ticketing.service.product.InfoByDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductValidator {

    private final InfoByDateService infoByDateService;

    public boolean isEqOption(Product product, Reservation selectedData) {
        List<Option> options = product.getOptions();
        List<ReservationOption> reservationOptions = selectedData.getReservationOptions();
        List<String> showTimeList = Arrays.asList(options.get(0).getOptionName(),
                                                options.get(1).getOptionName(),
                                                options.get(2).getOptionName());
        for(ReservationOption option :  reservationOptions)
            if (!showTimeList.contains(option.getOptionName())) {
                throw new IllegalStateException("선택한 옵션이 변경되었습니다!");
            }
        return true;
    }

    public boolean isEqShowTime(Product product, Reservation selectedData) {
        String selectTime = selectedData.getSelectTime();
        InfoByDate selectDateInfo = infoByDateService.findSelectDateInfo(product.getInfoByDates(), selectedData.getSelectDate().toString());
        String firstTime = selectDateInfo.getFirstTime();
        String secondTime = selectDateInfo.getSecondTime();
        if(firstTime!= null && firstTime.equals(selectTime)) return true;
        else if(secondTime != null && secondTime.equals(selectTime)) return true;
        else if(selectDateInfo.getThirdTime().equals(selectTime)) return true;
        else if(selectDateInfo.getFourthTime().equals(selectTime)) return true;
        else throw new IllegalStateException("해당 공연타임이 없습니다!");
    }

    public boolean isEqLocation(Product product, Reservation selectedData) {
        if (product.getLocation().equals(selectedData.getLocation())) return true;
        else throw new IllegalStateException("공연장소가 변경되었습니다!");
    }

    public void checkQuantity(Product product, Reservation selectedData) {
        InfoByDate selectedDateInfo = infoByDateService.findSelectDateInfo(product.getInfoByDates(), selectedData.getSelectDate().toString());
        selectedDateInfo.minusQuantity(selectedData.getSelectTime(), selectedData.getTotalCnt());
    }

    public boolean isRunningDay(Product product, Reservation selectedData) {
        LocalDate selectDate = selectedData.getSelectDate();
        if (selectDate.isAfter(product.getStartDate().minusDays(1)) && selectDate.isBefore(product.getEndDate().plusDays(1)))
            return true;
        else throw new IllegalStateException("공연날짜가 아닙니다!");
    }
}
