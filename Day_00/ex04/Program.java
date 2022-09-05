import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();

        if (line.length() == 0)
            System.exit(-1);

        char[]  str = line.toCharArray();
        int[]   array = new int[65535];

        for (char c : str) {
            array[c]++;
        }

        int[] letters = new int[10];
        int[] values = new int[10];
        int maxIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0){
                continue;
            }
            letters[maxIndex] = i;
            values[maxIndex] = array[i];
            maxIndex = 0;
            for (int j = 1; j < 10 && array[letters[maxIndex]] > 0; j++) {

                if ( array[letters[maxIndex]] >= array[letters[j]]) {
                    maxIndex = j;
                }
            }
        }

        sort(letters, values);

        int[] grid_count = new int[10];
        int max_entries = values[0];
        for (int i = 0; i < 10; i++) {
            if (values[i] > max_entries)
                max_entries = values[i];
        }

        for (int i = 0; i < grid_count.length; i++)
            grid_count[i] = (values[i] * 10) / max_entries;

        for (int row = 11; row > 0; row--) {
            for (int i = 0; i < 10; i++) {
                if (grid_count[i] + 1 == row)
                    System.out.print(values[i]);
                if (grid_count[i] >= row)
                    System.out.print("#");
                System.out.print("\t");
            }
            System.out.println();
        }
        for (int i = 0; i < 10; i++)
            System.out.print((char)letters[i] + "\t");
    }

    public static void sort(int[] letters, int[] values)
    {
        int temp;
        int temp2;
        int flag = 1;
        while (flag == 1) {
            flag = 0;
            for (int i = 0; i < values.length - 1; i++) {
                if (values[i] < values[i + 1]) {
                    temp = letters[i + 1];
                    temp2 = values[i + 1];
                    letters[i + 1] = letters[i];
                    values[i + 1] = values[i];
                    letters[i] = temp;
                    values[i] = temp2;
                    flag = 1;
                }
            }
        }
    }
}