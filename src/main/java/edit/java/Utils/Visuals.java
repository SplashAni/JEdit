package edit.java.Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static edit.java.Utils.FileUtils.write;

public class Visuals {
    public static Color background = new Color(65, 61, 61, 247);

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
        button.setBorderPainted(false);
        button.setForeground(Color.black);
        return button;
    }
    public static JButton defaultButton(String text) {
        JButton button = new JButton(text);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setForeground(Color.black);
        Font font = new Font("Arial", Font.BOLD, 13);
        button.setFont(font);
        return button;
    }
    public static void loaderGui(int state) throws IOException {
        switch (state){
            case 1:
                Color bg = JColorChooser.showDialog(null, "Choose a background color", background);
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