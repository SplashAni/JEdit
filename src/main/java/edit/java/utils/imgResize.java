package edit.java.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static edit.java.Editor.editWindow;
import static edit.java.Editor.renderImg;
import static edit.java.utils.config.readImg;
import static edit.java.utils.config.temp;

public class imgResize {

    public static int width = editWindow.getWidth();
    public static int height = editWindow.getHeight();
    public static boolean shouldScale;

    public static void load() {
        editWindow.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                width = editWindow.getWidth();
                height = editWindow.getHeight();

                Image image = temp.getImage();
                Image newImage = image.getScaledInstance(renderImg.getWidth(), renderImg.getHeight(), java.awt.Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(newImage);
                renderImg.setIcon(resizedIcon);

            }
        });
    }
}
