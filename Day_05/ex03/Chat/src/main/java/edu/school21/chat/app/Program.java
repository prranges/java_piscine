package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Optional;

public class Program {
    public static void main(String[] args) throws SQLException {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/chat");
        dataSource.setUsername("postgres");
        dataSource.setPassword("1234");

        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource);

        Optional<Message> messageOptional = repository.findById(5L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("Hello update!");
            message.setDate(null);
            repository.update(message);
        }
        dataSource.close();
    }
}