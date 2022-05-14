package pl.polsl.ProjektTab.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.OrderHistoryNotFoundException;
import pl.polsl.Exceptions.OrderNotFoundException;
import pl.polsl.Exceptions.ProductNotFoundException;
import pl.polsl.ProjektTab.Cart.Cart;
import pl.polsl.ProjektTab.Cart.CartRepository;
import pl.polsl.ProjektTab.OrderHistory.OrderHistory;
import pl.polsl.ProjektTab.OrderHistory.OrderHistoryRepository;
import pl.polsl.ProjektTab.Product.Product;
import pl.polsl.ProjektTab.Product.ProductRepository;
import pl.polsl.ProjektTab.User.UserRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderHistoryRepository orderHistoeyRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
                        OrderHistoryRepository orderHistoeyRepository, CartRepository cartRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderHistoeyRepository = orderHistoeyRepository;
        this.cartRepository=cartRepository;
        this.userRepository=userRepository;
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
    public float addOrderById(Long userID){
        Logger as=LoggerFactory.getLogger(pl.polsl.ProjektTab.ProjektTabApplication.class);
        as.info(userID.toString());
        List<Cart> items=cartRepository.foundAllItemFromCart(userID);
        float totalPrice=0;
        List<Order> orderList=new ArrayList<>();
        for(Cart item:items) {
            Order a = new Order();
            a.setProduct(item.getProduct());
            a.setAmountPurchased(item.getAmount());
            a.setSellingPrice(a.getProduct().getProductInfo().getSellingPrice());

            addOrder(a);
            orderList.add(a);
            totalPrice += (item.getAmount() * a.getProduct().getProductInfo().getSellingPrice());
        }


        java.sql.Date date= new java.sql.Date(Calendar.getInstance().getTime().getTime());
        OrderHistory orderHistory=new OrderHistory(date,totalPrice,userRepository.getById(userID),orderList);
        orderHistory=orderHistoeyRepository.save(orderHistory);

        /*for(Order order:orderList){
            order.setOrderHistory(orderHistory);
        }*/
        orderRepository.saveAll(orderList);

        cartRepository.deleteByUserId(userID);
        return totalPrice;
    }
    
}
