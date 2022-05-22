package pl.polsl.ProjektTab.Cart;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.CartNotFoundException;
import pl.polsl.Exceptions.ProductNotFoundException;
import pl.polsl.Exceptions.UserNotFoundException;
import pl.polsl.ProjektTab.ProjektTabApplication;
import pl.polsl.ProjektTab.Filters.CartsItem;
import pl.polsl.ProjektTab.Product.Product;
import pl.polsl.ProjektTab.Product.ProductRepository;
import pl.polsl.ProjektTab.User.User;
import pl.polsl.ProjektTab.User.UserRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart assignUserToCart(Long cartId, Long userId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() ->
            new CartNotFoundException(cartId)
        );
        User user = userRepository.findById(userId).orElseThrow(() ->
            new UserNotFoundException(userId)
        );
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    public void updateCartAmount(Integer amount,Long id){
        cartRepository.updateCartAmount(amount,id);
    }

    public Cart addToCart(Long productId, Long userId, Integer amount){
        Long cartId = cartRepository.findCartIdByProductIdAndUserId(productId, userId);
        Logger logger= LoggerFactory.getLogger(ProjektTabApplication.class);    // ?????

        Cart cart = new Cart();
        if(cartId != null) {
            cart = cartRepository.getById(cartId);
            updateCartAmount(amount, cartId);
            logger.info(cart.toString());
            return cart;
        } else {
            User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(userId)
            );
            cart.setUser(user);
            Product product = productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException(productId)
            );
            cart.setProduct(product);
            cart.setAmount(amount);
            return cartRepository.save(cart);
        }
    }

    public Cart assignProductToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() ->
            new CartNotFoundException(cartId)
        );
        Product product = productRepository.findById(productId).orElseThrow(() ->
            new ProductNotFoundException(productId)
        );
        cart.setProduct(product);
        return cartRepository.save(cart);
    }

    public Cart editCart(Long cartId, Cart cart) {
        Cart editedCart = cartRepository.findById(cartId).orElseThrow(() ->
            new CartNotFoundException(cartId)
        );
        if(cart.getAmount() != null)
            editedCart.setAmount(cart.getAmount());
        return cartRepository.save(editedCart);
    }

    public Cart deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() ->
            new CartNotFoundException(cartId)
        );
        cartRepository.delete(cart);
        return cart;
    }

    public List<CartsItem> findCartItemByUserId(Long userId){
        return cartRepository.findCartByUserId(userId);
    }
    
}
