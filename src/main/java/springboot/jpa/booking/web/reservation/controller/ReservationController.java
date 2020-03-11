package springboot.jpa.booking.web.reservation.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.jpa.booking.config.auth.LoginUser;
import springboot.jpa.booking.config.auth.dto.SessionUser;
import springboot.jpa.booking.service.coupon.CouponService;
import springboot.jpa.booking.service.product.InfoByDateService;
import springboot.jpa.booking.service.product.ProductService;
import springboot.jpa.booking.service.reservation.ReservationService;
import springboot.jpa.booking.web.product.dto.ProductOptionResponseDto;
import springboot.jpa.booking.web.product.dto.ProductResponseDto;
import springboot.jpa.booking.web.reservation.dto.ReservationResponseDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation/")
public class ReservationController {

    private final ReservationService reservationService;
    private final ProductService productService;
    private final CouponService couponService;
    private final InfoByDateService infoByDateService;


    @GetMapping("calendar/{id}")
    public String calendar(@PathVariable("id") Long productId, Model model) {
        model.addAttribute("product", productService.findByIdResponseDto(productId));
        return "reservation/calendar";
    }

    @GetMapping("options/{id}")
    public String choiceOptionPage(@PathVariable("id") Long productId,
                                   @RequestParam("selectTime") String selectTime,
                                   @RequestParam("selectDate") String selectDate,
                                   @RequestParam("quantity") String quantity,
                                   Model model) {
        List<ProductOptionResponseDto> options = productService.optionResponseDtos(productId);
        model.addAttribute("option1", options.get(0));
        model.addAttribute("option2", options.get(1));
        model.addAttribute("option3", options.get(2));
        model.addAttribute("product", productService.findByIdResponseDto(productId));
        model.addAttribute("selectTime", selectTime);
        model.addAttribute("selectDate", selectDate);
        model.addAttribute("quantity", quantity);
        return "reservation/options";
    }

    @GetMapping("exhibition/{id}")
    public String noCalenderProduct(Model model, @PathVariable("id") Long productId) {
        ProductResponseDto productResponseDto = productService.findByIdResponseDto(productId);
        List<ProductOptionResponseDto> options = productService.optionResponseDtos(productId);
        model.addAttribute("product", productResponseDto);
        model.addAttribute("option1", options.get(0));
        model.addAttribute("option2", options.get(1));
        model.addAttribute("option3", options.get(2));
        return "reservation/exhibition";

    }
    @GetMapping("modify/{productId}/{reservationId}")
    public String modifyOption(@PathVariable("productId") Long productId,
                               @PathVariable("reservationId") Long reservationId,
                               Model model) {
        ProductResponseDto productDto = productService.findByIdResponseDto(productId);
        List<ProductOptionResponseDto> options = productService.optionResponseDtos(productId);
        ReservationResponseDto reservationDto = reservationService.getReservationDto(reservationId);
        if (productDto.getCategoryId() != 4) {
            int selectQuantity = infoByDateService.getSelectQuantity(productId, reservationDto.getSelectTime(), reservationDto.getSelectDate());
            model.addAttribute("selectTime", reservationDto.getSelectTime());
            model.addAttribute("selectDate", reservationDto.getSelectDate());
            model.addAttribute("quantity", selectQuantity);
        }
        model.addAttribute("product", productDto);
        model.addAttribute("option1", options.get(0));
        model.addAttribute("option2", options.get(1));
        model.addAttribute("option3", options.get(2));
        model.addAttribute("reservationId", reservationId);
        return "reservation/options";
    }

    @GetMapping("billing/{id}")
    public String payPage(@PathVariable("id") Long id,
                          @LoginUser SessionUser user,
                          Model model) {
        ReservationResponseDto reservationDto = reservationService.getReservationDto(id);
        Long memberId = user.getId();
        model.addAttribute("selectData", reservationDto);
        model.addAttribute("memberId", memberId);
        model.addAttribute("email", reservationDto.getEmail().getValue());
        model.addAttribute("coupons", couponService.getNotUsedCoupons(memberId));
        return "reservation/pay";
    }
}
