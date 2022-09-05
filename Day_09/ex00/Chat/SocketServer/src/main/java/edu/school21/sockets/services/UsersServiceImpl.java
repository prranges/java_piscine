package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;

public class UsersServiceImpl implements UsersService {
    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUp(String login, String password) throws SQLException {
        User user = new User(null, login, passwordEncoder.encode(password));
        usersRepository.save(user);
        return null;
    }
}
