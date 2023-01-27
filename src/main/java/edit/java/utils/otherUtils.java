package edit.java.utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import static edit.java.Editor.editWindow;
import static edit.java.Editor.renderImg;
import static edit.java.utils.config.temp;
import static edit.java.utils.imgEffects.newPath;
import static edit.java.utils.config.path;

public class otherUtils {
    public static Color bg = new Color(54, 57, 63);

    public static int width = editWindow.getWidth();
    public static int height = editWindow.getHeight();

    public static void load() {
        editWindow.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                width = editWindow.getWidth();
                height = editWindow.getHeight();

                Image image = temp.getImage();
                Image newImage = image.getScaledInstance(renderImg.getWidth(), renderImg.getHeight(), java.awt.Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(newImage);
                renderImg.setIcon(resizedIcon);
                editWindow.setTitle(String.valueOf(width + " x " + height + " || JEdit"));
            }
        });
    }

    public static void zoomIn() {
        ImageIcon icon = (ImageIcon) renderImg.getIcon();
        Image image = icon.getImage();
        int newWidth = (int) (image.getWidth(null) * 1.1);
        int newHeight = (int) (image.getHeight(null) * 1.1);
        Image newImage = image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
        renderImg.setIcon(new ImageIcon(newImage));
        renderImg.repaint();
    }

    public static void zoomOut() {
        ImageIcon icon = (ImageIcon) renderImg.getIcon();
        Image image = icon.getImage();
        int newWidth = (int) (image.getWidth(null) * 0.9);
        int newHeight = (int) (image.getHeight(null) * 0.9);
        Image newImage = image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
        renderImg.setIcon(new ImageIcon(newImage));
        renderImg.repaint();
    }

    public static void boxClick() {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem zoomIn = new JMenuItem("ZoomIn");
        zoomIn.setForeground(Color.LIGHT_GRAY);
        popupMenu.add(zoomIn);
        JMenuItem zoomOut = new JMenuItem("ZoomOut");
        zoomOut.setForeground(Color.lightGray);
        popupMenu.add(zoomOut);
        popupMenu.setBorder(new LineBorder(Color.lightGray, 1));
        zoomIn.setBackground(bg);
        zoomOut.setBackground(bg);

        zoomIn.addActionListener(e -> zoomIn());
        zoomOut.addActionListener(e -> zoomOut());

        renderImg.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) { // left button
                    popupMenu.show(renderImg, e.getX(), e.getY());
                }
            }
        });
    }

    public static void saving() {
        editWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showOptionDialog(null, "Save changes", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Yes", "No"}, "Yes");
                if (result == JOptionPane.YES_OPTION) {
                    File delete = new File(newPath);
                    if(delete.exists()){
                        delete.delete();
                    }
                    try {
                        Desktop.getDesktop().open(new File(path));
                        editWindow.setVisible(false);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (result == JOptionPane.NO_OPTION) {
                    File org = new File(path);
                    if(org.exists()){
                        org.delete();
                    }
                    try {
                        Desktop.getDesktop().open(new File(newPath));
                        editWindow.setVisible(false);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
