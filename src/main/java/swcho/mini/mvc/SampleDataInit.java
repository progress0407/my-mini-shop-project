package swcho.mini.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swcho.mini.mvc.repository.ItemRepository;
import swcho.mini.mvc.domain.item.ItemType;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleDataInit {

    private final ItemRepository itemRepository;

    /**
     * 샘플 데이터 추가
     */

    @PostConstruct
    public void init() {
        itemRepository.addItem("한성 노트북 : 올데이롱"
                , 790000L
                , 3000L
                , "한성 컴퓨터에서 출시한 가성비 노트북.<br> 배터리가 굉장히 오래 갑니다."
                , true
                , List.of("SEOUL", "BUSAN")
                , ItemType.NOTEBOOK
                , "NORMAL");

        itemRepository.addItem("LG 노트북 : LG 그램"
                , 1280000L
                , 5000L
                , "LG 전자의 야심작 초경량 노트북. <br> 1KG이 안되어 그램이란 브랜드로 승부를 보고 있습니다."
                , true
                , List.of("SEOUL", "BUSAN", "JEJU")
                , ItemType.NOTEBOOK
                , "FAST");

        itemRepository.addItem("체리 키보드 : 토체티 & 듀가드"
                , 130000L
                , 2000L
                , "두 회사의 콜라보로 만들어진 키보드. <br> 마감이 뛰어나며 체리식 축입니다. <br> 저소음 적축/ 적축 / 갈축을 제공합니다."
                , true
                , List.of("SEOUL")
                , ItemType.KEYBOARD
                , "NORMAL");
    }
}
