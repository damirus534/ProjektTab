package pl.polsl.ProjektTab.Model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderHistory {

    private Long orderHistoryId;
    private Long userId;
    private Date date;
    private Float purchaseSum;

}
