package swcho.mini.mvc.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import swcho.mini.mvc.domain.member.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static swcho.mini.mvc.web.util.Const.LOGIN_SESSION_NAME;

/**
 * 로그인 인증 체크
 */
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        String requestURI = request.getRequestURI();

        if (session == null || session.getAttribute(LOGIN_SESSION_NAME) == null) {
            response.sendRedirect("/member/log-in?redirectURI=" + requestURI);
            return false;
        }

        Member member = (Member) session.getAttribute(LOGIN_SESSION_NAME);
        if (member != null) {
            log.info("Session Member = {}", member.getName());
        }
        return true;
    }

}
