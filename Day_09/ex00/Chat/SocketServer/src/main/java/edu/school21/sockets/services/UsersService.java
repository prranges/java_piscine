package edu.school21.sockets.services;

import edu.school21.sockets.models.User;

import java.sql.SQLException;

public interface UsersService {
    User signUp(String login, String password) throws SQLException;
}
