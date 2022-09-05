import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long    grades = 0;
        byte    weeks_count = 1;

        while(input.hasNextLine() && weeks_count < 19) {
            String line = input.nextLine();
            if (line.equals("42"))
                break;
            if (!line.equals("Week " + weeks_count))
                err();
            long min = 9;
            for ( int i = 0; i < 5; ++i) {
                if (!input.hasNextInt())
                    err();
                int num = input.nextInt();
                if (1 > num || num > 9)
                    err();
                min = num < min ? num : min;
            }
            if (!"".equals(input.nextLine()))
                err();
            grades += min * ten_power(weeks_count);
            weeks_count++;
        }
        input.close();
        print_res(grades);
    }

    public static void print_res(long grades) {
        int i = 0;
        while (grades != 0) {
            System.out.print("Week ");
            System.out.print(i + 1);
            System.out.print(" ");
            long digit = grades % 10;
            for (int j = 0; j < digit; j++) {
                System.out.print("=");
            }
            System.out.println(">");
            i++;
            grades /= 10;
        }
    }

    public static long ten_power(int weeks_count) {
        long power = 1;
        for (int i = 0; i < weeks_count - 1; i++)
            power *= 10;
        return power;
    }
    public static void err() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }
}
