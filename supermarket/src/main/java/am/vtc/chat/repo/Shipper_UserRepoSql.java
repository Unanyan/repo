package am.vtc.chat.repo;

import am.vtc.chat.model.Shipper_User;
import am.vtc.chat.repo.impl.ShipperRepoSql;
import am.vtc.chat.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Shipper_UserRepoSql {
    public void insert(Shipper_User shipper_user) throws SQLException {
        String query = "insert into shippers_users (shipperId, userId) values(?, ?);";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, shipper_user.getShipper().getId());
            preparedStatement.setInt(2, shipper_user.getUser().getId());
            preparedStatement.execute();
        }
    }

    public boolean exist(int id) throws SQLException {
        String query = "select count(*) from shippers_users where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) == 1;
            }
        }
    }

    public List<Shipper_User> findAll(int count) throws SQLException {
        List<Shipper_User> list = new ArrayList<>();
        String query = "select * from shippers_users;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next() && --count > 0) {
                    list.add(toShipper_user(resultSet));
                }
            }
        }
        return list;
    }

    public List<Shipper_User> findAll() throws SQLException {
        List<Shipper_User> list = new ArrayList<>();
        String query = "select * from shippers_users;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(toShipper_user(resultSet));
                }
            }
        }
        return list;
    }

    public void updateWasShipped(int shipperUserId) throws SQLException {
        String query = "UPDATE chat.shippers_users t SET t.wasShipped = 1 WHERE t.id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, shipperUserId);
            preparedStatement.execute();
        }
    }

    public Optional<Shipper_User> findById(int id) throws SQLException {
        String query = "select * from shippers_users where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(toShipper_user(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    public List<Shipper_User> findByIdShipper(int id) throws SQLException {
        String query = "select * from shippers_users where shipperId = ?;";
        List<Shipper_User> list = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(toShipper_user(resultSet));
                }
            }
        }
        return list;
    }

    public List<Shipper_User> findByIdUser(int id) throws SQLException {
        String query = "select * from shippers_users where userId = ?;";
        List<Shipper_User> list = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(toShipper_user(resultSet));
                }
            }
        }
        return list;

    }

    private static Shipper_User toShipper_user(ResultSet resultSet) throws SQLException {
        UserRepoSql userRepoSql = new UserRepoSql();
        ShipperRepoSql shipperRepoSql = new ShipperRepoSql();
        Shipper_User shipper_user = new Shipper_User();
        shipper_user.setId(resultSet.getInt("id"));
        shipper_user.setUser(userRepoSql.findById(resultSet.getInt("userId")).get());
        shipper_user.setShipper(shipperRepoSql.findById(resultSet.getInt("shipperId")).get());
        shipper_user.setWasShipped(resultSet.getBoolean("wasShipped"));
        return shipper_user;
    }
}
