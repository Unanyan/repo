package am.vtc.chat.service;

import am.vtc.chat.model.Shoping;
import am.vtc.chat.repo.ShopingRepoSql;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ShopingService {
    private final ShopingRepoSql shopingRepoSql;

    public ShopingService() {this.shopingRepoSql = new ShopingRepoSql();}

    public void save(Shoping shoping) throws SQLException {

        shopingRepoSql.insert(shoping);
    }
    public void update(Shoping shopingOld, Shoping shopingNew) throws SQLException {
        this.shopingRepoSql.update(shopingOld, shopingNew);
    }

    public void updateNotSum(Shoping shopingOld, Shoping shopingNew) throws SQLException {
        this.shopingRepoSql.updateNotSum(shopingOld, shopingNew);
    }

    public void delete(Shoping shoping) throws SQLException{
        this.shopingRepoSql.delete(shoping);
    }
    public int getLastIntex() throws SQLException {
        return this.shopingRepoSql.getLastIntex();
    }

    public boolean exist(int id) throws SQLException {
        return shopingRepoSql.exist(id);
    }

    public List<Shoping> findAll(int count) throws SQLException {
        return shopingRepoSql.findAll(count);
    }

    public Optional<Shoping> findById(int id) throws SQLException {
        return shopingRepoSql.findById(id);
    }
}
