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

    public Date getOrderHistoryDate() {
        return this.orderHistoryDate;
    }

    public void setOrderHistoryDate(Date orderHistoryDate) {
        this.orderHistoryDate = orderHistoryDate;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getOrderAmount() {
        return this.orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Float getOrderSellPrice() {
        return this.orderSellPrice;
    }

    public void setOrderSellPrice(Float orderSellPrice) {
        this.orderSellPrice = orderSellPrice;
    }

    public String getProductSize() {
        return this.productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Float getBuyingPrice() {
        return this.buyingPrice;
    }

    public void setBuyingPrice(Float buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
