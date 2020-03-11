package springboot.jpa.booking.service.member;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.booking.config.auth.dto.SessionUser;
import springboot.jpa.booking.core.domain.member.Member;
import springboot.jpa.booking.core.domain.member.MemberRepository;
import springboot.jpa.booking.core.domain.member.Role;
import springboot.jpa.booking.service.coupon.CouponService;
import springboot.jpa.booking.web.member.dto.MemberResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final CouponService couponService;

    @Transactional
    public void addCoupon(Long memberId, String couponName) {
        Member member = memberRepository.findById(memberId).get();
        member.addCoupon(couponService.findByName(couponName));
    }

    public MemberResponseDto getMemberResponseDto(SessionUser user) {
        return MemberResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .picture(user.getPicture())
                .role(Role.USER)
                .build();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).get();
    }
}
