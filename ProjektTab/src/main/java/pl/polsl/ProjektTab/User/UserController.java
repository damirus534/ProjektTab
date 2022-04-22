package pl.polsl.ProjektTab.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<User> getUsers() {
        return userService.getUsers();
    } 

    // not sure if POST request is correct, but angular's HttpClient does not allow body with GET method
    @PostMapping("/verify")
    public boolean verifyLoginCredentials(@RequestBody User user) {
        return userService.verifyLoginCredentials(user);
    }

    @PostMapping("/exists")
    public boolean isUserExists(@RequestBody User user) {
        return userService.isUserExists(user);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/edit/{userId}")
    public User editUser(@PathVariable Long userId, @RequestBody User user) {
        return userService.editUser(userId, user);
    }

    @DeleteMapping("/delete/{userId}")
    public User deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
    
}
