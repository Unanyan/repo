package am.vtc.chat.repo;

import am.vtc.chat.model.User;
import am.vtc.chat.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepoSql {
    public void insert(User user) throws SQLException {
        String query = "insert into users (name, phoneNumber, adress) values(?, ?, ?);";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getAdress());
            preparedStatement.execute();
        }
    }

    public void update(int oldUserId, User newUser) throws SQLException{
        String query = "UPDATE chat.users SET adress = ?, name = ?, phoneNumber = ? WHERE id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newUser.getAdress());
            preparedStatement.setString(2, newUser.getName());
            preparedStatement.setLong(3, newUser.getPhoneNumber());
            preparedStatement.setInt(4, oldUserId);
            preparedStatement.execute();
        }
    }
    public int getLastIntex() throws SQLException {
        String query = "SELECT id FROM users ORDER BY id DESC LIMIT 1;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println(resultSet.getInt(1));
                    return resultSet.getInt(1);
                }
                return 0;
            }
        }
    }

    public boolean exist(int id) throws SQLException {
        String query = "select count(*) from users where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) == 1;
            }
        }
    }

    public Optional<User> findByNameAndPhoneNumber(String name, long phoneNumber) throws SQLException {
        String query = "select * from users where name = ? and phoneNumber = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, phoneNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(UserRepoSql.toUser(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    public List<User> findAll(int count) throws SQLException {
        List<User> list = new ArrayList<>();
        String query = "select * from users;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next() && --count > 0) {
                    list.add(toUser(resultSet));
                }
            }
        }
        return list;
    }

    public Optional<User> findById(int id) throws SQLException {
        String query = "select * from users where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(toUser(resultSet));
                }
            }
        }
        return Optional.empty();
    }



    private static User toUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setAdress(resultSet.getString("adress"));
        user.setPhoneNumber(resultSet.getLong("phoneNumber"));
        return user;
    }
}
