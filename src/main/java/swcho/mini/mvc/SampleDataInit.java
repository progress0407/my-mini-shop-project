package swcho.mini.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import swcho.mini.mvc.domain.item.ItemRepository;
import swcho.mini.mvc.domain.item.ItemType;
import swcho.mini.mvc.domain.member.Member;
import swcho.mini.mvc.domain.member.MemberRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * 샘플 데이터 추가
     */

    @PostConstruct
    public void init() {
        createItemSampleData();
        createMemberSampleData();
    }

    private void createMemberSampleData() {
        memberRepository.createMember(new Member("admin", "qwer", "관리자"));
    }

    private void createItemSampleData() {
        itemRepository.addItem("한성 노트북 : 올데이롱"
                , 790000
                , 3000
                , "한성 컴퓨터에서 출시한 가성비 노트북.<br> 배터리가 굉장히 오래 갑니다."
                , true
                , List.of("SEOUL", "BUSAN")
                , ItemType.NOTEBOOK
                , "NORMAL");

        itemRepository.addItem("LG 노트북 : LG 그램"
                , 1280000
                , 5000
                , "LG 전자의 야심작 초경량 노트북. <br> 1KG이 안되어 그램이란 브랜드로 승부를 보고 있습니다."
                , true
                , List.of("SEOUL", "BUSAN", "JEJU")
                , ItemType.NOTEBOOK
                , "FAST");

        itemRepository.addItem("체리 키보드 : 토체티 & 듀가드"
                , 130000
                , 2000
                , "두 회사의 콜라보로 만들어진 키보드. <br> 마감이 뛰어나며 체리식 축입니다. <br> 저소음 적축/ 적축 / 갈축을 제공합니다."
                , true
                , List.of("SEOUL")
                , ItemType.KEYBOARD
                , "NORMAL");

        itemRepository.addItem("씽크패드 노트북 : 카본 나노"
                , 1930000
                , 2500
                , "나노는 매끄러운 탄소 섬유와 마그네슘으로 마무리된 깔끔한 디자인 입니다. <br> 더 작은 사이즈와 최신 디자인으로 뛰어난 배터리 수명을 가지고 있습니다."
                , true
                , List.of("SEOUL", "JEJU")
                , ItemType.NOTEBOOK
                , "NORMAL");

        itemRepository.addItem("mStone Groove T87A 게이트론 저소음갈축 키보드"
                , 139000
                , 1500
                , "기판과 보강판 사이 흡읍재를 넣어 더욱 조용하고 정갈한 타건감을 선사합니다. <br> 아노다이징 알루미늄 보강판만의 녹이 잘 슬지 않는 특성이 있습니다."
                , true
                , List.of("SEOUL", "BUSAN")
                , ItemType.KEYBOARD
                , "FAST");
    }


}
