package pl.polsl.ProjektTab.Order;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.polsl.ProjektTab.OrderHistory.OrderHistory;
import pl.polsl.ProjektTab.Product.Product;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amountPurchased;
    private Float sellingPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_history_id", referencedColumnName = "id")
    private OrderHistory orderHistory;

    public Order() {
    }

    public Order(Integer amountPurchased, Float sellingPrice) {
        this.amountPurchased = amountPurchased;
        this.sellingPrice = sellingPrice;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmountPurchased() {
        return this.amountPurchased;
    }

    public void setAmountPurchased(Integer amountPurchased) {
        this.amountPurchased = amountPurchased;
    }

    public Float getSellingPrice() {
        return this.sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderHistory getOrderHistory() {
        return this.orderHistory;
    }

    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(amountPurchased, order.amountPurchased) && Objects.equals(sellingPrice, order.sellingPrice) && Objects.equals(product, order.product) && Objects.equals(orderHistory, order.orderHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amountPurchased, sellingPrice, product, orderHistory);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", amountPurchased='" + getAmountPurchased() + "'" +
            ", sellingPrice='" + getSellingPrice() + "'" +
            ", product='" + getProduct() + "'" +
            ", orderHistory='" + getOrderHistory() + "'" +
            "}";
    }

}
