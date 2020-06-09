package am.vtc.chat.repo;

import am.vtc.chat.model.Cart;
import am.vtc.chat.model.Product;
import am.vtc.chat.model.Shoping;
import am.vtc.chat.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartRepoSql {
    public void update(Cart cart, Product product) throws SQLException{
        List<Shoping> shopings = cart.getShoping();
        String query = "UPDATE shopings SET count= ?, weight = ? WHERE idProduct = ?;";
        for(Shoping shoping: shopings) {
            try (Connection connection = DataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, shoping.getProduct().getCount());
                preparedStatement.setInt(2, shoping.getWeight());
                preparedStatement.setInt(3, product.getId());
                preparedStatement.execute();
            }
        }
    }

    public void insert(Cart cart) throws SQLException {
        StringBuilder query = new StringBuilder("insert into carts (idShoping, lastTimeToAdd) values");
        List<Shoping> shopings = cart.getShoping();
        int length = shopings.size();
        for (int i = 0; i < length - 1; i++) {
            query.append("(?,?),");
        }
        query.append("(?,?);");
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {

            int i = 1;
            for (Shoping shoping : shopings) {
                preparedStatement.setInt(i++, shoping.getId());
                preparedStatement.setLong(i++, cart.getLastTimeToAdd());
            }
            System.out.println(shopings);
            preparedStatement.execute();
        }
    }

    public boolean exist(int id) throws SQLException {
        String query = "select count(*) from carts where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) == 1;
            }
        }
    }


    public List<Cart> findAll(int count) throws SQLException {
        List<Cart> list = new ArrayList<>();
        String query = "select * from carts;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next() && --count > 0) {
                    list.add(toCart(resultSet));
                }
            }
        }
        return list;
    }

    public Optional<Cart> findById(int id) throws SQLException {
        String query = "select * from carts where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(toCart(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    public int getLastIndex() throws SQLException {
        String query = "SELECT id FROM carts ORDER BY id DESC LIMIT 1;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
                return 0;
            }
        }
    }

    private static Cart toCart(ResultSet resultSet) throws SQLException {
        ShopingRepoSql productRepoSql = new ShopingRepoSql();
        Cart cart = new Cart();
        cart.setId(resultSet.getInt("id"));
        cart.setShoping(productRepoSql.findById(resultSet.getInt("idShoping")).get());
        cart.setLastTimeToAdd(resultSet.getLong("lastTimeToAdd"));
        return cart;
    }
}
