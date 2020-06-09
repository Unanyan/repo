package am.vtc.chat.repo.impl;

import am.vtc.chat.model.Shipper;
import am.vtc.chat.repo.Repo;
import am.vtc.chat.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ShipperRepoSql implements Repo<Shipper> {

  @Override
  public void insert(Shipper shipper) throws SQLException {
    String query = "insert into shippers (name, surname, email, password) values(?, ?, ?, ?);";
    try (Connection connection = DataSource.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, shipper.getName());
      preparedStatement.setString(2, shipper.getSurname());
      preparedStatement.setString(3, shipper.getEmail());
      preparedStatement.setString(4, shipper.getPassword());
      preparedStatement.execute();
    }
  }

  @Override
  public Optional<Shipper> findById(int id) throws SQLException {
    String query = "select * from shippers where id = ?;";
    try (Connection connection = DataSource.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, id);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          return Optional.of(toShipper(resultSet));
        }
      }
    }
    return Optional.empty();
  }


  @Override
  public boolean exist(String email) throws SQLException {
    String query = "select count(*) from shippers where email = ?;";
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
  public Optional<Shipper> findByEmailAndPassword(String email, String password) throws SQLException {
    String query = "select * from shippers where email = ? and password = ?;";
    try (Connection connection = DataSource.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          return Optional.of(ShipperRepoSql.toShipper(resultSet));
        }
      }
    }
    return Optional.empty();
  }

  @Override
  public List<Shipper> findAll(int start, int count) throws SQLException {
    return null;
  }

  @Override
  public List<Shipper> findAll(int count) throws SQLException {
    return null;
  }

  @Override
  public long count() throws SQLException {
    return 0;
  }

  private static Shipper toShipper(ResultSet resultSet) throws SQLException {
    Shipper shipper = new Shipper();
    shipper.setId(resultSet.getInt("id"));
    shipper.setName(resultSet.getString("name"));
    shipper.setSurname(resultSet.getString("surname"));
    shipper.setEmail(resultSet.getString("email"));
    shipper.setPassword(resultSet.getString("password"));
    return shipper;
  }
}
