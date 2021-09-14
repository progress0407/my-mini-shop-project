package swcho.mini.mvc.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import swcho.mini.mvc.domain.item.Item;

@Slf4j
@Component
public class ItemValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
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
            if (resultPrice < 100000) {
                errors.reject("totalPriceMin", new Object[]{100000, resultPrice}, null);
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
