public class UserIdsGenerator {

    private static UserIdsGenerator instance;
    private Integer lastId;

    public UserIdsGenerator() {
        lastId = 0;
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null)
            instance = new UserIdsGenerator();
        return instance;
    }

    public Integer generateId() {
        return ++lastId;
    }
}