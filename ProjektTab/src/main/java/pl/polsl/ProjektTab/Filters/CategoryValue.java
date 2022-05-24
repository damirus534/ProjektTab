package pl.polsl.ProjektTab.Filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class CategoryValue {
    private Long id;
    private String description;
    private String productName;
    private List<String> photoUrl;

    public CategoryValue(Long id, String description, String productName,String photoURL) {
        this.id = id;
        this.description = description;
        this.productName = productName;
        this.photoUrl=new ArrayList<>();
        this.photoUrl.add(photoURL);
    }

    public CategoryValue() {
    }

    public CategoryValue(Long id, String description, String productName, List<String> photoUrl) {
        this.id = id;
        this.description = description;
        this.productName = productName;
        this.photoUrl = photoUrl;
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

    public List<String> getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(List<String> photoUrl) {
        this.photoUrl = photoUrl;
    }

    public CategoryValue id(Long id) {
        setId(id);
        return this;
    }

    public CategoryValue description(String description) {
        setDescription(description);
        return this;
    }

    public CategoryValue productName(String productName) {
        setProductName(productName);
        return this;
    }

    public CategoryValue photoUrl(List<String> photoUrl) {
        setPhotoUrl(photoUrl);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CategoryValue)) {
            return false;
        }
        CategoryValue categoryValue = (CategoryValue) o;
        return Objects.equals(id, categoryValue.id) && Objects.equals(description, categoryValue.description) && Objects.equals(productName, categoryValue.productName) && Objects.equals(photoUrl, categoryValue.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, productName, photoUrl);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", productName='" + getProductName() + "'" +
            ", photoUrl='" + getPhotoUrl() + "'" +
            "}";
    }
}
