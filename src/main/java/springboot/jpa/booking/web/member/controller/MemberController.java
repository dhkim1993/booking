package springboot.jpa.booking.web.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.jpa.booking.config.auth.LoginUser;
import springboot.jpa.booking.config.auth.dto.SessionUser;
import springboot.jpa.booking.service.member.MemberService;
import springboot.jpa.booking.service.reservation.ReservationService;

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
