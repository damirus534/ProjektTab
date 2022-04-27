package pl.polsl.ProjektTab.User;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    public String login(User user) {
        Optional<User> verifiedUser = userRepository.findOne(Example.of(user));
        if(verifiedUser.isPresent()) {
            try {
                Algorithm algorithm = Algorithm.HMAC256("sekret");
                return JWT.create()
                    .withClaim("name", verifiedUser.get().getLogin())
                    .withClaim("role", verifiedUser.get().getStatus())
                    .withIssuedAt(Date.from(Instant.now()))
                    .sign(algorithm);
            } catch (JWTCreationException e) {
                System.out.println(e);
            }
        }
        return null;
    }


    public boolean isUserExists(User user) {
        User checkUser = new User();
        checkUser.setLogin(user.getLogin());
        ExampleMatcher userMatcher = ExampleMatcher.matching()
            .withIgnorePaths("id");
        Example<User> userExample = Example.of(checkUser, userMatcher);
        return userRepository.exists(userExample);
    }

    public void addUser(User user) {
        userRepository.save(user); 
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
