package am.vtc.chat.repo;

import am.vtc.chat.model.Shoping;
import am.vtc.chat.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShopingRepoSql {
    public void insert(Shoping shoping) throws SQLException {
        String query = "insert into shopings (idProduct, count, weight) values(?, ?, ?);";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, shoping.getProduct().getId());
            preparedStatement.setInt(2, shoping.getCount());
            preparedStatement.setInt(3, shoping.getWeight());
            preparedStatement.execute();
        }
    }

    public void update(Shoping shopingOld, Shoping shopingNew) throws SQLException {
        String query = "UPDATE chat.shopings SET count = ? WHERE id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, shopingNew.getCount() + shopingOld.getCount());
            preparedStatement.setInt(2, shopingOld.getId());
            preparedStatement.execute();
        }
    }

    public void updateNotSum(Shoping shopingOld, Shoping shopingNew) throws SQLException {
        String query = "UPDATE chat.shopings SET count = ? WHERE id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, shopingNew.getCount());
            preparedStatement.setInt(2, shopingOld.getId());
            preparedStatement.execute();
        }
    }

    public void delete(Shoping shoping) throws SQLException{
        String query = "DELETE FROM chat.shopings WHERE id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, shoping.getId());
            preparedStatement.execute();
        }
    }

    public boolean exist(int id) throws SQLException {
        String query = "select count(*) from shopings where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) == 1;
            }
        }
    }


    public List<Shoping> findAll(int count) throws SQLException {
        List<Shoping> list = new ArrayList<>();
        String query = "select * from shopings;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next() && --count > 0) {
                    list.add(toShoping(resultSet));
                }
            }
        }
        return list;
    }

    public int getLastIntex() throws SQLException {
        String query = "SELECT id FROM shopings ORDER BY id DESC LIMIT 1;";
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

    public Optional<Shoping> findById(int id) throws SQLException {
        String query = "select * from shopings where id = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(toShoping(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Shoping> findByProductId(int id) throws SQLException {
        String query = "select * from shopings where idProduct = ?;";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(toShoping(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    private static Shoping toShoping(ResultSet resultSet) throws SQLException {
        ProductRepoSql productRepoSql = new ProductRepoSql();
        Shoping shoping = new Shoping();
        shoping.setId(resultSet.getInt("id"));
        shoping.setCount(resultSet.getInt("count"));
        shoping.setWeight(resultSet.getInt("weight"));
        shoping.setProduct(productRepoSql.findById(resultSet.getInt("idProduct")).get());

        return shoping;
    }
}
