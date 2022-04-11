package pl.polsl.ProjektTab.Category;

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

import pl.polsl.ProjektTab.ProductInfo.ProductInfo;

@Entity
@Table(name = "Categories")
public class Category implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<ProductInfo> productInfo;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductInfo> getProductInfo() {
        return this.productInfo;
    }

    public void setProductInfo(List<ProductInfo> productInfo) {
        this.productInfo = productInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(categoryName, category.categoryName) && Objects.equals(productInfo, category.productInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName, productInfo);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", productInfo='" + getProductInfo() + "'" +
            "}";
    }

}
