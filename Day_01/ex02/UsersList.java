public interface UsersList {
    public void add(User newUser);
    public User getById(Integer identifier);
    public User getByIndex(Integer index);
    public Integer getNumOfUsers();
}
