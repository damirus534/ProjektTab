package pl.polsl.ProjektTab.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductInfo {
    private Long productInfoId;
    private Long categoryId;
    private String name;
    private String description;
    private Float purchasePrice;
    private Float salePrice;
}
