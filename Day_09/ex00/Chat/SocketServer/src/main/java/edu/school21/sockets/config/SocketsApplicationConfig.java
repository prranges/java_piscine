package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class SocketsApplicationConfig {
    @Value("${db.url}")
    String url;

    @Value("${db.user}")
    String user;

    @Value("${db.password}")
    String password;

    @Value("${db.driver.name}")
    String driver;

    @Bean
    public DataSource dataSource() {
        HikariDataSource hikariDS = new HikariDataSource();
        hikariDS.setJdbcUrl(url);
        hikariDS.setUsername(user);
        hikariDS.setPassword(password);
        hikariDS.setDriverClassName(driver);
        return hikariDS;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsersRepository usersRepository(DataSource dataSource) {
        return new UsersRepositoryImpl(dataSource);
    }

    @Bean
    UsersService usersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        return new UsersServiceImpl(usersRepository, passwordEncoder);
    }

    @Bean
    Server server(UsersService usersService) {
        return new Server(usersService);
    }
}
