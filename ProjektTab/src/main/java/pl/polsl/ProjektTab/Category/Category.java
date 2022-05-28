package pl.polsl.ProjektTab.Category;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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

    @Lob
    private String categoryName;
    private Boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<ProductInfo> productInfo;

    public Category() {
    }

    public Category(Long id, String categoryName, Boolean isActive, List<ProductInfo> productInfo) {
        this.id = id;
        this.categoryName = categoryName;
        this.isActive = isActive;
        this.productInfo = productInfo;
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

    public Boolean isIsActive() {
        return this.isActive;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<ProductInfo> getProductInfo() {
        return this.productInfo;
    }

    public void setProductInfo(List<ProductInfo> productInfo) {
        this.productInfo = productInfo;
    }

    public Category id(Long id) {
        setId(id);
        return this;
    }

    public Category categoryName(String categoryName) {
        setCategoryName(categoryName);
        return this;
    }

    public Category isActive(Boolean isActive) {
        setIsActive(isActive);
        return this;
    }

    public Category productInfo(List<ProductInfo> productInfo) {
        setProductInfo(productInfo);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(categoryName, category.categoryName) && Objects.equals(isActive, category.isActive) && Objects.equals(productInfo, category.productInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName, isActive, productInfo);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", productInfo='" + getProductInfo() + "'" +
            "}";
    }

   
}
