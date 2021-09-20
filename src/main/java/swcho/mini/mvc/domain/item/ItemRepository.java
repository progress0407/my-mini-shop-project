package swcho.mini.mvc.domain.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import swcho.mini.mvc.domain.item.Item;
import swcho.mini.mvc.domain.item.ItemType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ItemRepository {

    private static Map<Long, Item> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    /**
     * 추가
     * 등록
     * 삭제
     * 조회
     * 상세조회
     */

    public int getTotalCnt() {
        return store.size();
    }

    /**
     * 등록
     */

    public Item addItem(Item item) {
        return addItem(item.getName(), item.getPrice(), item.getQuantity(), item.getDescription(), item.getOpen(), item.getRegions(), item.getItemType(), item.getDeliveryCode());
    }

    public Item addItem(String name, Integer price, Integer quantity, String description, Boolean open, List<String> regions, ItemType itemType, String deliveryCode) {
        Item item = new Item(++sequence, name, price, quantity, description, open, regions, itemType, deliveryCode);
        store.put(sequence, item);
        log.info("item = {}", item);
        return item;
    }

    /**
     * 리스트 조회
     */
    public List<Item> findAllItems() {
        return store.values().stream().collect(Collectors.toList());
    }

    /**
     * 상세 조회
     */
    public Item findItemById(long id) {
        Item findItem = store.get(id);

        if (findItem == null) {
            throw new IllegalArgumentException("해당 ID가 존재하지 않습니다.");
        }

        return findItem;
    }

    /**
     * 변경
     */
    public Item updateItem(Item item) {
        store.replace(item.getId(), item);
        return item;
    }

    public Item deleteItemById(long id) {
        Item toDeleteItem = findItemById(id);

        store.remove(id);
        return toDeleteItem;
    }

    public void deleteAll() {
        store.clear();
    }
}
