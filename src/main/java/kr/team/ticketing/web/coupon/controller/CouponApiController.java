package kr.team.ticketing.web.coupon.controller;


import kr.team.ticketing.config.auth.LoginUser;
import kr.team.ticketing.config.auth.dto.SessionUser;
import kr.team.ticketing.service.coupon.CouponApiService;
import kr.team.ticketing.web.coupon.dto.CouponRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/coupon")
public class CouponApiController {

    private final CouponApiService couponApiService;

    @PutMapping("/month")
    public String addThisMonthCoupons(@LoginUser SessionUser user) {
        return couponApiService.addThisMonthCoupons(user.getId());
    }

    @PostMapping("/{id}/{totalPrice}")
    public Long checkCouponExpirationDate(@PathVariable("id") Long couponId,
                            @PathVariable("totalPrice") int totalPrice) {
        return couponApiService.isCanUseCoupon(couponId, totalPrice);
    }

    @PostMapping
    public String save(@RequestBody CouponRequestDto couponRequestDto) {
        couponApiService.save(couponRequestDto);
        return "save success!";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody CouponRequestDto couponRequestDto) {
        couponApiService.update(id, couponRequestDto);
        return "update success!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        couponApiService.delete(id);
        return "delete success!";
    }
}
