package kr.team.ticketing.web;

import kr.team.ticketing.config.auth.LoginUser;
import kr.team.ticketing.config.auth.dto.SessionUser;
import kr.team.ticketing.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MemberService memberService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("member", memberService.getMemberResponseDto(user));
        }
        return "index";
    }
}
