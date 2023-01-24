package edit.java.utils;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.io.*;

import static edit.java.Editor.*;
import static edit.java.utils.getImg.*;

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

    private static int zoomLevel = 100;

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
        reload.addActionListener(e -> renderImg.repaint());
        fileoption.add(open);



        renderImg.setBorder(new LineBorder(borderColor, 2));

        fileoption.addSeparator();
        fileoption.add(exit);
        view.add(reload);
        view.add(zoomIn);
        view.add(zoomOut);
        menuBar.add(fileoption);
        menuBar.add(view);
        readImg();

        renderImg.setPreferredSize(new Dimension(20,30));
        editWindow.add(renderImg);
        editWindow.setJMenuBar(menuBar);
        editWindow.setResizable(false);
        editWindow.setSize(600, 400);
        editWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editWindow.setVisible(true);
    }

    static void open() {
        editWindow.dispose();
        new getImg();
    }

    static void exit() {
        editWindow.dispose();
    }

    static void zoomIn() {
        zoomLevel += 10;
        ImageIcon icon = (ImageIcon)renderImg.getIcon();
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(img.getWidth(null) * zoomLevel / 100, img.getHeight(null) * zoomLevel / 100, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        renderImg.setIcon(newIcon);
        renderImg.repaint();
    }

    static void zoomOut() {
        zoomLevel -= 10;
        ImageIcon icon = (ImageIcon)renderImg.getIcon();
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(img.getWidth(null) * zoomLevel / 100, img.getHeight(null) * zoomLevel / 100, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        renderImg.setIcon(newIcon);
        renderImg.repaint();
    }
    public static void readImg() {
        try {


            File file = new File(home + File.separator + "JEdit" + File.separator + "path.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            path = reader.readLine();
            reader.close();
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(600,400,  java.awt.Image.SCALE_SMOOTH);

            renderImg.setIcon((Icon) new ImageIcon(newimg));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
