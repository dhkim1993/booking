package springboot.jpa.booking.web.coupon.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.jpa.booking.config.auth.LoginUser;
import springboot.jpa.booking.config.auth.dto.SessionUser;
import springboot.jpa.booking.service.coupon.CouponApiService;
import springboot.jpa.booking.web.coupon.dto.CouponRequestDto;

@RestController
@RequiredArgsConstructor
public class CouponApiController {

    private final CouponApiService couponApiService;

    @PutMapping("/api/v2/coupon/month")
    public String addThisMonthCoupons(@LoginUser SessionUser user) {
        return couponApiService.addThisMonthCoupons(user.getId());
    }

    @PostMapping("/api/v2/coupon/{id}/{totalPrice}")
    public Long checkCouponExpirationDate(@PathVariable("id") Long couponId,
                            @PathVariable("totalPrice") int totalPrice) {
        return couponApiService.isCanUseCoupon(couponId, totalPrice);
    }

    @PostMapping("/api/v3/coupon")
    public String save(@RequestBody CouponRequestDto couponRequestDto) {
        couponApiService.save(couponRequestDto);
        return "save success!";
    }

    @PutMapping("/api/v3/coupon/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody CouponRequestDto couponRequestDto) {
        couponApiService.update(id, couponRequestDto);
        return "update success!";
    }

    @DeleteMapping("/api/v3/coupon/{id}")
    public String delete(@PathVariable("id") Long id) {
        couponApiService.delete(id);
        return "delete success!";
    }
}
