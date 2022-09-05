import java.util.UUID;

public class Program {

    public static void main(String[] args) {

        System.out.println("\nUSERS:");
        User user1 = new User("Jane", 5_000);
        System.out.println("Name: " + user1.getName() + "\t\tID: " + user1.getIdentifier() + "\t\tBalance: " + user1.getBalance());
        User user2 = new User("Maxim", 10_000);
        System.out.println("Name: " + user2.getName() + "\t\tID: " + user2.getIdentifier() + "\t\tBalance: " + user2.getBalance());
        System.out.println();

        user1.transactions.add(new Transaction(user1, user2, Transaction.Type.CREDIT, -200));
        user1.transactions.add(new Transaction(user1, user2, Transaction.Type.DEBIT, +300));
        user1.transactions.add(new Transaction(user1, user2, Transaction.Type.CREDIT, -800));
        user1.transactions.add(new Transaction(user1, user2, Transaction.Type.DEBIT, +700));
        user2.transactions.add(new Transaction(user2, user1, Transaction.Type.DEBIT, +2000));
        user2.transactions.add(new Transaction(user2, user1, Transaction.Type.CREDIT, -1000));
        user2.transactions.add(new Transaction(user2, user1, Transaction.Type.DEBIT, +1000));
        user2.transactions.add(new Transaction(user2, user1, Transaction.Type.CREDIT, -600));

        System.out.println("Transactions of " + user1.getName() + ": " + user1.transactions);
        System.out.println("Transactions of " + user2.getName() + ": " + user2.transactions + "\n");

        System.out.println("Transactions of " + user1.getName() + " to array:");
        Transaction[] array = user1.transactions.toArray();
        for (int i = 0; i < array.length; ++i) {
            System.out.println(i + ": " + array[i]);
        }

        System.out.println("\nTransactions of " + user2.getName() + " to array:");
        Transaction[] array2 = user2.transactions.toArray();
        for (int i = 0; i < array2.length; ++i) {
            System.out.println(i + ": " + array2[i]);
        }

        System.out.println("\nRemove transaction [1] of " + user1.getName() + " (" + array[1].getIdentifier() + ")");
        user1.transactions.remove(array[1].getIdentifier());

        System.out.println("\nTransactions of " + user1.getName() + ": " + user1.transactions);
        System.out.println("Transactions of " + user1.getName() + " to array:");
        array = user1.transactions.toArray();
        for (int i = 0; i < array.length; ++i) {
            System.out.println(i + ": " + array[i]);
        }


        UUID rand = UUID.randomUUID();
        System.out.println("\nTrying to remove not existent transaction of " + user1.getName() + " (" + rand + "):");
        try {
            user1.transactions.remove(rand);
        } catch (Exception e) {
            System.err.println(e + ": Transaction (" + rand + ") not found");
        }
    }
}
