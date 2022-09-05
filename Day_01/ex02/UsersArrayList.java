class UserNotFoundException extends RuntimeException {}

public class UsersArrayList implements UsersList {
    private User[] usersList;

    public UsersArrayList() {
        usersList = new User[10];
    }

    @Override
    public void add(User newUser) {
        for (int i = 0; i < usersList.length; ++i) {
            if (usersList[i] == null) {
                usersList[i] = newUser;
                return;
            }
        }
        int newLength = (int)(usersList.length * 1.5);
        User[] usersList_extended = new User[newLength];
        for (int i = 0; i < usersList.length; ++i) {
            usersList_extended[i] = usersList[i];
        }
        usersList_extended[usersList.length] = newUser;
        usersList = usersList_extended;
    }

    @Override
    public User getById(Integer identifier) {
        for (User user : usersList) {
            if (user != null && user.getIdentifier() == identifier)
                return user;
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getByIndex(Integer index) {
        int i = 0;
        for (User user : usersList) {
            if (user != null && i == index)
                return user;
            ++i;
        }
        return null;
    }

    @Override
    public Integer getNumOfUsers() {
        for (int i = 0; i < usersList.length; ++i) {
            if (usersList[i] == null)
                return i;
        }
        return 0;
    }
}
