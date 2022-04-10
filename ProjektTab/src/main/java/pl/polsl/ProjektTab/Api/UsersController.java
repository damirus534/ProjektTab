package pl.polsl.ProjektTab.Api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.polsl.ProjektTab.Dao.UsersDao;
import pl.polsl.ProjektTab.Model.Uzytkownicy;

@RequestMapping("users")
@RestController
public class UsersController {

    @Autowired
    private UsersDao usersDao;

    @PostMapping
    public void addUser(@RequestBody Uzytkownicy user){
        usersDao.save(user);
    }

}
