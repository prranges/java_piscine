import java.util.UUID;

public class Transaction {
    enum Type {
        DEBIT, CREDIT;
    }

    private UUID identifier;
    private User recipient;
    private User sender;
    private Type transferCategory;
    private Integer amount;

    public Transaction(User recipient, User sender, Type transferCategory, Integer amount) {
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.amount = amount;
        identifier = UUID.randomUUID();
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Type getTransferCategory() {
        return transferCategory;
    }

    public Integer getAmount() {
        return amount;
    }

    public static Transaction create(User recipient, User sender, Type transferCategory, Integer amount) {
        if ((transferCategory == Type.DEBIT && amount > 0 && sender.getBalance() >= amount)
                || (transferCategory == Type.CREDIT && amount < 0 && recipient.getBalance() >= -amount))
            return new Transaction(recipient, sender, transferCategory, amount);
        else
            return null;
    }
}