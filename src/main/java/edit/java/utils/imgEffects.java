package edit.java.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static edit.java.Editor.editWindow;
import static edit.java.Editor.renderImg;
import static edit.java.utils.config.path;
import static edit.java.utils.config.temp;
import static edit.java.utils.otherUtils.height;
import static edit.java.utils.otherUtils.width;

public class imgEffects {
    private static BufferedImage image;

    static {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int dotPngIndex = path.lastIndexOf(".png");
    public static String  newPath = path.substring(0, dotPngIndex) + "_edited" + path.substring(dotPngIndex);

    private static JSlider slider;

    public static void black() {
        File file = new File(newPath);
        BufferedImage orginalImage = null;
        try {
            orginalImage = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage blackAndWhiteImg = new BufferedImage(
                orginalImage.getWidth(), orginalImage.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(orginalImage, 0, 0, null);

        String fileExtension = getFileExtension(file.getName());

        try {
            ImageIO.write(blackAndWhiteImg, fileExtension, new File(newPath));
            reload();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public static void blur() {
        try {
            JFrame frame = new JFrame("Blur Slider");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(300, 100);

            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
            slider.setMajorTickSpacing(1);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            frame.add(slider);
            frame.setVisible(true);

            slider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    int blurAmount = slider.getValue();
                    if(slider.getValueIsAdjusting() && blurAmount == 0){
                        setOrgImg();
                    }else{
                        ImageIcon icon = new ImageIcon(newPath);
                        float[] matrix = new float[blurAmount * blurAmount];
                        Arrays.fill(matrix, 1.0f / (float) (blurAmount * blurAmount));
                        Kernel kernel = new Kernel(blurAmount, blurAmount, matrix);
                        ConvolveOp op = new ConvolveOp(kernel);
                        BufferedImage blurredImage = null;
                        try {
                            blurredImage = op.filter(ImageIO.read(new File(newPath)), null);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        File outputFile = new File(newPath);
                        try {
                            ImageIO.write(blurredImage, "png", outputFile);
                            reload();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sepia(){
        try {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int p = image.getRGB(x, y);

                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;

                    int tr = (int) (0.393 * r + 0.769 * g + 0.189 * b);
                    int tg = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                    int tb = (int) (0.272 * r + 0.534 * g + 0.131 * b);

                    if (tr > 255) {
                        r = 255;
                    } else {
                        r = tr;
                    }

                    if (tg > 255) {
                        g = 255;
                    } else {
                        g = tg;
                    }

                    if (tb > 255) {
                        b = 255;
                    } else {
                        b = tb;
                    }

                    p = (a << 24) | (r << 16) | (g << 8) | b;
                    image.setRGB(x, y, p);
                }
            }
                ImageIO.write(image, "png", new File(newPath));
            reload();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void invert(){
        try {

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int p = image.getRGB(x, y);

                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;

                    r = 255 - r;
                    g = 255 - g;
                    b = 255 - b;

                    p = (a << 24) | (r << 16) | (g << 8) | b;
                    image.setRGB(x, y, p);
                }
            }

            ImageIO.write(image, "png", new File(newPath));
            reload();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void emboss(){
        try {
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage embossed = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            int[][] kernel = {
                    {-2, -1, 0},
                    {-1,  1, 1},
                    { 0,  1, 2}
            };

            //APPY TO EVERY KERNEL PIXEL
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int newRed = 0;
                    int newGreen = 0;
                    int newBlue = 0;

                    //APPLY TOT EVERTY PIXEL
                    for (int ky = -1; ky <= 1; ky++) {
                        for (int kx = -1; kx <= 1; kx++) {
                            int xcoord = x + kx;
                            int ycoord = y + ky;

                            if (xcoord >= 0 && xcoord < width && ycoord >= 0 && ycoord < height) {
                                int color = image.getRGB(xcoord, ycoord);
                                int red = (color >> 16) & 0xff;
                                int green = (color >> 8) & 0xff;
                                int blue = color & 0xff;

                                newRed += kernel[ky+1][kx+1] * red;
                                newGreen += kernel[ky+1][kx+1] * green;
                                newBlue += kernel[ky+1][kx+1] * blue;
                            }
                        }
                    }

                    newRed = Math.min(Math.max(newRed, 0), 255);
                    newGreen = Math.min(Math.max(newGreen, 0), 255);
                    newBlue = Math.min(Math.max(newBlue, 0), 255);

                    //COLOURING EVERY PIXEL REMEMBER THIS ...
                    int newColor = (newRed << 16) | (newGreen << 8) | newBlue;
                    embossed.setRGB(x, y, newColor);
                }
            }

            ImageIO.write(embossed, "png", new File(newPath));
            reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reload(){
        width = editWindow.getWidth();
        height = editWindow.getHeight();

        Image image = temp.getImage();
        Image newImage = image.getScaledInstance(renderImg.getWidth(), renderImg.getHeight(), java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(newImage);
        renderImg.setIcon(resizedIcon);

    }
    public static void orgImage(){
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));

            ImageIO.write(originalImage, "png", new File(newPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setOrgImg(){
        ImageIcon orgImg = new ImageIcon(newPath);
        Image image = orgImg.getImage();
        Image newImage = image.getScaledInstance(renderImg.getWidth(), renderImg.getHeight(), java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(newImage);
        renderImg.setIcon(resizedIcon);
    }
}
