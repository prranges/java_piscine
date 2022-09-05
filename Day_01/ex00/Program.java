public class Program {

    public static void main(String[] args) {
        User user01 = new User("Peter", 100_000);
        user01.setIdentifier(1);
        User user02 = new User("Alex", 20_000);
        user02.setIdentifier(2);

        System.out.println("*** Example 1 ***");
        int amount = 1000;

        Transaction tr1 = Transaction.create(user02, user01, Transaction.Type.DEBIT, amount);
        Transaction tr2 = Transaction.create(user01, user02, Transaction.Type.CREDIT, -amount);

        System.out.println(user01.getName() + " balance: " + user01.getBalance());
        System.out.println(user02.getName() + " balance: " + user02.getBalance());
        System.out.println();

        if (tr1 != null && tr2 != null) {

            user01.setBalance(user01.getBalance() - amount);
            user02.setBalance(user02.getBalance() + amount);

            System.out.println(user01.getName() + " send " + amount + " to " + user02.getName() + "\n");
        }
        else {
            System.out.println("Transaction DEBIT: " + tr1);
            System.out.println("Transaction CREDIT: " + tr2);
            System.out.println(user01.getName() + " have not enough money fo transfer " + amount + " to " + user02.getName() + "\n");
        }

        System.out.println(user01.getName() + " balance: " + user01.getBalance());
        System.out.println(user02.getName() + " balance: " + user02.getBalance());
        System.out.println();

        System.out.println("*** Example 2 ***");
        int amount2 = 10000000;

        Transaction tr3 = Transaction.create(user02, user01, Transaction.Type.DEBIT, amount2);
        Transaction tr4 = Transaction.create(user01, user02, Transaction.Type.CREDIT, -amount2);

        if (tr3 != null && tr4 != null) {

            user01.setBalance(user01.getBalance() - amount2);
            user02.setBalance(user02.getBalance() + amount2);

            System.out.println(user01.getName() + " send " + amount2 + " to " + user02.getName() + "\n");
        }
        else {
            System.out.println("Transaction DEBIT: " + tr3);
            System.out.println("Transaction CREDIT: " + tr4);
            System.out.println(user01.getName() + " have not enough money fo transfer " + amount2 + " to " + user02.getName() + "\n");
        }

        System.out.println(user01.getName() + " balance: " + user01.getBalance());
        System.out.println(user02.getName() + " balance: " + user02.getBalance());
        System.out.println();
    }
}
