package swcho.mini.mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
     * 리스트 조회
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
     * 상세 폼
     */
    @GetMapping("/item/{itemId}")
    public String getItemDetail(@PathVariable("itemId") long id, Model model) {
        model.addAttribute("fragmentPath", "fragments/item-detail-add-update");
        model.addAttribute("fragmentName", "item-detail-add-update");
        model.addAttribute("dmlType", "R");

        model.addAttribute("item", itemRepository.findItemById(id));


        return layoutPath;
    }

    /**
     * 추가 폼
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("fragmentPath", "fragments/item-detail-add-update");
        model.addAttribute("fragmentName", "item-detail-add-update");
        model.addAttribute("dmlType", "C");

        model.addAttribute("item", new Item()); // tymeleaf 에서 렌더링하려고 하는데 오류나기에.. 빈 아이템 전송

        return "/layout";
    }

    /**
     * 추가
     */
    @PostMapping("/add")
    public String add(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {

        log.debug("item = {}", item);

        Item savedItem = itemRepository.addItem(item);

        redirectAttributes.addAttribute("status", true);

        return "redirect:/item/" + savedItem.getId();
    }

    /**
     * 리스트에서 삭제
     */
    @GetMapping("/delete/{itemId}")
    public String removeItemOne(@PathVariable("itemId") Long id, Model model) {

        itemRepository.deleteItemById(id);

        return "redirect:/list";
    }

    /**
     * 수정 폼
     */
    @GetMapping("/update/{itemId}")
    public String updateForm(@PathVariable("itemId") Long id, Model model) {
        log.debug("ItemController.updateForm");
        log.debug("id = {}", id);

        model.addAttribute("fragmentPath", "fragments/item-detail-add-update");
        model.addAttribute("fragmentName", "item-detail-add-update");
        model.addAttribute("dmlType", "U");
        model.addAttribute("item", itemRepository.findItemById(id));

        log.debug("item = {}", itemRepository.findItemById(id));

        return layoutPath;
    }

    /**
     * 수정
     */
    @PostMapping("/update/{itemId}")
    public String update(@PathVariable("itemId") Long id, @ModelAttribute Item item) {
        System.out.println("ItemController.update");
        log.debug("id = {}", id);
        log.debug("item = {}", item);

        itemRepository.updateItem(item);
        
        return "redirect:/item/" + item.getId();
    }

}
