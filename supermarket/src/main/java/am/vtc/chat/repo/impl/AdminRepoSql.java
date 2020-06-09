package am.vtc.chat.repo.impl;

import am.vtc.chat.model.Admin;
import am.vtc.chat.repo.Repo;
import am.vtc.chat.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminRepoSql implements Repo<Admin> {

    @Override
    public void insert(Admin admin) throws SQLException {
        String query = "insert into admins (name, surname, email, password) values(?, ?, ?, ?);";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getSurname());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.execute();
        }
    }

    @Override
    public Optional<Admin> findById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean exist(String email) throws SQLException {
        String query = "select count(*) from admins where email = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) == 1;
            }
        }
    }

    @Override
    public Optional<Admin> findByEmailAndPassword(String email, String password) throws SQLException {
        String query = "select * from admins where email = ? and password = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                     return Optional.of(AdminRepoSql.toAdmin(resultSet));
                }
            }
            return Optional.empty();
        }
    }

    @Override
    public List<Admin> findAll(int start, int count) throws SQLException {
        return null;
    }

    @Override
    public List<Admin> findAll(int count) throws SQLException {
        return null;
    }

    @Override
    public long count() throws SQLException {
        return 0;
    }
    private static Admin toAdmin(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        admin.setId(resultSet.getInt("id"));
        admin.setName(resultSet.getString("name"));
        admin.setSurname(resultSet.getString("surname"));
        admin.setEmail(resultSet.getString("email"));
        admin.setPassword(resultSet.getString("password"));
        return admin;
    }
}

