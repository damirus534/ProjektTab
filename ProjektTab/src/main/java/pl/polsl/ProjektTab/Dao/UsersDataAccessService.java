package pl.polsl.ProjektTab.Dao;

import org.springframework.stereotype.Repository;
import pl.polsl.ProjektTab.Model.Uzytkownicy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("usersDao")
public class UsersDataAccessService implements  UsersDao{

    private static List<Uzytkownicy> testDataList = new ArrayList<>();

    @Override
    public int insertUser(UUID id, Uzytkownicy user) {
        return 0;
    }

    @Override
    public int insertUser(Uzytkownicy user) {
        testDataList.add(new Uzytkownicy());
        return 1;
    }

    @Override
    public List<Uzytkownicy> selectAllUsers() {
        return testDataList;
    }
}
