package pl.polsl.ProjektTab.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {

    private Long orderId;
    private Long productId;
    private Long orderHistoryId;
    private Long amountOfBoughtProduct;
    private Float sellPrice;
}
