package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;
import java.io.IOException;

public class Program {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Wrong number of arguments");
            System.exit(-1);
        }
        char white = args[0].charAt(0);
        char black = args[1].charAt(0);
        try {
            int[][] arrayBMP = Logic.readBMP("./target/resourses/image.bmp", white, black);
            for (int y = 0; y < arrayBMP.length; ++y) {
                for (int x = 0; x < arrayBMP.length; ++x) {
                    System.out.print((char)arrayBMP[y][x]);
                }
                System.out.println();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
