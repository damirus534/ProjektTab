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
public class ProductOff {
    Long id;
    Integer amountAvailable;
    String size;




    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmountAvailable() {
        return this.amountAvailable;
    }

    public void setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ProductOff id(Long id) {
        setId(id);
        return this;
    }

    public ProductOff amountAvailable(Integer amountAvailable) {
        setAmountAvailable(amountAvailable);
        return this;
    }

    public ProductOff size(String size) {
        setSize(size);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductOff)) {
            return false;
        }
        ProductOff productOff = (ProductOff) o;
        return Objects.equals(id, productOff.id) && Objects.equals(amountAvailable, productOff.amountAvailable) && Objects.equals(size, productOff.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amountAvailable, size);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", amountAvailable='" + getAmountAvailable() + "'" +
            ", size='" + getSize() + "'" +
            "}";
    }

}
