package de.fbeckmann.seamcarvingapp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        BufferedImage img;
        String imgPath = "";
        // direction: horizontal or vertical
        String direction = "";
        int pixelAmount = 0;
        String output = "";
        try {
            imgPath = args[0];
            direction = args[1];
            pixelAmount = Integer.parseInt(args[2]);
            output = args[3];
            img = ImageIO.read(new File(imgPath));
            new SeamCarving(img, direction, pixelAmount, output);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}