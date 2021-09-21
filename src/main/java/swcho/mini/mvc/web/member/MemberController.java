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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import swcho.mini.mvc.domain.member.Member;
import swcho.mini.mvc.domain.member.MemberRepository;
import swcho.mini.mvc.web.util.ViewFragment;

import static swcho.mini.mvc.web.util.PathConst.LAYOUT_PATH_BEFORE_LOG_IN;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/log-in")
    public String loginForm(Model model) {
        ViewFragment.setModelParameters(model, "fragments/log-in", "log-in", null);
        model.addAttribute("member", new Member());
        return LAYOUT_PATH_BEFORE_LOG_IN;
    }

    @PostMapping("/log-in")
    public String login(@ModelAttribute Member member, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        Member foundMember = memberRepository.findById(member.getId());
        if(foundMember == null) {
            bindingResult.reject("NoneExistId", new Object[]{"noId"}, "ID가 존재하지 않습니다");
        }
        else if (!foundMember.getPassword().equals(member.getPassword())) {
            bindingResult.reject("NotExactPassword", "비밀번호가 정확하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            // 리다이렉트시 바인딩 에러에 대한 내용이 지워진다.. 재요청이기 때문에
//            return "redirect:/member/log-in";
            ViewFragment.setModelParameters(model, "fragments/log-in", "log-in", null);
            return LAYOUT_PATH_BEFORE_LOG_IN;
        }

//        redirectAttributes.addAttribute("member", member);
        model.addAttribute("member", foundMember);

//        ViewFragment.setModelParameters(model, "fragments/index", "index", null);
        ViewFragment.setRedirectParameters(redirectAttributes, "fragments/index", "index", null);
//        return LAYOUT_PATH_AFTER_LOG_IN;
        return "redirect:/index";
    }

    @GetMapping("/sign-up")
    public String signup(@ModelAttribute Member member, BindingResult bindingResult) {
        if(!memberRepository.createMember(member)) {
            bindingResult.reject("AlreadyExist", "이미 존재하는 회원입니다.");
            return LAYOUT_PATH_BEFORE_LOG_IN;
        }
        return LAYOUT_PATH_BEFORE_LOG_IN;
    }
}
