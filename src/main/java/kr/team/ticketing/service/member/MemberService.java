package kr.team.ticketing.service.member;


import kr.team.ticketing.config.auth.dto.SessionUser;
import kr.team.ticketing.core.domain.member.Member;
import kr.team.ticketing.core.domain.member.MemberRepository;
import kr.team.ticketing.core.domain.member.Role;
import kr.team.ticketing.service.coupon.CouponService;
import kr.team.ticketing.web.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
