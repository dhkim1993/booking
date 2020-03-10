package kr.team.ticketing.web.reservation.controller;


import kr.team.ticketing.service.product.InfoByDateService;
import kr.team.ticketing.service.product.ProductService;
import kr.team.ticketing.service.reservation.ReservationService;
import kr.team.ticketing.web.product.dto.InfoByDateResponseDto;
import kr.team.ticketing.web.reservation.dto.ReservationSaveDto;
import kr.team.ticketing.web.reservation.dto.SelectedDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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

