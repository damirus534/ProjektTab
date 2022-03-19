package Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    private Long productId;
    private Short categoryId;
    private Double purchasePrice;
    private Double salePrice;
    private String productName;
    private String size;
    private String description;
    private Long gallery_id;
}
