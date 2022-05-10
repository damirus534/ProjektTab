package pl.polsl.ProjektTab.Photo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("SELECT p from Photo p WHERE p.productInfo.id = ?1")
    List<Photo> findByProductInfoId(Long productInfoId);
    
}
