package am.vtc.chat.service;

import am.vtc.chat.model.Cart;
import am.vtc.chat.model.Shoping;
import am.vtc.chat.model.User;
import am.vtc.chat.model.User_Cart;
import am.vtc.chat.repo.CartRepoSql;
import am.vtc.chat.repo.User_CartRepoSql;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CartService {
    private ShopingService shopingService;
    private final User_CartRepoSql user_cartRepoSql;
    private final CartRepoSql cartRepoSql;
    public CartService(){
        this.cartRepoSql = new CartRepoSql();
        this.user_cartRepoSql = new User_CartRepoSql();
        this.shopingService = new ShopingService();
    }

    public boolean save(Cart cart, User user, Shoping shoping) throws SQLException {
        boolean isSave = true;
        List<User_Cart> user_carts = user_cartRepoSql.findByIdUser(user.getId());
        for(User_Cart user_cart: user_carts) {
            List<Shoping> shopings = user_cart.getCart().getShoping();
            for(Shoping shop: shopings) {
                if(shoping.getProduct().getId() == shop.getProduct().getId()) {
                    shopingService.update(shop, shoping);
                    shopingService.delete(shoping);
                    isSave = false;
                    return isSave;
                }
            }
        }
        cartRepoSql.insert(cart);
        return isSave;
    }

    public void updateNotSum(Cart cart, User user, Shoping shoping) throws SQLException {
        List<User_Cart> user_carts = user_cartRepoSql.findByIdUser(user.getId());
        for(User_Cart user_cart: user_carts) {
            List<Shoping> shopings = user_cart.getCart().getShoping();
            for(Shoping shop: shopings) {
                if(shoping.getProduct().getId() == shop.getProduct().getId()) {
                    shopingService.updateNotSum(shop, shoping);
                    shopingService.delete(shoping);
                }
            }
        }
    }


    public int getLastIndex() throws SQLException {
        return this.cartRepoSql.getLastIndex();
    }

    public boolean exist(int id) throws SQLException {
        return cartRepoSql.exist(id);
    }

    public List<Cart> findAll(int count) throws SQLException {
        return cartRepoSql.findAll(count);
    }

    public Optional<Cart> findById(int id) throws SQLException {
        return cartRepoSql.findById(id);
    }

}
