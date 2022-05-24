package pl.polsl.ProjektTab.OrderHistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.ProjektTab.Filters.AdminRaportFilterProfit;
import pl.polsl.ProjektTab.Filters.AdminRaportFilterReqBody;
import pl.polsl.ProjektTab.Filters.RaportFilter;
import pl.polsl.ProjektTab.Filters.UserRaportFilterReqBody;

@RestController
@RequestMapping("/order-history")
@CrossOrigin
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

    @PostMapping("/{userId}")
    public List<RaportFilter> getUserRaport(@PathVariable Long userId, @RequestBody UserRaportFilterReqBody reqBody){
        return orderHistoryService.getUserRaport(userId, reqBody.getBeginning(), reqBody.getEnding());
    }

    @PostMapping("/adminRaport")
    public List<AdminRaportFilterProfit> getAdminRaport(@RequestBody AdminRaportFilterReqBody reqBody){
        return orderHistoryService.getAdminRaport(reqBody.getCategoryId(), reqBody.getBeginning(), reqBody.getEnding(), reqBody.getRaportType());
    }

}
