package pl.polsl.ProjektTab.Api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.polsl.ProjektTab.Model.Users;
import pl.polsl.ProjektTab.Service.UsersService;

import java.util.List;

@RequestMapping("users")
@RestController
public class UsersController {

    private final UsersService userService;

    @Autowired
    public UsersController(UsersService usersService){
        this.userService = usersService;
    }
    @PostMapping
    public void addUser(@RequestBody Users user){
        userService.addUser(user);
    }

    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

}
