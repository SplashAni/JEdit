package edit.java.utils;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static edit.java.Editor.*;
import static edit.java.utils.getImg.file;
import static edit.java.utils.getImg.file1;

public class config {
    private static JMenuBar menuBar;
    private static JMenu fileoption;
    private static JMenu view;
    private static JMenuItem exit;
    private static JMenuItem open;
    private static JMenuItem zoomIn;
    private static JMenuItem zoomOut;
    private JLabel imageLabel;
    private ImageIcon image;
    private int zoomLevel = 100;

    public static void load() {

        menuBar = new JMenuBar();
        fileoption = new JMenu("File");
        view = new JMenu("View");
        exit = new JMenuItem("Exit");
        open = new JMenuItem("Open");
        zoomIn = new JMenuItem("Zoom In");
        zoomOut = new JMenuItem("Zoom Out");
        exit.addActionListener(e -> exit());
        open.addActionListener(e -> open());

        fileoption.add(open);

        fileoption.addSeparator();
        fileoption.add(exit);
        view.add(zoomIn);
        view.add(zoomOut);
        menuBar.add(fileoption);
        menuBar.add(view);

        editWindow.add(renderImg, BorderLayout.CENTER);
        editWindow.setJMenuBar(menuBar);
        editWindow.setLayout(new BorderLayout());
        editWindow.setResizable(false);
        editWindow.setSize(600, 400);
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

    }

    public static void createCnfg() {
        File folder = new File("JPaint");
        System.out.println(folder);
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