public class Program {
    public static void main(String[] args) {

        UsersList list = new UsersArrayList();

        System.out.println("User list contains " + list.getNumOfUsers() + " users");
        System.out.println();

        System.out.println("USERS:");
        User user1 = new User("Alex"); list.add(user1);
        System.out.println("Name: " + user1.getName() + "\t\tID: " + user1.getIdentifier() + "\t\tBalance: " + user1.getBalance());
        User user2 = new User("Peter"); list.add(user2);
        System.out.println("Name: " + user2.getName() + "\t\tID: " + user2.getIdentifier() + "\t\tBalance: " + user2.getBalance());
        User user3 = new User("Jane", 500); list.add(user3);
        System.out.println("Name: " + user3.getName() + "\t\tID: " + user3.getIdentifier() + "\t\tBalance: " + user3.getBalance());
        User user4 = new User("Maxim", 1000); list.add(user4);
        System.out.println("Name: " + user4.getName() + "\t\tID: " + user4.getIdentifier() + "\t\tBalance: " + user4.getBalance());
        System.out.println();

        System.out.println("User list contains " + list.getNumOfUsers() + " users");
        System.out.println();

        int id = 1;
        int i = 1;
        System.out.println("User with id " + id + ":\t\t\t" + (list.getById(id).getName()));
        System.out.println("User with index " + i + ":\t\t" + (list.getByIndex(i).getName()));
        id = 4;
        i = 2;
        System.out.println("User with id " + id + ":\t\t\t" + (list.getById(id).getName()));
        System.out.println("User with index " + i + ":\t\t" + (list.getByIndex(i).getName()));

        id = 40;
        try {
            System.out.println("\nTrying to find not existent user with id " + id + ":");
            System.out.println("User with id " + id + ":\t\t\t"+ (list.getById(id)));
        }
        catch (UserNotFoundException e) {
            System.err.println(e +": User with id " + id + " not found");
        }
    }
}
