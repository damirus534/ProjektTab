package pl.polsl.ProjektTab.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.polsl.ProjektTab.Filters.ProductOff;
import pl.polsl.ProjektTab.Filters.ReturnValue;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
        @Query("SELECT new pl.polsl.ProjektTab.Filters.ReturnValue(c.id,c.description,c.productName,p.photoUrl,p.productInfo.sellingPrice) FROM ProductInfo c JOIN c.photos p ON  c.id=p.productInfo.id group by c.id")
        public List<ReturnValue> getSeperatedProduct();


        @Query("SELECT new pl.polsl.ProjektTab.Filters.ReturnValue(c.id,c.description,c.productName,p.photoUrl,p.productInfo.sellingPrice) FROM ProductInfo c JOIN c.photos p ON  c.id=p.productInfo.id AND c.id=?1 group by c.id")
        public List<ReturnValue> getFilteredProduct(Long categoryId);

        @Query(value = "SELECT new pl.polsl.ProjektTab.Filters.ProductOff(p.id,p.amountAvailable,p.size) FROM Product p where p.productInfo.id=?1 ORDER BY p.size ASC ")
        public List<ProductOff> getProductByCategoryId(Long id);

}
