package kr.team.ticketing.service.product;

import kr.team.ticketing.core.domain.product.InfoByDate;
import kr.team.ticketing.core.domain.product.Product;
import kr.team.ticketing.core.domain.product.repository.ProductRepository;
import kr.team.ticketing.service.converter.InfoByDateConverter;
import kr.team.ticketing.web.product.dto.InfoByDateResponseDto;
import kr.team.ticketing.web.product.dto.ProductSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static kr.team.ticketing.support.utils.DateTimeUtils.formatToLocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InfoByDateService {

    private final ProductRepository productRepository;
    private final InfoByDateConverter infoByDateConverter;


    public InfoByDateResponseDto getWeekEndDay(Long productId) {
        return infoByDateConverter.convert(findWeekEndDay(productId));
    }

    public InfoByDate findWeekEndDay(Long productId) {
        Product product = productRepository.findById(productId).get();
        List<InfoByDate> infoByDates = product.getInfoByDates();
        for (InfoByDate infoByDate : infoByDates) {
            if(infoByDate.getFirstTime()!=null) return infoByDate;
        }
        return infoByDates.get(0);
    }

    public InfoByDateResponseDto getSelectDateInfoDto(Long productId, String date) {
        List<InfoByDate> infoByDates = productRepository.findById(productId).get().getInfoByDates();
        return infoByDateConverter.convert(findSelectDateInfo(infoByDates, date));
    }

    public InfoByDate findSelectDateInfo(List<InfoByDate> infoByDates, String date) {
        LocalDate localDate = formatToLocalDate(date);
        for (InfoByDate infoByDate : infoByDates) {
            if (infoByDate.getDate().equals(localDate)) {
                return infoByDate;
            }
        }
        throw new IllegalArgumentException("해당 날짜의 데이터가 없습니다!");
    }

    public int getSelectQuantity(Long productId, String selectTime, LocalDate selectDate) {
        List<InfoByDate> infoByDates = productRepository.findById(productId).get().getInfoByDates();
        InfoByDate selectDateInfo = findSelectDateInfo(infoByDates, selectDate.toString());
        return findSelectQuantity(selectDateInfo, selectTime);
    }

    public int findSelectQuantity(InfoByDate infoByDate, String selectTime) {
        String firstTime = infoByDate.getFirstTime();
        String secondTime = infoByDate.getSecondTime();
        String thirdTime = infoByDate.getThirdTime();
        if (firstTime!=null && firstTime.equals(selectTime)) {
            return infoByDate.getQuantity1();
        } else if (secondTime!=null && secondTime.equals(selectTime)) {
            return infoByDate.getQuantity2();
        } else if (thirdTime.equals(selectTime)) {
            return infoByDate.getQuantity3();
        } else return infoByDate.getQuantity4();
    }

    public  Long getRuntime(ProductSaveDto productSaveDto) {
        LocalDateTime start = productSaveDto.getStartDate().atStartOfDay();
        LocalDateTime end = productSaveDto.getEndDate().atStartOfDay();
        return Duration.between(start,end).toDays();
    }

    @Transactional
    public  List<InfoByDate> createInfoByDate(ProductSaveDto productSaveDto) {
        List<InfoByDate> infoByDates = new ArrayList<>();
        long runtime = getRuntime(productSaveDto)+1;
        int count = productSaveDto.getCount();
        LocalDate startDate = productSaveDto.getStartDate();
        for (int i = 0; i < runtime; i++) {
            DayOfWeek dayOfWeek = startDate.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
                infoByDates.add(InfoByDate.builder()
                        .date(startDate)
                        .quantity1(count)
                        .quantity2(count)
                        .quantity3(count)
                        .quantity4(count)
                        .firstTime(productSaveDto.getFirstTime())
                        .secondTime(productSaveDto.getSecondTime())
                        .thirdTime(productSaveDto.getThirdTime())
                        .fourthTime(productSaveDto.getFourthTime())
                        .build());
            } else {
                infoByDates.add(InfoByDate.builder()
                        .date(startDate)
                        .quantity3(count)
                        .quantity4(count)
                        .thirdTime(productSaveDto.getThirdTime())
                        .fourthTime(productSaveDto.getFourthTime())
                        .build());
            }
            startDate = startDate.plusDays(1);
        }
        return infoByDates;
    }
}
