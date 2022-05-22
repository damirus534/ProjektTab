package pl.polsl.ProjektTab.User;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200") // necessary for request from frontend to work
public class UserController {
        
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return userService.getUsers();
    } 

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/exists")
    public ResponseEntity<Boolean> isUserExists(@RequestBody User user) {
        return userService.isUserExists(user);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<User> editUser(@RequestHeader(value = "Authorization") String token, @PathVariable Long userId, @RequestBody User user) {
        return userService.editUser(token.substring(7), userId, user);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<User> deleteUser(@RequestHeader(value = "Authorization") String token, @PathVariable Long userId) {
        return userService.deleteUser(token.substring(7), userId);
    }
    
}
