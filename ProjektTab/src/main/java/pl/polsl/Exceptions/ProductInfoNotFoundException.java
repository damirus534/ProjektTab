package pl.polsl.Exceptions;

public class ProductInfoNotFoundException extends RuntimeException {

    public ProductInfoNotFoundException(String message) {
        super(message);
    }
    
    public ProductInfoNotFoundException(Long id) {
        super("ProductInfo with id of " + id + " does not exist.");
    }
    
}
