package swcho.mini.mvc.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import swcho.mini.mvc.domain.item.Item;
import swcho.mini.mvc.domain.item.ItemRepository;
import swcho.mini.mvc.web.util.ViewFragment;

import java.util.List;

import static swcho.mini.mvc.web.util.Const.LAYOUT_PATH;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ItemRepository itemRepository;

    /**
     * 첫화면
     */
    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }
//    @GetMapping({"/", "/index"})
    @GetMapping("/index")
    public String index(Model model) {
        ViewFragment.setModelParameters(model, "fragments/index", "index", null);
        List<Item> items = itemRepository.findAllItems();
        model.addAttribute("items", items);
        return LAYOUT_PATH;
    }
}
