package pl.polsl.ProjektTab.Photo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.polsl.ProjektTab.ProductInfo.ProductInfo;

@Entity
@Table(name = "Photos")
public class Photo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private ProductInfo productInfo;

    public Photo() {
    }

    public Photo(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ProductInfo getProductInfo() {
        return this.productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Photo)) {
            return false;
        }
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) && Objects.equals(photoUrl, photo.photoUrl) && Objects.equals(productInfo, photo.productInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoUrl, productInfo);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", photoUrl='" + getPhotoUrl() + "'" +
            ", productInfo='" + getProductInfo() + "'" +
            "}";
    }

    
}
