package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Optional<Message> optionalMessage = null;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String SQL_FIND_MESSAGE_BY_ID = "SELECT * FROM message WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(SQL_FIND_MESSAGE_BY_ID);
            resultSet.next();

            User user = new User(1L, "user", "user",null, null);
            Chatroom chatroom = new Chatroom(1L, "room", null, null);

            optionalMessage = Optional.of(new Message(resultSet.getLong(1), user, chatroom,
                    resultSet.getString("text"), resultSet.getObject("datetime", LocalDateTime.class)));

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return optionalMessage;
    }

    @Override
    public boolean save(Message message) throws NotSavedSubEntityException {
        String SQL_INSERT_MESSAGE = "INSERT INTO message (author, room, text, datetime)" + "VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_MESSAGE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getChatroom().getId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, Timestamp.valueOf(message.getDate()));
            statement.execute();

            ResultSet key = statement.getGeneratedKeys();
            key.next();
            message.setId(key.getLong(1));
        } catch (SQLException e) {
            throw new NotSavedSubEntityException("Data not saved");
        }
        return true;
    }

    @Override
    public boolean update(Message message) throws SQLException {
        String SQL_UPDATE_MESSAGE = "UPDATE message " + "SET author=?, room=?,  text=?, datetime=? " + "WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_MESSAGE)) {

            LocalDateTime dataTime = message.getDate();
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getChatroom().getId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, (dataTime != null ? Timestamp.valueOf(dataTime) : null));
            statement.setLong(5, message.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new NotSavedSubEntityException("Data not saved");
        }
        return true;
    }
}
