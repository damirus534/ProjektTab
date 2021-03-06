package pl.polsl.ProjektTab.OrderHistory;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.OrderHistoryNotFoundException;
import pl.polsl.Exceptions.UserNotFoundException;
import pl.polsl.ProjektTab.Filters.AdminRaportFilterProfit;
import pl.polsl.ProjektTab.Filters.RaportFilter;
import pl.polsl.ProjektTab.Filters.UserRaportFilterReqBody;
import pl.polsl.ProjektTab.Order.Order;
import pl.polsl.ProjektTab.User.User;
import pl.polsl.ProjektTab.User.UserRepository;

@Service
public class OrderHistoryService {
    
    private final OrderHistoryRepository orderHistoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderHistoryService(OrderHistoryRepository orderHistoryRepository, UserRepository userRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
        this.userRepository = userRepository;
    }

    public List<OrderHistory> getOrderHistory() {
        return orderHistoryRepository.findAll();
    }

    public OrderHistory addOrderHistory(OrderHistory orderHistory) {
        return orderHistoryRepository.save(orderHistory);
    }

    public OrderHistory assignUserToOrderHistory(Long orderHistoryId, Long userId) {
        OrderHistory orderHistory = orderHistoryRepository.findById(orderHistoryId).orElseThrow(() ->
            new OrderHistoryNotFoundException(orderHistoryId)
        );
        User user = userRepository.findById(userId).orElseThrow(() ->
            new UserNotFoundException(userId)
        );
        orderHistory.setUser(user);
        return orderHistoryRepository.save(orderHistory);
    }

    public OrderHistory editOrderHistory(Long orderHistoryId, OrderHistory orderHistory) {
        OrderHistory editedOrderHistory = orderHistoryRepository.findById(orderHistoryId).orElseThrow(() ->
            new OrderHistoryNotFoundException(orderHistoryId)
        );
        if(orderHistory.getDate() != null)
            editedOrderHistory.setDate(orderHistory.getDate());
        if(orderHistory.getTotalPrice() != null)
            editedOrderHistory.setTotalPrice(orderHistory.getTotalPrice());
        return orderHistoryRepository.save(editedOrderHistory);
    }

    public OrderHistory deleteOrderHistory(Long orderHistoryId) {
        OrderHistory orderHistory = orderHistoryRepository.findById(orderHistoryId).orElseThrow(() ->
            new OrderHistoryNotFoundException(orderHistoryId)
        );
        for(Order order : orderHistory.getOrders()) {
            order.setOrderHistory(null);
        }
        orderHistoryRepository.delete(orderHistory);
        return orderHistory;
    }

    public List<RaportFilter>getUserRaport(Long userId, Date beginning, Date ending){
        return orderHistoryRepository.getUserRaport(userId, beginning, ending);
    }

    public List<AdminRaportFilterProfit>getAdminRaport(Long categoryId, Date beginning, Date ending, Integer raportType){
        if(categoryId==null && beginning==null && ending==null && raportType==1){
            return orderHistoryRepository.getAdminIncomeRaport();
        } else if (categoryId==null && beginning==null && ending==null && raportType==2) {
            List<AdminRaportFilterProfit>CountProfit=orderHistoryRepository.getAdminProfitRaport();
            for(AdminRaportFilterProfit myUnit:CountProfit){
                myUnit.setOrderSellPrice(myUnit.getOrderSellPrice()-myUnit.getBuyingPrice());
            }
            return CountProfit;
        } else if(categoryId!=null && beginning==null && ending==null && raportType==1){
            return orderHistoryRepository.getAdminIncomeRaportCategory(categoryId);
        }else if(categoryId!=null && beginning==null && ending==null && raportType==2){
            List<AdminRaportFilterProfit>CountProfit=orderHistoryRepository.getAdminProfitRaportCategory(categoryId);
            for(AdminRaportFilterProfit myUnit:CountProfit){
                myUnit.setOrderSellPrice(myUnit.getOrderSellPrice()-myUnit.getBuyingPrice());
            }
            return CountProfit;
        }else if(categoryId==null && beginning!=null && ending!=null && raportType==1){
            return orderHistoryRepository.getAdminIncomeRaportDate(beginning, ending);
        }else if(categoryId==null && beginning!=null && ending!=null && raportType==2){
            List<AdminRaportFilterProfit>CountProfit=orderHistoryRepository.getAdminProfitRaportDate(beginning, ending);
            for(AdminRaportFilterProfit myUnit:CountProfit){
                myUnit.setOrderSellPrice(myUnit.getOrderSellPrice()-myUnit.getBuyingPrice());
            }
            return CountProfit;
        }else if(categoryId!=null && beginning!=null && ending!=null && raportType==1){
            return orderHistoryRepository.getAdminIncomeRaportDateCategory(categoryId, beginning, ending);
        }else if(categoryId!=null && beginning!=null && ending!=null && raportType==2) {
            List<AdminRaportFilterProfit>CountProfit=orderHistoryRepository.getAdminProfitRaportDateCategory(categoryId, beginning, ending);
            for(AdminRaportFilterProfit myUnit:CountProfit){
                myUnit.setOrderSellPrice(myUnit.getOrderSellPrice()-myUnit.getBuyingPrice());
            }
            return CountProfit;
        }else{
            return null;
        }

    }

}
