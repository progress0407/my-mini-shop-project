package swcho.mini.mvc.domain;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {

    private static Map<Long, Item> store = new ConcurrentHashMap<>();
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
        addItem("한성 노트북 : 올데이롱", 790000L, 3000L, "한성 컴퓨터에서 출시한 가성비 노트북.<br> 배터리가 굉장히 오래 갑니다.");
        addItem("LG 노트북 : LG 그램", 1280000L, 5000L, "LG 전자의 야심작 초경량 노트북. <br> 1KG이 안되어 그램이란 브랜드로 승부를 보고 있습니다.");
        addItem("체리 키보드 : 토체티 & 듀가드", 130000L, 2000L, "두 회사의 콜라보로 만들어진 키보드. <br> 마감이 뛰어나며 체리식 축입니다. <br> 저소음 적축/ 적축 / 갈축을 제공합니다.");
    }

    public int getTotalCnt() {
        return store.size();
    }

    /**
     * 등록
     */

    public Item addItem(Item item) {
        return addItem(item.getName(), item.getPrice(), item.getQuantity(), item.getDescription());
    }

    public Item addItem(String name, Long price, Long quantity, String description) {
        Item item = new Item(++sequence, name, price, quantity, description);
        store.put(sequence, item);
        return item;
    }

    /**
     * 리스트 조회
     * @return
     */
    public List<Item> findAllItems() {
        return store.values().stream().collect(Collectors.toList());
    }

    /**
     * 상세 조회
     * @param id
     * @return
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
     * @param item
     * @return
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
