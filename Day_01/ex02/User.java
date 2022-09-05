public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    public User(String name) {
        this.name = name;
        this.balance = 0;
        this.identifier = UserIdsGenerator.getInstance().generateId();
    }

    public User(String name, Integer balance) {
        this.name = name;
        this.balance = balance > 0 ? balance : 0;
        this.identifier = UserIdsGenerator.getInstance().generateId();
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance > 0 ? balance : 0;
    }
}
