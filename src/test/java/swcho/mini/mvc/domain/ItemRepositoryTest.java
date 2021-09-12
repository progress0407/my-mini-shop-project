package swcho.mini.mvc.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import swcho.mini.mvc.SampleDataInit;
import swcho.mini.mvc.domain.item.Item;
import swcho.mini.mvc.repository.ItemRepository;
import swcho.mini.mvc.domain.item.ItemType;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Slf4j
//@SpringBootTest
class ItemRepositoryTest {

//    @Autowired
    ItemRepository itemRepository = new ItemRepository();
    SampleDataInit sampleDataInit = new SampleDataInit(itemRepository);

    @BeforeEach
    public void 모두_비우기() {
        itemRepository.deleteAll();
        sampleDataInit.init();
    }

    @Test
    public void 조회() throws Exception {
        System.out.println("ItemRepositoryTest.조회");
        // given

        // when
        List<Item> itemList = itemRepository.findAllItems();
        log.debug("itemList = {}", itemList);


        // then
        assertThat(itemList.size()).isEqualTo(3);
        itemList.stream().forEach(System.out::println);
    }

    @Test
    public void 수정() {
        System.out.println("ItemRepositoryTest.수정");

        Item item = new Item(2L
                            , "레노버 놋북 빨콩 최고"
                            , 780000L
                            , 300L
                            , "내용"
                            , true
                            , List.of("SEOUL")
                            , ItemType.KEYBOARD
                            , "NORMAL");
        Item updateItem = itemRepository.updateItem(item);
        log.debug("updateItem = {}", updateItem);

        assertThat(updateItem.getName()).isEqualTo("레노버 놋북 빨콩 최고");

    }

    @Test
    public void ID로_조회() throws Exception {
        // given
        System.out.println("ItemRepositoryTest.ID로_조회");

        // when
        Item findItem = itemRepository.findItemById(1L);
        log.debug("findItem = {}", findItem);

        // then
//        assertThat(findItem.getName()).isEqualTo("한성 노트북 : 올데이롱");

    }

    @Test
    public void ID로_삭제() {
        // given
        System.out.println("ItemRepositoryTest.ID로_삭제");
        int beforeRemoveCnt = itemRepository.getTotalCnt();

        // when
        Item deletedItem = itemRepository.deleteItemById(3L);
        int afterRemoveCnt = itemRepository.getTotalCnt();

        assertThat(beforeRemoveCnt - afterRemoveCnt).isEqualTo(1);

        IllegalArgumentException e = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> itemRepository.deleteItemById(3L));
        assertThat(e.getMessage()).isEqualTo("해당 ID가 존재하지 않습니다.");

        log.debug("error msg = {}", e.getMessage());

    }


}