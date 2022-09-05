package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Logic {

    public static int[][] readBMP(String fileName, char white, char black) throws IOException {
        BufferedImage buf = ImageIO.read(new FileInputStream(fileName));

        int[][] bmpArray = new int[buf.getWidth()][buf.getHeight()];
        int color;
        for (int y = 0; y < buf.getHeight(); ++y) {
            for (int x = 0; x < buf.getWidth(); ++x) {
                bmpArray[y][x] = ((color = buf.getRGB(x, y)) == Color.BLACK.getRGB()) ? black : white;
            }
        }
        return bmpArray;
    }
}
