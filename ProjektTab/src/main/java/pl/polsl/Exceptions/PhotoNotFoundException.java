package pl.polsl.Exceptions;

public class PhotoNotFoundException extends RuntimeException {

    public PhotoNotFoundException(String message) {
        super(message);
    }
        
    public PhotoNotFoundException(Long id) {
        super("Photo with id of " + id + " does not exist.");
    }
    
}
