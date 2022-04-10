package pl.polsl.Exceptions;

public class OrderHistoryNotFoundException extends RuntimeException {

    public OrderHistoryNotFoundException(String message) {
        super(message);
    }
    
    public OrderHistoryNotFoundException(Long id) {
        super("OrderHistory with id of " + id + " does not exist.");
    }
    
}
