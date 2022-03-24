package pl.polsl.ProjektTab.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    private Long productId;
    private Long productInfoId;
    private String size;
    private Long numberOfAvailableProducts;
}
