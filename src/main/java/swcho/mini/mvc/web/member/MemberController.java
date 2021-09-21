package swcho.mini.mvc.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import swcho.mini.mvc.domain.member.Member;
import swcho.mini.mvc.domain.member.MemberRepository;
import swcho.mini.mvc.web.util.ViewFragment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static swcho.mini.mvc.web.util.Const.LAYOUT_PATH;
import static swcho.mini.mvc.web.util.Const.LOGIN_SESSION_NAME;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/log-in")
    public String loginForm(Model model) {
        ViewFragment.setModelParameters(model, "fragments/member/log-in", "log-in", null);
        model.addAttribute("member", new Member());
        return LAYOUT_PATH;
    }

    @PostMapping("/log-in")
    public String login(@ModelAttribute Member member, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        Model model,
                        HttpServletRequest request,
                        @RequestParam(defaultValue = "/index") String redirectURI) {

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
            ViewFragment.setModelParameters(model, "fragments/member/log-in", "log-in", null);
            return LAYOUT_PATH;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(LOGIN_SESSION_NAME, foundMember);
//        session.setMaxInactiveInterval(1800); // 기본 30분

        return "redirect:" + redirectURI;
    }

    @GetMapping("/sign-up")
    public String signup(@ModelAttribute Member member, BindingResult bindingResult) {
        if(!memberRepository.createMember(member)) {
            bindingResult.reject("AlreadyExist", "이미 존재하는 회원입니다.");
            return LAYOUT_PATH;
        }
        return LAYOUT_PATH;
    }
}
