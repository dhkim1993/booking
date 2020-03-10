package kr.team.ticketing.web.coupon.controller;

import kr.team.ticketing.service.coupon.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public String coupon_save() {
        return "/coupon/create";
    }

    @GetMapping("/{id}")
    public String coupon_update(Model model, @PathVariable("id") Long id) {
        model.addAttribute("coupon", couponService.findByIdResponseDto(id));
        return "/coupon/update";
    }
}
