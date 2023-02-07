package de.fbeckmann.seamcarvingapp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SeamCarving {

    BufferedImage img;
    double[][] energy;
    
    String direction;
    int pixelAmount;
    String output;


    public SeamCarving(BufferedImage img, String direction, int pixelAmount, String output) {
        this.img = img;
        this.direction = direction;
        this.pixelAmount = pixelAmount;
        this.output = output;
        run();
        BufferedImage imgOut = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                imgOut.setRGB(i, j, (int) energy[i][j]);
            }
        }
        try {
            ImageIO.write(imgOut, "jpg", new File(output));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        energy = new double[img.getWidth()][img.getHeight()];
        calcEnergy();
        System.out.println("Energy calculated");
    }


    public void calcEnergy() {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                // case 1: top left corner
                if (i == 0 && j == 0) {
                    energy[i][j] = Math.sqrt(
                            Math.pow((img.getRGB(i + 1, j) & 0xff) - (img.getRGB(i, j) & 0xff), 2) +
                                    Math.pow((img.getRGB(i, j + 1) & 0xff) - (img.getRGB(i, j) & 0xff), 2)
                    );
                }
                // case 2: top right corner
                else if (i == img.getWidth() - 1 && j == 0) {
                    energy[i][j] = Math.sqrt(
                            Math.pow((img.getRGB(i, j) & 0xff) - (img.getRGB(i - 1, j) & 0xff), 2) +
                                    Math.pow((img.getRGB(i, j + 1) & 0xff) - (img.getRGB(i, j) & 0xff), 2)
                    );
                }
                // case 3: bottom left corner
                else if (i == 0 && j == img.getHeight() - 1) {
                    energy[i][j] = Math.sqrt(
                            Math.pow((img.getRGB(i + 1, j) & 0xff) - (img.getRGB(i, j) & 0xff), 2) +
                                    Math.pow((img.getRGB(i, j) & 0xff) - (img.getRGB(i, j - 1) & 0xff), 2)
                    );
                }
                // case 4: bottom right corner
                else if (i == img.getWidth() - 1 && j == img.getHeight() - 1) {
                    energy[i][j] = Math.sqrt(
                            Math.pow((img.getRGB(i, j) & 0xff) - (img.getRGB(i - 1, j) & 0xff), 2) +
                                    Math.pow((img.getRGB(i, j) & 0xff) - (img.getRGB(i, j - 1) & 0xff), 2)
                    );
                }
                // case 5: top row
                else if (j == 0) {
                    energy[i][j] = Math.sqrt(
                            Math.pow((img.getRGB(i + 1, j) & 0xff) - (img.getRGB(i - 1, j) & 0xff), 2) +
                                    Math.pow((img.getRGB(i, j + 1) & 0xff) - (img.getRGB(i, j) & 0xff), 2)
                    );
                }
                // case 6: bottom row
                else if (j == img.getHeight() - 1) {
                    energy[i][j] = Math.sqrt(
                            Math.pow((img.getRGB(i + 1, j) & 0xff) - (img.getRGB(i - 1, j) & 0xff), 2) +
                                    Math.pow((img.getRGB(i, j) & 0xff) - (img.getRGB(i, j - 1) & 0xff), 2)
                    );
                }
                // case 7: left column
                else if (i == 0) {
                    energy[i][j] = Math.sqrt(
                            Math.pow((img.getRGB(i + 1, j) & 0xff) - (img.getRGB(i, j) & 0xff), 2) +
                                    Math.pow((img.getRGB(i, j + 1) & 0xff) - (img.getRGB(i, j - 1) & 0xff), 2)
                    );
                }
                // case 8: right column
                else if (i == img.getWidth() - 1) {
                    energy[i][j] = Math.sqrt(
                            Math.pow((img.getRGB(i, j) & 0xff) - (img.getRGB(i - 1, j) & 0xff), 2) +
                                    Math.pow((img.getRGB(i, j + 1) & 0xff) - (img.getRGB(i, j - 1) & 0xff), 2)
                    );
                }
                // case 9: all other pixels
                else {
                    energy[i][j] = Math.sqrt(
                            Math.pow((img.getRGB(i + 1, j) & 0xff) - (img.getRGB(i - 1, j) & 0xff), 2) +
                                    Math.pow((img.getRGB(i, j + 1) & 0xff) - (img.getRGB(i, j - 1) & 0xff), 2)
                    );
                }
            }
        }

    }

}
