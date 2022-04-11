package pl.polsl.Exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }
        
    public OrderNotFoundException(Long id) {
        super("Order with id of " + id + " does not exist.");
    }
    
}
