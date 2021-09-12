package swcho.mini.mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import swcho.mini.mvc.domain.item.DeliveryCode;
import swcho.mini.mvc.domain.item.Item;
import swcho.mini.mvc.domain.item.ItemType;
import swcho.mini.mvc.repository.ItemRepository;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    //    private final ItemService itemService;
    private final ItemRepository itemRepository;
    public static final String layoutPath = "/layout";

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new LinkedList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "하루 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        return deliveryCodes;
    }

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주도");
        return regions;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemType() {
        return ItemType.values();
    }

    /**
     * 첫화면
     */
    @GetMapping({"/", "/index"})

    public String index(Model model) {

        model.addAttribute("fragmentPath", "fragments/index");
        model.addAttribute("fragmentName", "index");

        return layoutPath;
    }

    /**
     * 리스트 조회
     */
    @GetMapping("/list")
    public String boardList(Model model) {

        viewFragmentModelAdd(model, "fragments/item-list", "item-list", null);

        List<Item> items = itemRepository.findAllItems();

        model.addAttribute("items", items);

        return layoutPath;
    }

    /**
     * 상세 폼
     */
    @GetMapping("/item/{itemId}")
    public String getItemDetail(@PathVariable("itemId") long id, Model model) {
        viewFragmentModelAdd(model, "fragments/item-detail-add-update", "item-detail-add-update", "R");

        model.addAttribute("item", itemRepository.findItemById(id));


        return layoutPath;
    }

    /**
     * 추가 폼
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        viewFragmentModelAdd(model, "fragments/item-detail-add-update", "item-detail-add-update", "C");

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

        viewFragmentModelAdd(model, "fragments/item-detail-add-update", "item-detail-add-update", "U");
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

    private void viewFragmentModelAdd(Model model, String fragmentPath, String fragmentName, String dmlType) {
        model.addAttribute("fragmentPath", fragmentPath);
        model.addAttribute("fragmentName", fragmentName);
        model.addAttribute("dmlType", dmlType);
    }
}
