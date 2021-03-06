package swcho.mini.mvc.domain.util;

import org.springframework.stereotype.Component;
import swcho.mini.mvc.domain.item.Item;
import swcho.mini.mvc.web.item.ItemSaveForm;
import swcho.mini.mvc.web.item.ItemUpdateForm;

@Component
public class ConvertingVo {

    public Item convertUpdateFormToItem(ItemUpdateForm form) {
        return new Item(form.getId(), form.getName(), form.getPrice(), form.getQuantity(), form.getDescription(), form.getOpen(), form.getRegions(), form.getItemType(), form.getDeliveryCode());
    }

    public Item convertSaveFormToItem(ItemSaveForm form) {
        return new Item(form.getId(), form.getName(), form.getPrice(), form.getQuantity(), form.getDescription(), form.getOpen(), form.getRegions(), form.getItemType(), form.getDeliveryCode());
    }

}
