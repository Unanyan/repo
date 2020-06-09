package am.vtc.chat.repo;

import am.vtc.chat.model.User_Cart;
import am.vtc.chat.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User_CartRepoSql {
    public void insert(User_Cart user_cart) throws SQLException {
        String query = "insert into users_carts (idUser, idCart) values(?, ?);";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user_cart.getUser().getId());
            preparedStatement.setInt(2, user_cart.getCart().getId());
            preparedStatement.execute();
        }
    }

    public boolean exist(int id) throws SQLException {
        String query = "select count(*) from users_carts where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) == 1;
            }
        }
    }

    public List<User_Cart> findAll(int count) throws SQLException {
        List<User_Cart> list = new ArrayList<>();
        String query = "select * from users_carts;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next() && --count > 0) {
                    list.add(toUser_Cart(resultSet));
                }
            }
        }
        return list;
    }

    public List<User_Cart> findAll() throws SQLException {
        List<User_Cart> list = new ArrayList<>();
        String query = "select * from users_carts;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(toUser_Cart(resultSet));
                }
            }
        }
        return list;
    }

    public Optional<User_Cart> findById(int id) throws SQLException {
        String query = "select * from users_carts where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(toUser_Cart(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    public List<User_Cart> findByIdUser(int id) throws SQLException {
        String query = "select * from users_carts where idUser = ?;";
        List<User_Cart> list = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(toUser_Cart(resultSet));
                }
            }
        }
        return list;
    }

    public Optional<User_Cart> findByIdCart(int id) throws SQLException {
        String query = "select * from users_carts where idCart = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(toUser_Cart(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    private static User_Cart toUser_Cart(ResultSet resultSet) throws SQLException {
        UserRepoSql userRepoSql = new UserRepoSql();
        CartRepoSql cartRepoSql = new CartRepoSql();
        User_Cart user_cart = new User_Cart();
        user_cart.setId(resultSet.getInt("id"));
        user_cart.setUser(userRepoSql.findById(resultSet.getInt("idUser")).get());
        user_cart.setCart(cartRepoSql.findById(resultSet.getInt("idCart")).get());
        return user_cart;
    }
}
