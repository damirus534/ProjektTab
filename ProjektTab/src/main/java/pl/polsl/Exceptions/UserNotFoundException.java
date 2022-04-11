package pl.polsl.Exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
        
    public UserNotFoundException(Long id) {
        super("User with id of " + id + " does not exist.");
    }
    
}
