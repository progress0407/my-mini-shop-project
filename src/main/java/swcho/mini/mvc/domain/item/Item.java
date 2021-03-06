package swcho.mini.mvc.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {


    private Long id; // 상품 식별번호
    private String name; // 상품명
    private Integer price; // 상품 가격
    private Integer quantity; // 상품 수량
    private String description; // 상품 설명
    
    private Boolean open; // 판매여부
    private List<String> regions; // 배송 가능 지역
    private ItemType itemType; // 상품 종류
    private String deliveryCode; // 배송 코드

    public Item(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


}