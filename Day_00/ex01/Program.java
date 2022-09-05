import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int i = 2;
        boolean bool = true;
        if (!input.hasNextInt())
            err();
        int num = input.nextInt();
        if (num < 2)
            err();
        else {
            while (i * i <= num && bool) {
                if (num % i == 0) {
                    bool = false;
                    break;
                }
                i++;
            }
            System.out.println(String.format("%s %d", bool, i - 1));
        }
          System.exit(0);
    }

    public static void err() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }
}