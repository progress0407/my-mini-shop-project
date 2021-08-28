package swcho.mini.mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import swcho.mini.mvc.domain.Item;
import swcho.mini.mvc.domain.ItemRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    //    private final ItemService itemService;
    private final ItemRepository itemRepository;
    public static final String layoutPath = "/layout";

    /**
     * 첫화면
     */
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("fragmentPath", "fragments/index");
        model.addAttribute("fragmentName", "index");

        model.addAttribute("foo", "index Something");
        return layoutPath;
    }

    /**
     * 조회
     */
    @GetMapping("/list")
    public String boardList(Model model) {
        model.addAttribute("fragmentPath", "fragments/item-list");
        model.addAttribute("fragmentName", "item-list");

        List<Item> items = itemRepository.findAllItems();

        model.addAttribute("items", items);

        return layoutPath;
    }

    /**
     * 상세 조회
     */
    @GetMapping("/item/{itemId}")
    public String getItemDetail(@PathVariable("itemId") long id, Model model) {
        model.addAttribute("fragmentPath", "fragments/item-detail");
        model.addAttribute("fragmentName", "item-detail");

        model.addAttribute("item", itemRepository.findItemById(id));
        return layoutPath;
    }

    /**
     * 추가 양식
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("fragmentPath", "fragments/item-add-update");
        model.addAttribute("fragmentName", "item-add-update");

        return "/layout";
    }

    /**
     * 추가
     */
    @PostMapping("/add")
    public String add(@ModelAttribute Item item) {
//        @ModelAttribute Item item
//        @RequestBody Item item2

        log.debug("item = {}", item);
//        log.debug("item2 = {}", item2);

        itemRepository.addItem(item);

        return "redirect:/index";
    }

    /**
     * 삭제
     */
    @GetMapping("/delete/{itemId}")
    public String removeItemOne(@PathVariable("itemId") long id, Model model) {

        itemRepository.deleteItemById(id);

        return "redirect:/list";
    }

    /**
     * 변경 양식
     */
    @GetMapping("/update/{itemId}")
    public String updateForm(@PathVariable("itemId") long id, Model model) {
        System.out.println("ItemController.updateForm");
        log.debug("id = {}", id);
        log.debug("model = {}", model);

        model.addAttribute("fragmentPath", "fragments/item-add-update");
        model.addAttribute("fragmentName", "item-add-update");
        model.addAttribute("idReadonly", true);
        model.addAttribute("item", itemRepository.findItemById(id));
        model.addAttribute("itemList", itemRepository.findAllItems());

        log.debug("item = {}", itemRepository.findItemById(id));

        return layoutPath;
    }

    /**
     * 변경
     */
    @PostMapping("/update/{itemId}")
    public String update(@PathVariable("itemId") long id, @ModelAttribute Item item) {
        System.out.println("ItemController.update");
        log.debug("id = {}", id);
        log.debug("item = {}", item);

        itemRepository.updateItem(item);
        
        return "redirect:/item/" + item.getId();
    }

}
