package pl.polsl.ProjektTab.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product p WHERE p.productInfo.id = ?1")
    List<Product> findByProductInfoId(Long productInfoId);
    
}
