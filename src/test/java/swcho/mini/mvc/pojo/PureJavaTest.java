package swcho.mini.mvc.pojo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PureJavaTest {

    @Test
    public void ListTest() throws Exception {
        List<String> list = new ArrayList<>();
        System.out.println("list = " + list);
        System.out.println("list.isEmpty() = " + list.isEmpty());

        String str = null;
        System.out.println("str = " + str);
//        System.out.println("str.isEmpty() = " + str.isEmpty()); // NPE
        str = "";
        System.out.println("str = " + str);
        System.out.println("str.isEmpty() = " + str.isEmpty());
        str = " ";
        System.out.println("str = " + str);
        System.out.println("str.isEmpty() = " + str.isEmpty());

    }
}
