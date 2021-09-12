package swcho.mini.mvc.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    // 상품 식별번호
    private Long id;
    // 상품명
    private String name;
    // 상품 가격
    private Long price;
    // 상품 수량
    private Long quantity;
    // 상품 설명
    private String description;
    
    private Boolean open; // 판매여부
    private List<String> regions; // 배송 가능 지역
    private ItemType itemType; // 상품 종류
    private String deliveryCode; // 배송 코드

    public Item(String name, Long price, Long quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


}