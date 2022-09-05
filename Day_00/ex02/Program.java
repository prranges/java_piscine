import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = 0;
        int counter = 0;
        while (true) {
            while (!input.hasNextInt())
                input.next();
            number = input.nextInt();
            if (number == 42)
                break;
            if (number > 1) {
                counter += check(sum(number));
            }
        }
        System.out.println(counter);
    }

    public static int sum(int number) {
        int res = 0;

        while (number > 0) {
            res += number % 10;
            number /= 10;
        }
        return res;
    }

    public static int check(int sum) {
        int i = 2;

        while (i * i <= sum) {
            if (sum % i == 0)
                return 0;
            i++;
        }
        return 1;
    }
}
