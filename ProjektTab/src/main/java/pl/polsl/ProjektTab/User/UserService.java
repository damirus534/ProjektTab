package pl.polsl.ProjektTab.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.UserNotFoundException;
import pl.polsl.ProjektTab.Cart.Cart;
import pl.polsl.ProjektTab.OrderHistory.OrderHistory;

@Service
public class UserService {
 
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User editUser(Long userId, User user) {
        User editedUser = userRepository.findById(userId).orElseThrow(() ->
            new UserNotFoundException(userId)
        );
        if(user.getStatus() != null)
            editedUser.setStatus(user.getStatus());
        if(user.getLogin() != null)
            editedUser.setLogin(user.getLogin());
        if(user.getPassword() != null)
            editedUser.setPassword(user.getPassword());
        if(user.getAddress() != null)
            editedUser.setAddress(user.getAddress());
        return userRepository.save(editedUser);
    }

    public User deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
            new UserNotFoundException(userId)
        );
        for(Cart cart : user.getCarts()) {
            cart.setUser(null);
        }
        for(OrderHistory orderHistory : user.getOrderHistory()) {
            orderHistory.setUser(null);
        }
        userRepository.delete(user);
        return user;
    }
    
}
