package pl.polsl.ProjektTab.Filters;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor

@ToString
public class CartsItem {
    Long id;
    String name;
    String size;
    String imageUrl;
    Float price;
    Integer amount;

    public CartsItem(Long id, String name, String size, String imageUrl, Float price, Integer amount) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.imageUrl = imageUrl;
        this.price = price;
        this.amount = amount;
    }
}
