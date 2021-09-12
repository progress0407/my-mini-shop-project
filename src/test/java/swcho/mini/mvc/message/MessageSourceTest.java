package swcho.mini.mvc.message;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    public void lang() throws Exception {
        log.info(ms.getMessage("nav.logged.on", new String[] {"swcho"}, Locale.KOREA));
        assertThat(ms.getMessage("nav.logged.on", new String[] {"swcho"}, Locale.KOREA)).isEqualTo("swcho 님 안녕하세요 !");

        log.info(ms.getMessage("nav.logged.on", new String[] {"swcho"}, Locale.ENGLISH));
        assertThat(ms.getMessage("nav.logged.on", new String[] {"swcho"}, Locale.ENGLISH)).isEqualTo("Hello, swcho !");

        log.info(ms.getMessage("page.item", null, Locale.KOREA));
        assertThat(ms.getMessage("page.item", null, Locale.KOREA)).isEqualTo("상품 상세");

        log.info(ms.getMessage("page.item", null, Locale.ENGLISH));
        assertThat(ms.getMessage("page.item", null, Locale.ENGLISH)).isEqualTo("Item Detail");
    }

}
