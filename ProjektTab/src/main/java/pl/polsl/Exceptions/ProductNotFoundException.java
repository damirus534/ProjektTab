package pl.polsl.Exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
        
    public ProductNotFoundException(Long id) {
        super("Product with id of " + id + " does not exist.");
    }
    
}
