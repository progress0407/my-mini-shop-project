package swcho.mini.mvc.web.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import swcho.mini.mvc.domain.item.Item;
import swcho.mini.mvc.domain.util.ConvertingVo;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemValidator implements Validator {

    private final ConvertingVo convertingVo;

    @Override
    public boolean supports(Class<?> clazz) {
        if(ItemSaveForm.class.isAssignableFrom(clazz)
                || ItemUpdateForm.class.isAssignableFrom(clazz)
                || Item.class.isAssignableFrom(clazz)) {
            return true;
        }
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
//        Item item = (Item) target;
        Item item = null;
        if (target instanceof ItemSaveForm) {
            item = convertingVo.convertSaveFormToItem((ItemSaveForm) target);
        } else if (target instanceof ItemUpdateForm) {
            item = convertingVo.convertUpdateFormToItem((ItemUpdateForm) target);
        }

        log.debug("item to validate = {}", item);

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "required");
//
//        if (item.getPrice() == null || item.getPrice() < 500 || item.getPrice() > 1000000) {
//            errors.rejectValue("price", "range", new Object[]{500, 1000000}, null);8
//        }
//
//        if (item.getQuantity() == null || item.getQuantity() > 99999) {
//            errors.rejectValue("quantity", "max", new Object[]{99999}, null);
//        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            int minPrice = 200000;
            if (resultPrice < minPrice) {
                errors.reject("totalPriceMin", new Object[]{minPrice, resultPrice}, null);
            }
        }

        if (item.getRegions().isEmpty()) {
            errors.rejectValue("regions", "required");
        }

        if (item.getItemType() == null) {
            errors.rejectValue("itemType", "required");
        }

        if (item.getDeliveryCode().equals("")) {
            errors.rejectValue("deliveryCode", "required");
        }
    }
}
