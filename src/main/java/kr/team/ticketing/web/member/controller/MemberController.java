package kr.team.ticketing.web.member.controller;

import kr.team.ticketing.config.auth.LoginUser;
import kr.team.ticketing.config.auth.dto.SessionUser;
import kr.team.ticketing.service.member.MemberService;
import kr.team.ticketing.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final ReservationService reservationService;

    @GetMapping("/list")
    public String member_list(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "/member/list";
    }

    @GetMapping("/mypage")
    public String myPage(Model model, @LoginUser SessionUser user) {
        model.addAttribute("reservations", reservationService.getReservationDtosByMemberId(user.getId()));
        return "member/mypage";
    }
}
