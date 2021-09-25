package swcho.mini.mvc.web.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import swcho.mini.mvc.web.util.Const;
import swcho.mini.mvc.web.util.ViewFragment;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
@Slf4j
public class ErrorController {

    @RequestMapping("/4xx")
    public String errorPage400(Model model, HttpServletRequest request) {
        ViewFragment.setModelParameters(model, "fragments/error/4xx", "error-4xx", null);
        // 직전 페이지로 돌아가기
        String previousPage = request.getHeader("Referer");
        model.addAttribute("previousPage", previousPage);
        return Const.LAYOUT_PATH;
    }

    @RequestMapping("/404")
    public String errorPage404(Model model, HttpServletRequest request) {
        ViewFragment.setModelParameters(model, "fragments/error/404", "error-404", null);
        // 직전 페이지로 돌아가기
        String previousPage = request.getHeader("Referer");
        model.addAttribute("previousPage", previousPage);
        return Const.LAYOUT_PATH;
    }

    /**
     * Runtime Exception 도 같이 처리합니다
     */
    @RequestMapping("/500")
    public String errorPage500(Model model, HttpServletRequest request) {
        ViewFragment.setModelParameters(model, "fragments/error/500", "error-500", null);
        // 직전 페이지로 돌아가기
        String previousPage = request.getHeader("Referer");
        model.addAttribute("previousPage", previousPage);
        return Const.LAYOUT_PATH;
    }
}
