package pl.polsl.ProjektTab.Product;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pl.polsl.ProjektTab.Cart.Cart;
import pl.polsl.ProjektTab.Order.Order;
import pl.polsl.ProjektTab.ProductInfo.ProductInfo;

@Entity
@Table(name = "Products")
public class Product implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String size;
    private Integer amountAvailable;

    @ManyToOne
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private ProductInfo productInfo;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Cart> carts;

    public Product() {
    }

    public Product(String size, Integer amountAvailable) {
        this.size = size;
        this.amountAvailable = amountAvailable;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getAmountAvailable() {
        return this.amountAvailable;
    }

    public void setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public ProductInfo getProductInfo() {
        return this.productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Cart> getCarts() {
        return this.carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(size, product.size) && Objects.equals(amountAvailable, product.amountAvailable) && Objects.equals(productInfo, product.productInfo) && Objects.equals(orders, product.orders) && Objects.equals(carts, product.carts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, amountAvailable, productInfo, orders, carts);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", size='" + getSize() + "'" +
            ", amountAvailable='" + getAmountAvailable() + "'" +
            ", productInfo='" + getProductInfo() + "'" +
            ", orders='" + getOrders() + "'" +
            ", carts='" + getCarts() + "'" +
            "}";
    }

}
