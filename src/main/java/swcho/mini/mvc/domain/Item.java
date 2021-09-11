package swcho.mini.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    // 상품 식별번호
    private Long id;
    // 상품명
    private String name;
    // 상품 가격
    private long price;
    // 상품 수량
    private long quantity;
    // 상품 설명
    private String description;
    
//    private Boolean open; // 판매여부



    public Item(String name, long price, long quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}