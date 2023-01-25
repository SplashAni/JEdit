package edit.java.utils;

import edit.java.Setup;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;

import static edit.java.Editor.*;
import static edit.java.Setup.*;
import static edit.java.utils.imgResize.shouldScale;

public class config {
    public static String path;
    private static JMenuBar menuBar;
    private static JMenu fileoption;
    private static JMenu view;
    private static JMenuItem exit;
    private static JMenuItem open;
    private static JMenuItem zoomIn;
    private static JMenuItem zoomOut;
    private static JMenuItem reload;
    public static ImageIcon temp;


    public static String home = System.getProperty("user.home");


    public static void load() {

        menuBar = new JMenuBar();
        fileoption = new JMenu("File");
        view = new JMenu("View");
        exit = new JMenuItem("Exit");
        open = new JMenuItem("Open");
        reload = new JMenuItem("Reload");
        zoomIn = new JMenuItem("Zoom In");
        zoomOut = new JMenuItem("Zoom Out");
        exit.addActionListener(e -> exit());
        open.addActionListener(e -> open());
        zoomIn.addActionListener(e -> zoomIn());
        zoomOut.addActionListener(e -> zoomOut());
        reload.addActionListener(e -> reload());
        fileoption.add(open);

        Color borderColor = new Color(238, 130, 238);
        renderImg.setBorder(new LineBorder(borderColor, 2));
        fileoption.add(exit);
        view.add(reload);
        view.addSeparator();
        view.add(zoomIn);
        view.add(zoomOut);
        menuBar.add(fileoption);
        menuBar.add(view);
        readImg();


        renderImg.requestFocusInWindow();
        renderImg.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        renderImg.setPreferredSize(new Dimension(20,30));
        editWindow.add(renderImg);
        editWindow.setJMenuBar(menuBar);
        editWindow.setResizable(true);
        editWindow.setSize(600, 400);
        editWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editWindow.setVisible(true);
    }

    static void open() {
        editWindow.dispose();
        new Setup();
    }

    static void exit() {
        editWindow.dispose();
    }

    static void zoomIn() {
        ImageIcon icon = (ImageIcon) renderImg.getIcon();
        Image image = icon.getImage();
        int newWidth = (int) (image.getWidth(null) * 1.1);
        int newHeight = (int) (image.getHeight(null) * 1.1);
        Image newImage = image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
        renderImg.setIcon(new ImageIcon(newImage));
        renderImg.repaint();
    }

    static void zoomOut() {
        ImageIcon icon = (ImageIcon) renderImg.getIcon();
        Image image = icon.getImage();
        int newWidth = (int) (image.getWidth(null) * 0.9);
        int newHeight = (int) (image.getHeight(null) * 0.9);
        Image newImage = image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
        renderImg.setIcon(new ImageIcon(newImage));
        renderImg.repaint();
    }
    public static void readImg() {
        try {
            editWindow.setVisible(true);
            File file = new File(home + File.separator + "JEdit" + File.separator + "path.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            path = reader.readLine();
            reader.close();

            temp = new ImageIcon(path);

            imgResize.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void reload(){
        renderImg.setVisible(false);
    }
    public static void createCnfg() {
        File folder = new File(home + File.separator + "JEdit");
        if (!folder.exists()) {
            boolean success = folder.mkdirs();
            if (success) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        } else {
            System.out.println("ok");
        }

    }
}
