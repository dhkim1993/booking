package springboot.jpa.booking.web.reservation.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.jpa.booking.service.product.InfoByDateService;
import springboot.jpa.booking.service.product.ProductService;
import springboot.jpa.booking.service.reservation.ReservationService;
import springboot.jpa.booking.web.product.dto.InfoByDateResponseDto;
import springboot.jpa.booking.web.reservation.dto.ReservationSaveDto;
import springboot.jpa.booking.web.reservation.dto.SelectedDataDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/")
public class ReservationApiController {

    private final ProductService productService;
    private final ReservationService reservationService;
    private final InfoByDateService infoByDateService;

    @GetMapping("calendar/{dateText}/{id}")
    public InfoByDateResponseDto infoByDateResponse(@PathVariable("dateText") String date,
                                                    @PathVariable("id") Long productId) {
        return infoByDateService.getSelectDateInfoDto(productId, date);
    }

    @PostMapping("pay")
    public Long selectDataSaveAndThenPayPage(@RequestBody SelectedDataDto selectedDataDto) {
        return reservationService.selectedDataSave(selectedDataDto);
    }

    @PutMapping("pay")
    public Long selectedDataUpdate(@RequestBody SelectedDataDto selectedDataDto) {
        return reservationService.selectedDataUpdate(selectedDataDto);
    }

    @PostMapping("reservation")
    public String reservation(@RequestBody ReservationSaveDto reservationSaveDto) {
        reservationService.reservationSave(reservationSaveDto);
        return "에약성공!";
    }

    @PutMapping("reservation/{id}")
    public String cancel(@PathVariable Long id) {
        reservationService.cancel(id);
        return "취소되었습니다.";
    }
}

