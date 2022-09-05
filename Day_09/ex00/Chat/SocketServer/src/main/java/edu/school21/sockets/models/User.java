package edu.school21.sockets.models;

public class User {
    private Long identifier;
    private String userename;
    private String password;

    public User() {
    }

    public User(Long identifier, String userename, String password) {
        this.identifier = identifier;
        this.userename = userename;
        this.password = password;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getUserename() {
        return userename;
    }

    public void setUserename(String userename) {
        this.userename = userename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", userename='" + userename + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
