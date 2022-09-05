import java.util.UUID;

public interface TransactionsList {
    public void add(Transaction transaction);
    public void remove(UUID identifier);
    public Transaction[] toArray();
}
