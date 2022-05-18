package pl.polsl.ProjektTab.Filters;

import java.util.Objects;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReturnValue {
    private Long id;
    private String description;
    private String productName;
    private String photoUrl;
    private Float prize;


    public ReturnValue(Long id, String description, String productName, String photoUrl, Float prize) {
        this.id = id;
        this.description = description;
        this.productName = productName;
        this.photoUrl = photoUrl;
        this.prize = prize;
    }

    public ReturnValue() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Float getPrize() {
        return this.prize;
    }

    public void setPrize(Float prize) {
        this.prize = prize;
    }

    public ReturnValue id(Long id) {
        setId(id);
        return this;
    }

    public ReturnValue description(String description) {
        setDescription(description);
        return this;
    }

    public ReturnValue productName(String productName) {
        setProductName(productName);
        return this;
    }

    public ReturnValue photoUrl(String photoUrl) {
        setPhotoUrl(photoUrl);
        return this;
    }

    public ReturnValue prize(Float prize) {
        setPrize(prize);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ReturnValue)) {
            return false;
        }
        ReturnValue returnValue = (ReturnValue) o;
        return Objects.equals(id, returnValue.id) && Objects.equals(description, returnValue.description) && Objects.equals(productName, returnValue.productName) && Objects.equals(photoUrl, returnValue.photoUrl) && Objects.equals(prize, returnValue.prize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, productName, photoUrl, prize);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", productName='" + getProductName() + "'" +
            ", photoUrl='" + getPhotoUrl() + "'" +
            ", prize='" + getPrize() + "'" +
            "}";
    }

}
