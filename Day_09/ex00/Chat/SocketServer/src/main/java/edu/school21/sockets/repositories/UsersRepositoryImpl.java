package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(@Qualifier("dataSource") DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        String SQL_FIND_USER_BY_ID = "SELECT * FROM users2 WHERE identifier =?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, BeanPropertyRowMapper.newInstance(User.class), id));
        } catch (DataAccessException e) {
            throw new RuntimeException("User with specified id not found");
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        String SQL_FIND_ALL_BY_ID = "SELECT * FROM users2;";
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_ID, BeanPropertyRowMapper.newInstance(User.class));
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(User entity) throws SQLException {
        String SQL_INSERT_USER = "INSERT INTO users2 (username, password) VALUES (?, ?) RETURNING identifier";
        try {
            Long id = jdbcTemplate.queryForObject(SQL_INSERT_USER, Long.class, entity.getUserename(), entity.getPassword());
            entity.setIdentifier(id);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User entity) throws SQLException {
        String SQL_UPDATE_USER = "UPDATE users2 SET username=? password=? WHERE identifier=?";
        try {
            jdbcTemplate.update(SQL_UPDATE_USER, entity.getUserename(), entity.getPassword(), entity.getIdentifier());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String SQL_DELETE_USER = "DELETE FROM users2 WHERE identifier=?";
        try {
            jdbcTemplate.update(SQL_DELETE_USER, id);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByUsername(String username) throws SQLException {
        String SQL_FIND_USER_BY_USERNAME = "SELECT * FROM users2 WHERE username=?";
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_USER_BY_USERNAME,
                    BeanPropertyRowMapper.newInstance(User.class), username);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }
}