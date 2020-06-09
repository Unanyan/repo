package am.vtc.chat.service;

import am.vtc.chat.model.User;
import am.vtc.chat.repo.UserRepoSql;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepoSql userRepoSql;

    public UserService(){this.userRepoSql = new UserRepoSql();}

    public void save(User user) throws SQLException {
        this.userRepoSql.insert(user);
    }

    public int getLastIntex() throws SQLException {
        return this.userRepoSql.getLastIntex();
    }

    public void update(int oldUserId, User newUser) throws SQLException{
        this.userRepoSql.update(oldUserId, newUser);
    }

    public boolean exist(int id) throws SQLException {
        return this.userRepoSql.exist(id);
    }

    public Optional<User> findByNameAndPhoneNumber(String name, long phoneNumber) throws SQLException {
        return this.userRepoSql.findByNameAndPhoneNumber(name, phoneNumber);
    }

    public List<User> findAll(int count) throws SQLException {
        return this.userRepoSql.findAll(count);
    }

    public Optional<User> findById(int id) throws SQLException {
        return this.userRepoSql.findById(id);
    }
}
