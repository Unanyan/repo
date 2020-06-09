package am.vtc.chat.service;

import am.vtc.chat.exception.DatabaseException;
import am.vtc.chat.model.Product;
import am.vtc.chat.repo.ProductRepoSql;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductRepoSql productRepoSql;

    public ProductService(){this.productRepoSql = new ProductRepoSql();}

    public Optional<Product> findById(int id) throws SQLException {
        return this.productRepoSql.findById(id);
    }

    public boolean exist(int id) throws DatabaseException {
        try {
            return this.productRepoSql.exist(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void save(Product product) throws DatabaseException {
        try {
            if (product.getId() > 0) {
                //todo update
            } else {
                //System.out.println(product.getImagePath().substring(product.getImagePath().lastIndexOf(".")));
                //String [] words = product.getImagePath().split(".");
               // System.out.println("*******" + words.length);
                String format = product.getImagePath().substring(product.getImagePath().lastIndexOf(".") );
                String pathInResources = "/home/vrezh/Desktop/webGnun/04.19.2020/supermarket/src/main/web/resources/images/" + product.getName() + format;
                FileOutputStream fileOutputStream = new FileOutputStream(
                        pathInResources, true);

                FileInputStream fileInputStream = new FileInputStream(product.getImagePath());
                try (fileInputStream; fileOutputStream) {
                    int i;
                    do {
                        i = fileInputStream.read();
                        fileOutputStream.write(i);
                    } while (i != -1);

                    product.setImagePath(pathInResources.substring(pathInResources.lastIndexOf("resources/")));

                    this.productRepoSql.insert(product);
                }
            }
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public List<Product> findByType(String type) throws SQLException {
        return this.productRepoSql.findByType(type);
    }

    public List<Product> findAll(int count) throws SQLException {
        return this.productRepoSql.findAll(count);
    }
}
