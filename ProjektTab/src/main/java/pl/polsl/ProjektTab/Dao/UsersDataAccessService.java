package pl.polsl.ProjektTab.Dao;

import org.springframework.stereotype.Repository;
import pl.polsl.ProjektTab.Model.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("usersDao")
public class UsersDataAccessService implements  UsersDao{

    private static List<Users> testDataList = new ArrayList<>();

    @Override
    public int insertUser(UUID id, Users user) {
        return 0;
    }

    @Override
    public int insertUser(Users user) {
        testDataList.add(new Users((long) (testDataList.size()+1), user.getPermission(), user.getLogin(), user.getPassword(), user.getAdres()));
        return 1;
    }

    @Override
    public List<Users> selectAllUsers() {
        return testDataList;
    }
}
