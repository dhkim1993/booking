package springboot.jpa.booking.core.domain.coupon;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CouponRepository extends JpaRepository<Coupon, Long>, CouponRepositoryCustom{
    Coupon findByName(String name);
}
