package pl.polsl.ProjektTab.Photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Query(value = "SELECT p.photoUrl FROM Photo p where p.productInfo.id=?1")
    List<String> findAllPhotos(Long id);
}
