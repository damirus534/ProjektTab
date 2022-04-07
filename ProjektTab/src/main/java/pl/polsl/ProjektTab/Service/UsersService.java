package pl.polsl.ProjektTab.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.polsl.ProjektTab.Dao.UsersDao;
import pl.polsl.ProjektTab.Model.Uzytkownicy;

import java.util.List;

@Service
public class UsersService {


    private final UsersDao userDao;

    @Autowired
    public UsersService(@Qualifier("usersDao") UsersDao userDao){
        this.userDao = userDao;
    }

    public int addUser(Uzytkownicy user){
        return userDao.insertUser(user);
    }

    public List<Uzytkownicy> getAllUsers(){
        return userDao.selectAllUsers();
    }
}
