package pl.polsl.ProjektTab.Filters;

import lombok.Data;

import java.util.Date;

@Data
public class AdminRaportFilterProfit {
    private Date orderHistoryDate;
    private String productName;
    private Integer orderAmount;
    private Float orderSellPrice;
    private String productSize;
    private Float buyingPrice;
    private String categoryName;

    public AdminRaportFilterProfit(Date orderHistoryDate, String productName, Integer orderAmount, Float orderSellPrice, String productSize, Float buyingPrice, String categoryName) {
        this.orderHistoryDate = orderHistoryDate;
        this.productName = productName;
        this.orderAmount = orderAmount;
        this.orderSellPrice = orderSellPrice;
        this.productSize = productSize;
        this.buyingPrice = buyingPrice;
        this.categoryName = categoryName;
    }
}
