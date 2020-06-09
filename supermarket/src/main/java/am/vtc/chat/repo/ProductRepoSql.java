package am.vtc.chat.repo;

import am.vtc.chat.model.Product;
import am.vtc.chat.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepoSql {
    public void insert(Product product) throws SQLException {
        String query = "insert into products (name, imagePath, aboutProduct, type, count, weight, price) values(?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getImagePath());
            preparedStatement.setString(3, product.getAboutProduct());
            preparedStatement.setString(4, product.getType());
            preparedStatement.setInt(5, product.getCount());
            preparedStatement.setInt(6, product.getWeight());
            preparedStatement.setLong(7, product.getPrice());
            preparedStatement.execute();
        }
    }

    public Optional<Product> findById(int id) throws SQLException {
        String query = "select * from products where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(toProduct(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    public boolean exist(int id) throws SQLException {
        String query = "select count(*) from products where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) == 1;
            }
        }
    }

    public List<Product> findAll(int count) throws SQLException {
        List<Product> list = new ArrayList<>();
        String query = "select * from products;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                Product product;
                while (resultSet.next() && --count > 0) {
                    list.add(toProduct(resultSet));
                }
            }
        }
        return list;
    }

    public List<Product> findByType(String type) throws SQLException {
        List<Product> list = new ArrayList<>();
        String query = "select * from products where type = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, type);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                Product product;
                while (resultSet.next()) {
                    list.add(toProduct(resultSet));
                }
            }
        }
        return list;
    }

    public List<Product> findAll(int start, int count) throws SQLException {
        return null;
    }

    public long count() throws SQLException {
        return 0;
    }
    private static Product toProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setImagePath(resultSet.getString("imagePath"));
        product.setAboutProduct(resultSet.getString("aboutProduct"));
        product.setType(resultSet.getString("type"));
        product.setCount(resultSet.getInt("count"));
        product.setWeight(resultSet.getInt("weight"));
        product.setPrice(resultSet.getLong("price"));
        return product;
    }
}
