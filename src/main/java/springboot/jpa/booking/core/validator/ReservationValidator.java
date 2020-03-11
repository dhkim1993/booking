package springboot.jpa.booking.core.validator;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springboot.jpa.booking.core.domain.product.Product;
import springboot.jpa.booking.core.domain.reservation.Reservation;

@Component
@RequiredArgsConstructor
public class ReservationValidator {

    private final ProductValidator productValidator;

    public void forBookingValidation(Product product, Reservation selectedData) {
        Long categoryId = product.getCategoryId();
        if (categoryId != 4) { //전시회처럼 회차가 없는 상품들
            productValidator.checkQuantity(product, selectedData); //잔여좌석수량체크해서 빼줌
            productValidator.isEqShowTime(product, selectedData);//선택한 공연시간이 있는지 체크
            productValidator.isRunningDay(product, selectedData);//예약할려는 날짜가 공연 날짜인지 체크
        }
        productValidator.isEqLocation(product, selectedData);//공연장이 변경되었는지 체크
        productValidator.isEqOption(product, selectedData);//선택한 옵션의 변경사항이 있는지 체크
    }
}
