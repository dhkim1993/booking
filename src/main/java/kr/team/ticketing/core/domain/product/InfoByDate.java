package kr.team.ticketing.core.domain.product;

import kr.team.ticketing.support.exception.NotEnoughStockException;
import kr.team.ticketing.web.product.dto.ProductSaveDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InfoByDate {

    @Id
    @GeneratedValue
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


    public static void updateInfoByDate(List<InfoByDate> infoByDates, ProductSaveDto productSaveDto) {
        LocalDate startDate = productSaveDto.getStartDate();
        int count = productSaveDto.getCount();
        for (InfoByDate infoByDate : infoByDates) {
            infoByDate.date=startDate;
            DayOfWeek dayOfWeek = startDate.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
                infoByDate.firstTime=productSaveDto.getFirstTime();
                infoByDate.secondTime=productSaveDto.getSecondTime();
                infoByDate.quantity1=count;
                infoByDate.quantity2=count;
            }
            infoByDate.thirdTime=productSaveDto.getThirdTime();
            infoByDate.fourthTime=productSaveDto.getFourthTime();
            infoByDate.quantity3=count;
            infoByDate.quantity4=count;
            startDate = startDate.plusDays(1);
        }
    }

    public void plusQuantity(String selectTime, int totalCnt) {
        if (selectTime.equals(firstTime)) {
            quantity1+=totalCnt;
        } else if (selectTime.equals(secondTime)) {
            quantity2+=totalCnt;
        } else if (selectTime.equals(thirdTime)) {
            quantity3+=totalCnt;
        } else {
            quantity4+=totalCnt;
        }
    }

    public void minusQuantity(String selectTime, int totalCnt) {
        if (selectTime.equals(firstTime)) {
            if (quantity1 - totalCnt >= 0) {
                quantity1 -= totalCnt;
            }else throw new NotEnoughStockException("need more stock");
        } else if (selectTime.equals(secondTime)) {
            if (quantity2 - totalCnt >= 0) {
                quantity2 -= totalCnt;
            }else throw new NotEnoughStockException("need more stock");
        } else if (selectTime.equals(thirdTime)) {
            if (quantity3 - totalCnt >= 0) {
                quantity3 -= totalCnt;
            }else throw new NotEnoughStockException("need more stock");
        } else {
            if (quantity4 - totalCnt >= 0) {
                quantity4 -= totalCnt;
            }else throw new NotEnoughStockException("need more stock");
        }
    }

    @Builder
    public InfoByDate(LocalDate date, String firstTime, String secondTime, String thirdTime, String fourthTime, int quantity1, int quantity2, int quantity3, int quantity4) {
        this.date = date;
        this.firstTime = firstTime;
        this.secondTime = secondTime;
        this.thirdTime = thirdTime;
        this.fourthTime = fourthTime;
        this.quantity1 = quantity1;
        this.quantity2 = quantity2;
        this.quantity3 = quantity3;
        this.quantity4 = quantity4;
    }
}
