package swcho.mini.mvc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Item {

    private Long id;
    private String name;
    private long price;
    private long quantity;
    private String description;

    public Item(long id, String name, long price, long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(String name, long price, long quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}