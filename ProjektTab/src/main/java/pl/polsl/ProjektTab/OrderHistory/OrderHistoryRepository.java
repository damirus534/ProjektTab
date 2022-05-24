package pl.polsl.ProjektTab.OrderHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.polsl.ProjektTab.Filters.RaportFilter;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    @Query("SELECT new pl.polsl.ProjektTab.Filters.RaportFilter(oh.date,pi.productName, o.amountPurchased, o.sellingPrice, p.size)FROM OrderHistory oh JOIN Order o ON o.orderHistory.id=oh.id JOIN Product p ON o.product.id=p.id JOIN ProductInfo pi ON p.productInfo.id=pi.id AND oh.user.id=?1 AND oh.date > ?2 AND oh.date < ?3")
    public List<RaportFilter> getUserRaport(Long userId, Date beginning, Date ending);


}
