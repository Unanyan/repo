package am.vtc.chat.service;

import am.vtc.chat.model.Cart;
import am.vtc.chat.model.Shipper_User;
import am.vtc.chat.model.User;
import am.vtc.chat.model.User_Cart;
import am.vtc.chat.repo.Shipper_UserRepoSql;
import am.vtc.chat.repo.User_CartRepoSql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Shipper_userService {
    private final Shipper_UserRepoSql shipper_userRepoSql;
    public Shipper_userService() {
        this.shipper_userRepoSql = new Shipper_UserRepoSql();
    }

    public void save(Shipper_User shipper_user) throws SQLException {
        this.shipper_userRepoSql.insert(shipper_user);
    }

    public List<Shipper_User> findAll() throws SQLException {
        return this.shipper_userRepoSql.findAll();
    }

    public boolean exist(int id) throws SQLException {
        return this.shipper_userRepoSql.exist(id);
    }

    public List<Shipper_User> findAll(int count) throws SQLException {
        return this.shipper_userRepoSql.findAll(count);
    }

    public Optional<Shipper_User> findById(int id) throws SQLException {
        return this.shipper_userRepoSql.findById(id);
    }
    public void updateWasShipped(int shipperUserId) throws SQLException {
        this.shipper_userRepoSql.updateWasShipped(shipperUserId);
    }

    public List<Shipper_User> findByIdUser(int id) throws SQLException {
        return this.shipper_userRepoSql.findByIdUser(id);
    }

    public List<User> findByIdShipperReturnUsers(int id) throws SQLException {
        List<User> users = new ArrayList<>();
        List<Shipper_User> list = this.findByIdShipper(id);
        Iterator<Shipper_User> iterator = list.iterator();
        Shipper_User shipper_user;
        while (iterator.hasNext()) {
            shipper_user = iterator.next();
            if(shipper_user.getUser().getId() == id) {
                users.add(shipper_user.getUser());
            }
        }
        return users;
    }

    public List<Shipper_User> findByIdShipper(int id) throws SQLException {
        return this.shipper_userRepoSql.findByIdShipper(id);
    }
}
