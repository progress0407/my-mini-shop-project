package swcho.mini.mvc.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import swcho.mini.mvc.web.util.Const;
import swcho.mini.mvc.web.util.ViewFragment;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("")
    public String root(Model model) {
        ViewFragment.setModelParameters(model, "fragments/test/test-main", "test-main", null);
        return Const.LAYOUT_PATH;
    }

    @GetMapping("/error-throw/runtime")
    public void exRunTime() {
        throw new RuntimeException("[Error Page] Runtime Exception");
    }

    @GetMapping("/error-throw/404")
    public void ex404(HttpServletResponse response) throws IOException {
        response.sendError(404, "[Error Page] 404");
    }

    @GetMapping("/error-throw/400")
    public void ex400(HttpServletResponse response) throws IOException {
        response.sendError(400, "[Error Page] 400");
    }

    @GetMapping("/error-throw/500")
    public void ex500(HttpServletResponse response) throws IOException {
        response.sendError(500, "[Error Page] 500");
    }
}
