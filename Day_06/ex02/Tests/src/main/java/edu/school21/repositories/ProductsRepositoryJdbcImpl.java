package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String SQL_FIND_ALL_BY_ID = "SELECT * FROM product;";

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_BY_ID);

        while (resultSet.next()) {
            productList.add(new Product(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            ));
        }

        statement.close();
        connection.close();

        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        String SQL_FIND_PRODUCT_BY_ID = "SELECT * FROM product WHERE identifier = " + id;

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(SQL_FIND_PRODUCT_BY_ID);
        if (!resultSet.next())
            throw new RuntimeException("Product with specified id not found");
        Product product = new Product(
                resultSet.getLong("identifier"),
                resultSet.getString("name"),
                resultSet.getInt("price"));

        statement.close();
        connection.close();

        return Optional.of(product);
    }

    @Override
    public void update(Product product) throws SQLException {
        String SQL_UPDATE_PRODUCT = "UPDATE product SET name=?,  price=? WHERE identifier=?";

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PRODUCT);

        statement.setString(1, product.getName());
        statement.setInt(2, product.getPrice());
        statement.setLong(3, product.getIdentifier());
        statement.execute();

        statement.close();
        connection.close();
    }

    @Override
    public void save(Product product) throws SQLException {
        String SQL_INSERT_PRODUCT = "INSERT INTO product (name, price) VALUES (?, ?)";

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getName());
        statement.setLong(2, product.getPrice());
        statement.execute();

        ResultSet key = statement.getGeneratedKeys();
        key.next();
        product.setIdentifier(key.getLong(1));

        statement.close();
        connection.close();
    }

    @Override
    public void delete(Long id)  throws SQLException {
        String SQL_DELETE_PRODUCT = "DELETE FROM product WHERE identifier=?";

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PRODUCT);
        statement.setLong(1, id);
        statement.execute();

        statement.close();
        connection.close();
    }
}
