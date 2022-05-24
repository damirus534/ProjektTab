package pl.polsl.ProjektTab.OrderHistory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pl.polsl.ProjektTab.Order.Order;
import pl.polsl.ProjektTab.User.User;

@Entity
@Table(name = "OrderHistory")
public class OrderHistory implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private Float totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "orderHistory")
    private List<Order> orders;

    public OrderHistory() {
    }

    public OrderHistory(Date date, Float totalPrice) {
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public OrderHistory(Date date, Float totalPrice, User user, List<Order> orders) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.user = user;
        this.orders = orders;
    }

    public OrderHistory(Long id, Date date, Float totalPrice, User user) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.user = user;
    }

    public OrderHistory(Long id, Date date, Float totalPrice, User user, List<Order> orders) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.user = user;
        this.orders = orders;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderHistory)) {
            return false;
        }
        OrderHistory orderHistory = (OrderHistory) o;
        return Objects.equals(id, orderHistory.id) && Objects.equals(date, orderHistory.date) && Objects.equals(totalPrice, orderHistory.totalPrice) && Objects.equals(user, orderHistory.user) && Objects.equals(orders, orderHistory.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, totalPrice, user, orders);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", date='" + getDate() + "'" +
            ", totalPrice='" + getTotalPrice() + "'" +
            ", user='" + getUser() + "'" +
            ", orders='" + getOrders() + "'" +
            "}";
    }

}
