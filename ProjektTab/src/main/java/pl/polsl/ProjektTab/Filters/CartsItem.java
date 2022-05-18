package pl.polsl.ProjektTab.Filters;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartsItem {
    Long id;
    String name;
    String size;
    String imageUrl;
    Float price;
    Integer amount;





    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public CartsItem id(Long id) {
        setId(id);
        return this;
    }

    public CartsItem name(String name) {
        setName(name);
        return this;
    }

    public CartsItem size(String size) {
        setSize(size);
        return this;
    }

    public CartsItem imageUrl(String imageUrl) {
        setImageUrl(imageUrl);
        return this;
    }

    public CartsItem price(Float price) {
        setPrice(price);
        return this;
    }

    public CartsItem amount(Integer amount) {
        setAmount(amount);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CartsItem)) {
            return false;
        }
        CartsItem cartsItem = (CartsItem) o;
        return Objects.equals(id, cartsItem.id) && Objects.equals(name, cartsItem.name) && Objects.equals(size, cartsItem.size) && Objects.equals(imageUrl, cartsItem.imageUrl) && Objects.equals(price, cartsItem.price) && Objects.equals(amount, cartsItem.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, size, imageUrl, price, amount);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", size='" + getSize() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", price='" + getPrice() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }

}
