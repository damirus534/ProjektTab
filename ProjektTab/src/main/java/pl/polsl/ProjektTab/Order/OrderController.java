package pl.polsl.ProjektTab.Order;

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
@RequestMapping("/orders")
public class OrderController {
    
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PutMapping("/{orderId}/product/{productId}")
    public Order assignProductToOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        return orderService.assignProductToOrder(orderId, productId);
    }

    @PutMapping("/{orderId}/order-history/{historyOrderId}")
    public Order assignOrderHistoryToOrder(@PathVariable Long orderId, @PathVariable Long historyOrderId) {
        return orderService.assignOrderHistoryToOrder(orderId, historyOrderId);
    }

    @PutMapping("/edit/{orderId}")
    public Order editOrder(@PathVariable Long orderId, @RequestBody Order order) {
        return orderService.editOrder(orderId, order);
    }

    @DeleteMapping("/delete/{orderId}")
    public Order deleteOrder(@PathVariable Long orderId) {
        return orderService.deleteOrder(orderId);
    }

}
