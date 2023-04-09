package edit.java.Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static edit.java.Utils.FileUtils.read;
import static edit.java.Utils.FileUtils.write;

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
    public static void loaderGui(int state) throws IOException {
        switch (state){
            case 1:
                Color bg = JColorChooser.showDialog(null, "Choose a background color", new Color(65, 61, 61));
                if(bg != null){
                    String background = String.format("%d,%d,%d", bg.getRed(), bg.getGreen(), bg.getBlue());
                    write(1,"bg.cfg",background);
                }
                break;
            case 2:
                Color border = JColorChooser.showDialog(null, "Choose a background color",new Color(238, 130, 238));
                if(border != null){
                    String b = String.format("%d,%d,%d", border.getRed(), border.getGreen(), border.getBlue());
                    write(1,"border.cfg",b);
                }
            case 3:
                Integer[] range = new Integer[16];
                for (int i = 0; i < 16; i++) {
                    range[i] = i + 1;
                }
                JComboBox<Integer> comboBox = new JComboBox<>(range);
                comboBox.setSelectedItem(12);
                int result = JOptionPane.showOptionDialog(null,
                        comboBox,
                        "Font size",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new String[]{"Enter", "Cancel"},
                        "Enter");
                if (result == JOptionPane.OK_OPTION) {
                    int value = (int) comboBox.getSelectedItem();
                    write(1,"size.cfg", String.valueOf(value));
                }
                break;
            case 4:
                int option = JOptionPane.showOptionDialog(null,
                        "Choose a type: ",
                        "Button type",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new Object[]{"Flat", "Custom"},
                        "Flat");

                if (option == 1) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileNameExtensionFilter("jpg or png", "jpg", "jpeg", "png"));
                    int path = fileChooser.showOpenDialog(null);

                    if (path == JFileChooser.APPROVE_OPTION) {
                        File fileResult = fileChooser.getSelectedFile();
                        write(1,"button.cfg",fileResult.getAbsolutePath());
                    }
                } else if (option == 0) {
                    write(1,"button.cfg","default");
                }
                break;
        }
    }

}