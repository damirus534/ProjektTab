package pl.polsl.ProjektTab.OrderHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.ProjektTab.User.User;

import java.util.Date;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

}
