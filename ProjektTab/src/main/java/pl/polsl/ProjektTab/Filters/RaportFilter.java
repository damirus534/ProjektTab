package pl.polsl.ProjektTab.Filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
public class RaportFilter {
    private Date orderHistoryDate;
    private String productName;
    private Integer orderAmount;
    private Float orderSellPrice;
    private String productSize;

    public RaportFilter(Date orderHistoryDate, String productName, Integer orderAmount, Float orderSellPrice, String productSize) {
        this.orderHistoryDate = orderHistoryDate;
        this.productName = productName;
        this.orderAmount = orderAmount;
        this.orderSellPrice = orderSellPrice;
        this.productSize = productSize;
    }

}
