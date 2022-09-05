package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    DataSource dataSource;

    public UsersRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        String SQL_FIND_ALL_BY_ID = "SELECT * FROM users2;";

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_BY_ID);

        while (resultSet.next()) {
            userList.add(new User(
                    resultSet.getLong(1),
                    resultSet.getString(2)
            ));
        }

        statement.close();
        connection.close();

        return userList;
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        String SQL_FIND_USER_BY_ID = "SELECT * FROM users2 WHERE identifier = " + id;

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(SQL_FIND_USER_BY_ID);
        if (!resultSet.next())
            throw new RuntimeException("User with specified id not found");
        User user = new User(
                resultSet.getLong("identifier"),
                resultSet.getString("email"));

        statement.close();
        connection.close();

        return Optional.of(user);
    }

    @Override
    public void update(User user) throws SQLException {
        String SQL_UPDATE_USER = "UPDATE users2 SET email=? WHERE identifier=?";

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER);

        statement.setString(1, user.getEmail());
        statement.execute();

        statement.close();
        connection.close();
    }

    @Override
    public void save(User user) throws SQLException {
        String SQL_INSERT_USER = "INSERT INTO users2 (email) VALUES (?)";

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getEmail());
        statement.execute();

        ResultSet key = statement.getGeneratedKeys();
        key.next();
        user.setIdentifier(key.getLong(1));

        statement.close();
        connection.close();
    }

    @Override
    public void delete(Long id)  throws SQLException {
        String SQL_DELETE_USER = "DELETE FROM users2 WHERE identifier=?";

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER);
        statement.setLong(1, id);
        statement.execute();

        statement.close();
        connection.close();
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users2 WHERE email = " + email;

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(SQL_FIND_USER_BY_EMAIL);
        if (!resultSet.next())
            throw new RuntimeException("User with specified email not found");
        User user = new User(
                resultSet.getLong("identifier"),
                resultSet.getString("email"));

        statement.close();
        connection.close();

        return Optional.of(user);
    }
}
