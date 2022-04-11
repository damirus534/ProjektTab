package pl.polsl.ProjektTab.OrderHistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-history")
public class OrderHistoryController {
    
    private final OrderHistoryService orderHistoryService;

    @Autowired
    public OrderHistoryController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }

    @GetMapping
    public List<OrderHistory> getOrders() {
        return orderHistoryService.getOrderHistory();
    }

    @PostMapping
    public OrderHistory addOrder(@RequestBody OrderHistory orderHistory) {
        return orderHistoryService.addOrderHistory(orderHistory);
    }

    @PutMapping("/{orderHistoryId}/user/{userId}")
    public OrderHistory assignUserToOrderHistory(@PathVariable Long orderHistoryId, @PathVariable Long userId) {
        return orderHistoryService.assignUserToOrderHistory(orderHistoryId, userId);
    }

    @PutMapping("/edit/{orderHistoryId}")
    public OrderHistory editOrderHistory(@PathVariable Long orderHistoryId, @RequestBody OrderHistory orderHistory) {
        return orderHistoryService.editOrderHistory(orderHistoryId, orderHistory);
    }

    @DeleteMapping("/delete/{orderHistoryId}")
    public OrderHistory deleteOrderHistory(@PathVariable Long orderHistoryId) {
        return orderHistoryService.deleteOrderHistory(orderHistoryId);
    }

}
