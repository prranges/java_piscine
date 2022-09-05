package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> createdChatrooms;
    private List<Chatroom> joinedChatrooms;

    public User(Long id, String login, String password, List<Chatroom> createdChatrooms, List<Chatroom> joinedChatrooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdChatrooms = createdChatrooms;
        this.joinedChatrooms = joinedChatrooms;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getCreatedChatrooms() {
        return createdChatrooms;
    }

    public void setCreatedChatrooms(List<Chatroom> createdChatrooms) {
        this.createdChatrooms = createdChatrooms;
    }

    public List<Chatroom> getJoinedChatrooms() {
        return joinedChatrooms;
    }

    public void setJoinedChatrooms(List<Chatroom> joinedChatrooms) {
        this.joinedChatrooms = joinedChatrooms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdChatrooms, joinedChatrooms);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id && login.equals(user.login) && password.equals(user.password)
                && Objects.equals(createdChatrooms, user.createdChatrooms)
                && Objects.equals(joinedChatrooms, user.joinedChatrooms);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdChatrooms +
                ", joinedRooms=" + joinedChatrooms +
                '}';
    }
}
