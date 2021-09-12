package swcho.mini.mvc.domain;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import swcho.mini.mvc.domain.item.ItemType;

@Slf4j
public class DataTypeTest {

    @Test
    public void 아이템_타입_모모있니() throws Exception {
        log.info("아이템 타입 = {}", ItemType.values());
        Assertions.assertThat(ItemType.values()).containsExactly(ItemType.NOTEBOOK, ItemType.KEYBOARD, ItemType.ETC);
    }
}
