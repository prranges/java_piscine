package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) throws SQLException {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/chat");
        dataSource.setUsername("postgres");
        dataSource.setPassword("1234");

        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource);

        User author = new User(1L, "user", "1234", new ArrayList<>(), new ArrayList<>());
        Chatroom room = new Chatroom(1L, "chat", author, new ArrayList<>());
        Message msg = new Message(null, author, room, "message", LocalDateTime.now());

        repository.save(msg);
        System.out.println(msg.getId());
    }
}
