package pl.polsl.ProjektTab.Dao;


import pl.polsl.ProjektTab.Model.Uzytkownicy;
import java.util.List;
import java.util.UUID;

public interface UsersDao {

    int insertUser(UUID id, Uzytkownicy user);

    default int insertUser(Uzytkownicy user){
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }


    List<Uzytkownicy> selectAllUsers();
}
