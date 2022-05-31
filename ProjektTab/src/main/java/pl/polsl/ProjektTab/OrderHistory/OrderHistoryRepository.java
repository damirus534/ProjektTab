package pl.polsl.ProjektTab.OrderHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.polsl.ProjektTab.Filters.AdminRaportFilterProfit;
import pl.polsl.ProjektTab.Filters.RaportFilter;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    @Query("SELECT new pl.polsl.ProjektTab.Filters.RaportFilter(oh.date,pi.productName, o.amountPurchased, o.sellingPrice, p.size)FROM OrderHistory oh JOIN Order o ON o.orderHistory.id=oh.id JOIN Product p ON o.product.id=p.id JOIN ProductInfo pi ON p.productInfo.id=pi.id AND oh.user.id=?1 AND oh.date >= ?2 AND oh.date <= ?3")
    public List<RaportFilter> getUserRaport(Long userId, Date beginning, Date ending);

    @Query("SELECT new pl.polsl.ProjektTab.Filters.AdminRaportFilterProfit(oh.date,pi.productName, o.amountPurchased, o.sellingPrice, p.size,pi.buyingPrice, c.categoryName)FROM OrderHistory oh JOIN Order o ON o.orderHistory.id=oh.id JOIN Product p ON o.product.id=p.id JOIN ProductInfo pi ON p.productInfo.id=pi.id JOIN Category c ON pi.category.id=c.id")
    public List<AdminRaportFilterProfit> getAdminIncomeRaport();

    @Query("SELECT new pl.polsl.ProjektTab.Filters.AdminRaportFilterProfit(oh.date,pi.productName, o.amountPurchased, o.sellingPrice, p.size, pi.buyingPrice, c.categoryName)FROM OrderHistory oh JOIN Order o ON o.orderHistory.id=oh.id JOIN Product p ON o.product.id=p.id JOIN ProductInfo pi ON p.productInfo.id=pi.id JOIN Category c ON pi.category.id=c.id")
    public List<AdminRaportFilterProfit> getAdminProfitRaport();


    @Query("SELECT new pl.polsl.ProjektTab.Filters.AdminRaportFilterProfit(oh.date,pi.productName, o.amountPurchased, o.sellingPrice, p.size, pi.buyingPrice, c.categoryName)FROM OrderHistory oh JOIN Order o ON o.orderHistory.id=oh.id JOIN Product p ON o.product.id=p.id JOIN ProductInfo pi ON p.productInfo.id=pi.id JOIN Category c ON pi.category.id=c.id AND oh.date >= ?1 AND oh.date <= ?2")
    public List<AdminRaportFilterProfit> getAdminProfitRaportDate(Date beginning, Date ending);


    @Query("SELECT new pl.polsl.ProjektTab.Filters.AdminRaportFilterProfit(oh.date,pi.productName, o.amountPurchased, o.sellingPrice, p.size, pi.buyingPrice, c.categoryName)FROM OrderHistory oh JOIN Order o ON o.orderHistory.id=oh.id JOIN Product p ON o.product.id=p.id JOIN ProductInfo pi ON p.productInfo.id=pi.id JOIN Category c ON pi.category.id=c.id AND oh.date >= ?1 AND oh.date <= ?2")
    public List<AdminRaportFilterProfit> getAdminIncomeRaportDate(Date beginning, Date ending);


    @Query("SELECT new pl.polsl.ProjektTab.Filters.AdminRaportFilterProfit(oh.date,pi.productName, o.amountPurchased, o.sellingPrice, p.size, pi.buyingPrice, c.categoryName)FROM OrderHistory oh JOIN Order o ON o.orderHistory.id=oh.id JOIN Product p ON o.product.id=p.id JOIN ProductInfo pi ON p.productInfo.id=pi.id JOIN Category c ON pi.category.id=c.id AND pi.category.id=?1 AND oh.date >= ?2 AND oh.date <= ?3")
    public List<AdminRaportFilterProfit> getAdminIncomeRaportDateCategory(Long categoryId, Date beginning, Date ending);

    @Query("SELECT new pl.polsl.ProjektTab.Filters.AdminRaportFilterProfit(oh.date,pi.productName, o.amountPurchased, o.sellingPrice, p.size, pi.buyingPrice, c.categoryName)FROM OrderHistory oh JOIN Order o ON o.orderHistory.id=oh.id JOIN Product p ON o.product.id=p.id JOIN ProductInfo pi ON p.productInfo.id=pi.id JOIN Category c ON pi.category.id=c.id AND pi.category.id=?1 AND oh.date > ?2 AND oh.date < ?3")
    public List<AdminRaportFilterProfit> getAdminProfitRaportDateCategory(Long categoryId, Date beginning, Date ending);

    @Query("SELECT new pl.polsl.ProjektTab.Filters.AdminRaportFilterProfit(oh.date,pi.productName, o.amountPurchased, o.sellingPrice, p.size, pi.buyingPrice, c.categoryName)FROM OrderHistory oh JOIN Order o ON o.orderHistory.id=oh.id JOIN Product p ON o.product.id=p.id JOIN ProductInfo pi ON p.productInfo.id=pi.id JOIN Category c ON pi.category.id=c.id AND pi.category.id=?1")
    public List<AdminRaportFilterProfit> getAdminProfitRaportCategory(Long categoryId);

    @Query("SELECT new pl.polsl.ProjektTab.Filters.AdminRaportFilterProfit(oh.date,pi.productName, o.amountPurchased, o.sellingPrice, p.size, pi.buyingPrice, c.categoryName)FROM OrderHistory oh JOIN Order o ON o.orderHistory.id=oh.id JOIN Product p ON o.product.id=p.id JOIN ProductInfo pi ON p.productInfo.id=pi.id JOIN Category c ON pi.category.id=c.id AND pi.category.id=?1")
    public List<AdminRaportFilterProfit> getAdminIncomeRaportCategory(Long categoryId);

}
