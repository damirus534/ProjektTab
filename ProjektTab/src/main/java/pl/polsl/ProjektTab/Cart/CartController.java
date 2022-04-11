package pl.polsl.ProjektTab.Cart;

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
@RequestMapping("/carts")
public class CartController {
    
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getCarts() {
        return cartService.getCarts();
    }

    @PostMapping
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @PutMapping("/{cartId}/user/{userId}")
    public Cart assingUserToCart(
        @PathVariable Long cartId, 
        @PathVariable Long userId
    ) {
        return cartService.assignUserToCart(cartId, userId);
    }

    @PutMapping("/{cartId}/product/{productId}")
    public Cart assingProductToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return cartService.assignProductToCart(cartId, productId);
    }

    @PutMapping("/edit/{cartId}")
    public Cart editCart(@PathVariable Long cartId, @RequestBody Cart cart) {
        return cartService.editCart(cartId, cart);
    }

    @DeleteMapping("/delete/{cartId}")
    public Cart deleteCart(@PathVariable Long cartId) {
        return cartService.deleteCart(cartId);
    }

}
