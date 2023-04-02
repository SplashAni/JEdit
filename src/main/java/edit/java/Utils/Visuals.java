package edit.java.Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Visuals {
    public static Color Background = new Color(65, 61, 61, 247);

    public static LineBorder border(int thickness) {
        return new LineBorder(new Color(238, 130, 238), thickness);
    }
    public static BufferedImage toTransparent(Image image, int width, int height) {
        BufferedImage transparentImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = transparentImg.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, width, height);
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return transparentImg;
    }

    public static ImageIcon transparentIcon(String imagePath, int width, int height) throws IOException {
        Image img = Utils.readImage(imagePath);
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage transparentImg = toTransparent(resizedImg, width, height);
        return new ImageIcon(transparentImg);
    }

    public static JButton transparentButton(ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setForeground(Color.black);
        return button;
    }
}