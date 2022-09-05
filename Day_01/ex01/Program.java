public class Program {
    public static void main(String[] args) {
        System.out.println("\nUSERS:");
        User user1 = new User("Alex");
        System.out.println("Name: " + user1.getName() + "\t\tID: " + user1.getIdentifier() + "\t\tBalance: " + user1.getBalance());
        User user2 = new User("Peter");
        System.out.println("Name: " + user2.getName() + "\t\tID: " + user2.getIdentifier() + "\t\tBalance: " + user2.getBalance());
        User user3 = new User("Jane", 500);
        System.out.println("Name: " + user3.getName() + "\t\tID: " + user3.getIdentifier() + "\t\tBalance: " + user3.getBalance());
        User user4 = new User("Maxim", 1000);
        System.out.println("Name: " + user4.getName() + "\t\tID: " + user4.getIdentifier() + "\t\tBalance: " + user4.getBalance());
    }
}