package pl.polsl.ProjektTab.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.ProjektTab.Model.Uzytkownicy;

@Repository
public interface UsersDao extends JpaRepository<Uzytkownicy, Long> {

}
