package am.vtc.chat.service;

import am.vtc.chat.model.Cart;
import am.vtc.chat.model.User_Cart;
import am.vtc.chat.repo.UserRepoSql;
import am.vtc.chat.repo.User_CartRepoSql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class User_CartService {
    private final User_CartRepoSql user_cartRepoSql;
    private final UserRepoSql userRepoSql;

    public User_CartService(){
        this.user_cartRepoSql = new User_CartRepoSql();
        this.userRepoSql = new UserRepoSql();
    }

    public void save(User_Cart user_cart) throws SQLException {
        user_cartRepoSql.insert(user_cart);
    }

    public List<User_Cart> findAll() throws SQLException {
        return this.user_cartRepoSql.findAll();
    }

    public boolean exist(int id) throws SQLException {
        return user_cartRepoSql.exist(id);
    }

    public List<User_Cart> findAll(int count) throws SQLException {
        return user_cartRepoSql.findAll(count);
    }

    public Optional<User_Cart> findById(int id) throws SQLException {
        return user_cartRepoSql.findById(id);
    }

    public List<User_Cart> findByIdUser(int id) throws SQLException {
        return user_cartRepoSql.findByIdUser(id);
    }

    public List<Cart> findByIdUserReturnCarts(int id) throws SQLException {
        List<Cart> carts = new ArrayList<>();
        List<User_Cart> list = user_cartRepoSql.findByIdUser(id);
        Iterator<User_Cart> iterator = list.iterator();
        User_Cart user_cart;
        while (iterator.hasNext()) {
            user_cart = iterator.next();
            if(user_cart.getUser().getId() == id) {
                carts.add(user_cart.getCart());
            }
        }
         return carts;
    }

    public Optional<User_Cart> findByIdCart(int id) throws SQLException {
        return user_cartRepoSql.findByIdCart(id);
    }
}
