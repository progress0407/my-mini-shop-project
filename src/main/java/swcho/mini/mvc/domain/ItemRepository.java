package swcho.mini.mvc.domain;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {

    private static Map<Long, Item> items = new ConcurrentHashMap<>();
    private static long sequence = 1L;
//    List<Item> items = new ArrayList<>();

    /**
     * 추가
     * 등록
     * 삭제
     * 조회
     * 상세조회
     */

    @PostConstruct
    public void init() {
        addItem("한성 노트북 : 올데이롱", 790000L, 150L);
        addItem("LG 노트북 : LG 그램", 1285000L, 320L);
        addItem("체리 키보드 : 토체티 & 듀가드", 135000L, 90L);
    }

    public int getTotalCnt() {
        return items.size();
    }

    /**
     * 등록
     * @param name
     * @param price
     * @param quantity
     */
    public Item addItem(String name, Long price, Long quantity) {
        return addItem(name, price, quantity, "");
    }

    public Item addItem(Item item) {
        return addItem(item.getName(), item.getPrice(), item.getQuantity(), item.getDescription());
    }

    public Item addItem(String name, long price, long quantity, String description) {
        Item item = new Item(sequence, name, price, quantity);
        item.setDescription(description);
        items.put(sequence++, item);
        return item;
    }

    /**
     * 리스트 조회
     * @return
     */
    public List<Item> findAllItems() {
        return items.values().stream().collect(Collectors.toList());
    }

    /**
     * 상세 조회
     * @param id
     * @return
     */
    public Item findItemById(long id) {
        Item findItem = items.get(id);

        if (findItem == null) {
            throw new IllegalArgumentException("해당 ID가 존재하지 않습니다.");
        }

        return findItem;
    }

    /**
     * 변경
     * @param item
     * @return
     */
    public Item updateItem(Item item) {
        items.replace(item.getId(), item);
        return item;
    }

    public Item deleteItemById(long id) {
        Item toDeleteItem = findItemById(id);

        items.remove(id);
        return toDeleteItem;
    }

    public void deleteAll() {
        items.clear();
    }
}
