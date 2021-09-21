package swcho.mini.mvc.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import swcho.mini.mvc.domain.member.Member;
import swcho.mini.mvc.domain.member.MemberRepository;
import swcho.mini.mvc.web.item.PathConst;
import swcho.mini.mvc.web.util.ViewFragment;

import static swcho.mini.mvc.web.item.PathConst.LAYOUT_PATH;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/log-in")
    public String loginForm(Model model) {
        ViewFragment.setParameters(model, "fragments/log-in", "log-in", null);
        model.addAttribute("member", new Member());
        return LAYOUT_PATH;
    }

    @PostMapping("/log-in")
    public String login(@ModelAttribute Member member, BindingResult bindingResult) {
        if(!memberRepository.createMember(member)) {
            bindingResult.reject("AlreadyExist", "이미 존재하는 회원입니다.");
        }
        return LAYOUT_PATH;
    }

    @GetMapping("/sign-up")
    public String signup() {
        return LAYOUT_PATH;
    }
}
