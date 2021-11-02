package swcho.mini.mvc.web.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import swcho.mini.mvc.domain.item.ItemType;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemSaveForm {


    private Long id; // 상품 식별번호

    @NotBlank
    private String name; // 상품명

    @NotNull
    @Range(min = 1_000, max = 1_000_000)
    private Integer price; // 상품 가격

    @NotNull
    @Max(value = 9999)
    private Integer quantity; // 상품 수량

    private String description; // 상품 설명

    private Boolean open; // 판매여부

    @NotNull // OK
//    @NotBlank // 500 Error
    private List<String> regions; // 배송 가능 지역

    @NotNull
    private ItemType itemType; // 상품 종류

    @NotBlank
    private String deliveryCode; // 배송 코드

    public ItemSaveForm(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


}