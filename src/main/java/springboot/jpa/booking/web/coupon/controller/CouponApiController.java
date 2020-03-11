package springboot.jpa.booking.web.coupon.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.jpa.booking.config.auth.LoginUser;
import springboot.jpa.booking.config.auth.dto.SessionUser;
import springboot.jpa.booking.service.coupon.CouponApiService;
import springboot.jpa.booking.web.coupon.dto.CouponRequestDto;

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
