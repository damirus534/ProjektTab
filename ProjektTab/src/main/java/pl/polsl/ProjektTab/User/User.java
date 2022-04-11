package pl.polsl.ProjektTab.User;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pl.polsl.ProjektTab.Cart.Cart;
import pl.polsl.ProjektTab.OrderHistory.OrderHistory;

@Entity
@Table(name = "Users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private String login;
    private String password;
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Cart> carts;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<OrderHistory> orderHistory;

    public User() {
    }

    public User(String status, String login, String password, String address) {
        this.status = status;
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Cart> getCarts() {
        return this.carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<OrderHistory> getOrderHistory() {
        return this.orderHistory;
    }

    public void setOrderHistory(List<OrderHistory> orderHistory) {
        this.orderHistory = orderHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(status, user.status) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(address, user.address) && Objects.equals(carts, user.carts) && Objects.equals(orderHistory, user.orderHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, login, password, address, carts, orderHistory);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", status='" + getStatus() + "'" +
            ", login='" + getLogin() + "'" +
            ", password='" + getPassword() + "'" +
            ", address='" + getAddress() + "'" +
            ", carts='" + getCarts() + "'" +
            ", orderHistory='" + getOrderHistory() + "'" +
            "}";
    }
    
}
