package pl.polsl.ProjektTab.Dao;

import pl.polsl.ProjektTab.Model.Users;

import java.util.List;
import java.util.UUID;

public interface UsersDao {

    int insertUser(UUID id, Users user);

    default int insertUser(Users user){
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<Users> selectAllUsers();
}
