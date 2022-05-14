package pl.polsl.ProjektTab.User;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.polsl.Exceptions.UserNotFoundException;
import pl.polsl.ProjektTab.Reference;
import pl.polsl.ProjektTab.Cart.Cart;
import pl.polsl.ProjektTab.OrderHistory.OrderHistory;

@Service
public class UserService {
 
    private final UserRepository userRepository;
    private final Algorithm algorithm = Algorithm.HMAC256(Reference.JWTSecret);
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<Map<String, String>> login(User user) {
        HashMap<String, String> map = new HashMap<>();
        Optional<User> verifiedUser = userRepository.findOne(Example.of(user));
        if(verifiedUser.isPresent()) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(Reference.JWTSecret);
                String token = JWT.create()
                    .withClaim("id", verifiedUser.get().getId())
                    .withClaim("role", verifiedUser.get().getStatus())
                    .withIssuedAt(Date.from(Instant.now()))
                    .withExpiresAt(Date.from(Instant.now().plusSeconds(60 * 60 * 24)))  // 24 hours from the issuing time
                    .sign(algorithm);
                map.put("JWT_TOKEN", token);
                return ResponseEntity.ok(map);
            } catch (JWTCreationException e) {
                System.out.println(e);
            }
        }
        return null;
    }


    public ResponseEntity<Boolean> isUserExists(User user) {
        User checkUser = new User();
        checkUser.setLogin(user.getLogin());
        ExampleMatcher userMatcher = ExampleMatcher.matching()
            .withIgnorePaths("id");
        Example<User> userExample = Example.of(checkUser, userMatcher);
        return ResponseEntity.ok(userRepository.exists(userExample));
    }

    public ResponseEntity<User> addUser(User user) {
        return ResponseEntity.ok(userRepository.save(user)); 
    }

    public ResponseEntity<User> editUser(String token, Long userId, User user) {
        try {
            verifier.verify(token);
            
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
            return ResponseEntity.ok(userRepository.save(editedUser));
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<User> deleteUser(String token, Long userId) {
        try {
            verifier.verify(token);
            
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
            return ResponseEntity.ok(user);
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
