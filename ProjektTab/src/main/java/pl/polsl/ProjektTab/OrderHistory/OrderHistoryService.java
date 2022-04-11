package pl.polsl.ProjektTab.OrderHistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.OrderHistoryNotFoundException;
import pl.polsl.Exceptions.UserNotFoundException;
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
}
