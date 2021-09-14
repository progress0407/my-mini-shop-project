package swcho.mini.mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import swcho.mini.mvc.domain.item.*;
import swcho.mini.mvc.repository.ItemRepository;
import swcho.mini.mvc.util.ConvertingVo;
import swcho.mini.mvc.validation.ItemValidator;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;
    private final ConvertingVo convertingVo;

    public static final String layoutPath = "/layout";

    @InitBinder
    public void initValidator(WebDataBinder dataBinder) {
        dataBinder.addValidators(itemValidator);
    }

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
        model.addAttribute("item", new ItemSaveForm()); // tymeleaf 에서 렌더링하려고 하는데 오류나기에.. 빈 아이템 전송
        return layoutPath;
    }

    /**
     * 추가
     */
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            viewFragmentModelAdd(model, "fragments/item-detail-add-update", "item-detail-add-update", "C");
            return layoutPath;
        }
        
        // 성공 로직

        log.debug("form = {}", form);
        Item item = convertingVo.convertSaveFormToItem(form);
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
    public String update(@Validated @PathVariable("itemId") Long id, @ModelAttribute ItemUpdateForm form) {
        log.debug("id = {}", id);
        log.debug("item = {}", form);


        Item item = convertingVo.convertUpdateFormToItem(form);
        itemRepository.updateItem(item);
        return "redirect:/item/{itemId}";
    }

    private void viewFragmentModelAdd(Model model, String fragmentPath, String fragmentName, String dmlType) {
        model.addAttribute("fragmentPath", fragmentPath);
        model.addAttribute("fragmentName", fragmentName);
        model.addAttribute("dmlType", dmlType);
    }
}
