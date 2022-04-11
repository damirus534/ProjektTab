package pl.polsl.ProjektTab.Order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.OrderHistoryNotFoundException;
import pl.polsl.Exceptions.OrderNotFoundException;
import pl.polsl.Exceptions.ProductNotFoundException;
import pl.polsl.ProjektTab.OrderHistory.OrderHistory;
import pl.polsl.ProjektTab.OrderHistory.OrderHistoryRepository;
import pl.polsl.ProjektTab.Product.Product;
import pl.polsl.ProjektTab.Product.ProductRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderHistoryRepository orderHistoeyRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
            OrderHistoryRepository orderHistoeyRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderHistoeyRepository = orderHistoeyRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order assignProductToOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
            new OrderNotFoundException(orderId)
        );
        Product product = productRepository.findById(productId).orElseThrow(() ->
            new ProductNotFoundException(productId)
        );
        order.setProduct(product);
        return orderRepository.save(order);
    }

    public Order assignOrderHistoryToOrder(Long orderId, Long historyOrderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
            new OrderNotFoundException(orderId)
        );
        OrderHistory orderHistory = orderHistoeyRepository.findById(historyOrderId).orElseThrow(() ->
            new OrderHistoryNotFoundException(historyOrderId)
        );
        order.setOrderHistory(orderHistory);
        return orderRepository.save(order);
    }

    public Order editOrder(Long orderId, Order order) {
        Order editedOrder = orderRepository.findById(orderId).orElseThrow(() ->
            new OrderNotFoundException(orderId)
        );
        if(order.getAmountPurchased() != null)
            editedOrder.setAmountPurchased(order.getAmountPurchased());
        if(order.getSellingPrice() != null)
            editedOrder.setSellingPrice(order.getSellingPrice());
        return orderRepository.save(editedOrder);
    }

    public Order deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
            new OrderNotFoundException(orderId)
        );
        orderRepository.delete(order);
        return order;
    }
    
}
