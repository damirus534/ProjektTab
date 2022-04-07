package pl.polsl.ProjektTab.Model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Basket {

    private Long basketId;
    private Long userId;
    private Long productId;
    private Long amountOfProducts;
}
