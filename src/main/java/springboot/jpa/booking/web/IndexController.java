package springboot.jpa.booking.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springboot.jpa.booking.config.auth.LoginUser;
import springboot.jpa.booking.config.auth.dto.SessionUser;
import springboot.jpa.booking.service.member.MemberService;

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
