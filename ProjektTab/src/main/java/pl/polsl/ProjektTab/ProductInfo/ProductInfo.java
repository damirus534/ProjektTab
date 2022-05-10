package pl.polsl.ProjektTab.ProductInfo;

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

import pl.polsl.ProjektTab.Category.Category;
import pl.polsl.ProjektTab.Photo.Photo;
import pl.polsl.ProjektTab.Product.Product;

@Entity
@Table(name = "ProductInfo")
public class ProductInfo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String description;
    private Float buyingPrice;
    private Float sellingPrice;
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "productInfo")
    private List<Product> products;

    @JsonIgnore
    @OneToMany(mappedBy = "productInfo")
    private List<Photo> photos;

    public ProductInfo() {
    }

    public ProductInfo(String productName, String description, Float buyingPrice, Float sellingPrice, Boolean isActive) {
        this.productName = productName;
        this.description = description;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.isActive = isActive;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getBuyingPrice() {
        return this.buyingPrice;
    }

    public void setBuyingPrice(Float buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Float getSellingPrice() {
        return this.sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Photo> getPhotos() {
        return this.photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductInfo)) {
            return false;
        }
        ProductInfo productInfo = (ProductInfo) o;
        return Objects.equals(id, productInfo.id) && Objects.equals(productName, productInfo.productName) && Objects.equals(description, productInfo.description) && Objects.equals(buyingPrice, productInfo.buyingPrice) && Objects.equals(sellingPrice, productInfo.sellingPrice) && Objects.equals(isActive, productInfo.isActive) && Objects.equals(category, productInfo.category) && Objects.equals(products, productInfo.products) && Objects.equals(photos, productInfo.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, description, buyingPrice, sellingPrice, isActive, category, products, photos);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", description='" + getDescription() + "'" +
            ", buyingPrice='" + getBuyingPrice() + "'" +
            ", sellingPrice='" + getSellingPrice() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", category='" + getCategory() + "'" +
            ", products='" + getProducts() + "'" +
            ", photos='" + getPhotos() + "'" +
            "}";
    }


}
