package pl.polsl.ProjektTab.Cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.polsl.ProjektTab.Filters.CartsItem;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PutMapping ("{userId}/product/{productId}/amount/{amount}")
    public Cart addToCart(@PathVariable Long userId, @PathVariable Long productId, @PathVariable Integer amount) {
        return cartService.addToCart(productId, userId, amount);
    }

    @GetMapping("/users")
    public List<CartsItem> getCartsItemByUserId(@RequestParam Long id) {
        return cartService.findCartItemByUserId(id);
    }
}
