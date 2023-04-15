package edit.java.Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static edit.java.Utils.FileUtils.read;

public class Visuals {
    public static int size() {
        String sizeStr = null;
        try {
            sizeStr = read(1, "size.cfg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sizeStr == null || sizeStr.trim().isEmpty()) {
            return 12;
        } else {
            return Integer.parseInt(sizeStr.trim());
        }
    }

    public static Color background() {
        String colorString;
        try {
            colorString = read(1, "bg.cfg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (colorString != null) {
            String[] colorValues = colorString.split(",");
            int r = Integer.parseInt(colorValues[0].trim());
            int g = Integer.parseInt(colorValues[1].trim());
            int b = Integer.parseInt(colorValues[2].trim());
            return new Color(r, g, b);
        } else {
            return new Color(65, 61, 61);
        }
    }

    public static LineBorder border(int thickness) {
        String borderConfig = null;
        try {
            borderConfig = read(1, "border.cfg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (borderConfig == null || borderConfig.trim().isEmpty()) {
            return new LineBorder(new Color(238, 130, 238), thickness);
        } else {
            String[] values = borderConfig.split(",");
            int r = Integer.parseInt(values[0].trim());
            int g = Integer.parseInt(values[1].trim());
            int b = Integer.parseInt(values[2].trim());
            Color color = new Color(r, g, b);
            return new LineBorder(color, thickness);
        }
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
        button.setBorderPainted(false);
        button.setForeground(Color.black);
        return button;
    }
    public static JButton defaultButton(String name){
        JButton button = new JButton(name);
        button.setForeground(Color.GRAY);
        button.setForeground(new Color(58, 54, 54));
        button.setBackground(Color.gray);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }
            public static JButton settingButton() throws IOException {
        String buttonConfig = read(1, "button.cfg");

        if (buttonConfig != null && buttonConfig.contains("Flat")) {
            JButton button = defaultButton("Settings");

            return button;
        } else {
            ImageIcon icon = Visuals.transparentIcon("setting.png", 25, 25);
            JButton button = Visuals.transparentButton(icon);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    try {
                        button.setIcon(Visuals.transparentIcon("setting1.png", 25, 25));
                        button.repaint();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setIcon(icon);
                }
            });
            return button;
        }
    }
    public static JLabel renderLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }
}