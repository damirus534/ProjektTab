package pl.polsl.Exceptions;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(String message) {
        super(message);
    }
    
    public CartNotFoundException(Long id) {
        super("Cart with id of " + id + " does not exist.");
    }
    
}
