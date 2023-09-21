package JEdit.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {
    public static ImageUtils INSTANCE = new ImageUtils();

    public ImageUtils() {
    }

    public void replaceColor(File file, Color from, Color to) {
        try {
            BufferedImage img = ImageIO.read(file);
            boolean foundPixel;

            do {
                foundPixel = false;

                for (int y = 0; y < img.getHeight(); y++) {
                    for (int x = 0; x < img.getWidth(); x++) {
                        int pixel = img.getRGB(x, y);
                        Color color = new Color(pixel, true);

                        if (distanceTo(color, from) <= 15 && color.getAlpha() == 255) {
                            img.setRGB(x, y, to.getRGB());
                            foundPixel = true;
                        }
                    }
                }
            } while (foundPixel);

            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int distanceTo(Color c1, Color c2) {
        int r = c1.getRed() - c2.getRed();
        int g = c1.getGreen() - c2.getGreen();
        int bDiff = c1.getBlue() - c2.getBlue();
        return r * r + g * g + bDiff * bDiff;
    }
}
