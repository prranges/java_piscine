package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
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
        String SQL_INSERT_USER = "INSERT INTO users2 (email) VALUES (?)";
        try {
            Long id = jdbcTemplate.queryForObject(SQL_INSERT_USER, Long.class, entity.getEmail());
            entity.setIdentifier(id);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User entity) throws SQLException {
        String SQL_UPDATE_USER = "UPDATE users2 SET email=? WHERE identifier=?";
        try {
            jdbcTemplate.update(SQL_UPDATE_USER, entity.getEmail(), entity.getIdentifier());
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
    public Optional<User> findByEmail(String email) throws SQLException {
        String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users2 WHERE email =?";
        try {
            User user = jdbcTemplate.queryForObject("SELECT * from public.user WHERE email=?",
                    BeanPropertyRowMapper.newInstance(User.class), email);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
