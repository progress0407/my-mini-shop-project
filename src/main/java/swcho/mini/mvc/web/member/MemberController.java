package swcho.mini.mvc.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import swcho.mini.mvc.domain.member.Authority;
import swcho.mini.mvc.domain.member.Member;
import swcho.mini.mvc.domain.member.MemberRepository;
import swcho.mini.mvc.web.util.Const;
import swcho.mini.mvc.web.util.ViewFragment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static swcho.mini.mvc.web.util.Const.LAYOUT_PATH;
import static swcho.mini.mvc.web.util.Const.LOGIN_SESSION_NAME;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

/*    @ModelAttribute("member")
    public Member emptyMemberModel() {
        return new Member();
    }*/

    @ModelAttribute("authorities")
    public List<Authority> authoritiesModel() {
        List<Authority> authorities =
                Arrays.stream(Authority.values())
                        .filter(e -> e != Authority.ADMIN)
                        .collect(Collectors.toList());
        return authorities;
    }

    /**
     * 로그인 폼
     */
    @GetMapping("/log-in")

    public String loginForm(Model model) {
        ViewFragment.setModelParameters(model, "fragments/member/log-in", "log-in", null);
        model.addAttribute("member", new Member());
        return LAYOUT_PATH;
    }

    /**
     * 로그인
     * Password 암호화 시키기
     */
    @PostMapping("/log-in")
    public String login(@ModelAttribute Member member, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        Model model,
                        HttpServletRequest request,
                        @RequestParam(defaultValue = "/index") String redirectURI) {

        Member foundMember = memberRepository.findById(member.getId());
        if (foundMember == null) {
            bindingResult.reject("NoneExistId", new Object[]{"noId"}, "ID가 존재하지 않습니다");
        } else if (!foundMember.getPassword().equals(member.getPassword())) {
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

    /**
     * 로그 아웃
     */
    @GetMapping("/log-out")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/index";
    }

    /**
     * 회원 가입 폼
     */
    @GetMapping("/sign-up")
    public String signup(Model model) {
        ViewFragment.setModelParameters(model, "fragments/member/sign-up", "sign-up", null);
//        model.addAttribute("member", new MemberSignUpForm());
        model.addAttribute(new MemberSignUpForm());
        return LAYOUT_PATH;
    }

    /**
     * 회원 가입
     */
    @PostMapping("/sign-up")
    public String signup(@Validated @ModelAttribute MemberSignUpForm form, BindingResult bindingResult, Model model, HttpServletRequest request) {

        if (!form.getPassword().equals(form.getPasswordCheck())) {
            bindingResult.reject("NotSamePassword", new Object[]{null}, "비밀번호가 일치하지 않습니다");
        }

        Member newMember = new Member(form.getId(), form.getPassword(), form.getName(), Authority.NORMAL);

        if (!memberRepository.createMember(newMember)) {
            bindingResult.reject("AlreadyExist", "이미 존재하는 회원입니다.");
        }

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
//            log.info("bindingResult.getAllErrors() = {}", bindingResult.getAllErrors());
            ViewFragment.setModelParameters(model, "fragments/member/sign-up", "sign-up", null);
            model.addAttribute("member", form);
            return LAYOUT_PATH;
        }



        // 검증 성공
        HttpSession session = request.getSession(true);// 세션 생성
        session.setAttribute(LOGIN_SESSION_NAME, newMember);

        return "redirect:/index";
    }

    /**
     * 회원 상세
     */
    @GetMapping("/{memberId}")
    public String detail(@PathVariable String memberId, Model model) {
        Member member = memberRepository.findById(memberId);
        ViewFragment.setModelParameters(model, "fragments/index", "index", null);
        model.addAttribute("member", member);
        return LAYOUT_PATH;
    }
}
