package pl.polsl.ProjektTab.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.polsl.ProjektTab.Dao.UsersDao;
import pl.polsl.ProjektTab.Model.Users;

import java.util.List;

@Service
public class UsersService {


    private final UsersDao userDao;

    @Autowired
    public UsersService(@Qualifier("usersDao") UsersDao userDao){
        this.userDao = userDao;
    }

    public int addUser(Users user){
        return userDao.insertUser(user);
    }

    public List<Users> getAllUsers(){
        return userDao.selectAllUsers();
    }
}
