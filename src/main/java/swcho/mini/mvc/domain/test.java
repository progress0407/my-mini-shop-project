package swcho.mini.mvc.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        test3();
    }

    public static void test1() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("A", 100L, 1L));
        items.add(new Item("B", 200L, 2L));
        items.add(new Item("C", 300L, 3L));
        Item newB = new Item("B", 2000L, 44L);

        Item findOne = items.stream().filter(item -> item.getName().equals(newB.getName())).findFirst().get();
        System.out.println("findOne = " + findOne);
    }

    public static void test2() {
        Map<Long, Item> items = new ConcurrentHashMap<>();
        items.put(1L, new Item("A", 100L, 1L));
        items.put(2L, new Item("B", 200L, 2L));
        items.put(3L, new Item("C", 300L, 3L));

        System.out.println("items.get(2L) = " + items.get(2L));

        items.put(2L, new Item("B", 210L, 21L));
        System.out.println("#1 items.get(2L) = " + items.get(2L));

        items.replace(2L, new Item("B", 220L, 22L));
        System.out.println("#2 items.get(2L) = " + items.get(2L));

        items.merge(2L, new Item("B", 230L, 23L), (item, item2) -> {
            System.out.println("nooo");
            return null;
        });
        System.out.println("#3 items.get(2L) = " + items.get(2L));


        items.replace(4L, new Item("B", 240L, 24L));
        System.out.println("items.get(4L) = " + items.get(4L));

    }

    public static void test3() {
        Map<Long, Item> items = null;
//        Map<Long, Item> items = new ConcurrentHashMap<>();
        System.out.println("items = " + items);
        items.put(1L, new Item("A", 100L, 1L));
        System.out.println("items = " + items);

        items.clear();
        System.out.println("items = " + items);

        items.put(1L, new Item("B", 100L, 1L));
        System.out.println("items = " + items);
    }
}
