package pl.polsl.ProjektTab.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.ProjektTab.Filters.CartsItem;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT new pl.polsl.ProjektTab.Filters.CartsItem(d.id, a.productName, b.size, c.photoUrl, a.sellingPrice, d.amount, b.amountAvailable) from Cart d JOIN Product b On d.user.id=?1 AND d.product.id=b.id JOIN ProductInfo a ON b.productInfo.id=a.id JOIN Photo c ON c.productInfo.id=a.id GROUP BY d.id")
        List<CartsItem> findCartByUserId(Long id);

    @Query(value = "SELECT c.id FROM Cart c WHERE c.product.id=?1 AND c.user.id=?2 GROUP BY c.id")
        Long findCartIdByProductIdAndUserId(Long ProductId, Long UserId);

    @Transactional
    @Modifying
    @Query("Update Cart c SET c.amount=c.amount+:add WHERE c.id=:cartId")
        void updateCartAmount(@Param("add")Integer amount, @Param("cartId")Long id);

    @Transactional
    @Modifying
    @Query("delete from Cart c where c.user.id=:id")
        void deleteByUserId(@Param("id")Long id);

    @Query(value = "SELECT c FROM Cart c WHERE c.user.id=:id")
        List<Cart> findAllItemsOfUser(@Param("id")Long id);
}
