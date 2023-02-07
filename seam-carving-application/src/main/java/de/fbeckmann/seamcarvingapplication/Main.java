package de.fbeckmann.seamcarvingapplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args){

        try {
            String imgPath = args[0];
            String direction = args[1];
            int pixelAmount = Integer.parseInt(args[2]);
            String output = args[3];
            BufferedImage img = ImageIO.read(new File(imgPath));
            new SeamCarving(img, direction, pixelAmount, output);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
