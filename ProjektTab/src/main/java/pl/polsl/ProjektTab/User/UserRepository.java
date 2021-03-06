package pl.polsl.ProjektTab.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u from User u WHERE u.login = ?1")
    public User findUserByLogin(String userEmail);

}
