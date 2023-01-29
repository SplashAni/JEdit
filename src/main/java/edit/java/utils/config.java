package edit.java.utils;

import edit.java.Setup;
import edit.java.utils.paint.chooseCol;
import edit.java.utils.paint.paint;
import edit.java.utils.paint.shapeUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;

import static edit.java.Editor.*;
import static edit.java.utils.imgEffects.newPath;
import static edit.java.utils.imgEffects.reload;
import static edit.java.utils.otherUtils.*;
import static edit.java.utils.paint.paint.stateValue;

public class config {
    public static String path;
    private static JMenuBar menuBar;
    private static JMenu fileoption;
    private static JMenu editOption;
    private static JMenu paint1;
    private static JMenu view;
    private static JMenuItem exit;
    private static JMenuItem open;
    private static JMenuItem zoomIn;
    private static JMenuItem zoomOut;
    private static JMenuItem reload;
    public static JMenuItem toggle;
    private static JMenuItem color;
    private static JMenuItem size;
    public static JMenuItem shapes;
    private static JMenuItem save;


    //effects ->
    private static JMenuItem black;
    private static JMenuItem blur;
    private static JMenuItem sepia;
    private static JMenuItem invert;
    private static JMenuItem emboss;


    public static ImageIcon temp;
    public static Color borderColor = new Color(238, 130, 238);


    public static String home = System.getProperty("user.home");


    public static void load() {
        otherUtils.boxClick();
        menuBar = new JMenuBar();
        menuBar.setBackground(bg);
        menuBar.setBorderPainted(false);
        fileoption = new JMenu("File");
        fileoption.setForeground(Color.lightGray);
        view = new JMenu("View");
        paint1 = new JMenu("Paint");
        editOption = new JMenu("Edit");

        editOption.setForeground(Color.lightGray);
        view.setForeground(Color.lightGray);
        paint1.setForeground(Color.lightGray);
        exit = new JMenuItem("Exit");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        reload = new JMenuItem("Reload");
        zoomIn = new JMenuItem("Zoom In");
        zoomOut = new JMenuItem("Zoom Out");
        blur = new JMenuItem("Blur");
        black = new JMenuItem("B/W");
        sepia = new JMenuItem("Senpia");
        invert = new JMenuItem("Invert");
        emboss = new JMenuItem("Emboss");
        toggle = new JMenuItem("Toggle [F]");
        color = new JMenuItem("Color");
        size = new JMenuItem("Size");
        shapes = new JMenuItem("Shapes");


        exit.addActionListener(e -> otherUtils.exiting());
        open.addActionListener(e -> open());
        save.addActionListener(e -> otherUtils.saveImgState());
        zoomIn.addActionListener(e -> zoomIn());
        zoomOut.addActionListener(e -> zoomOut());
        reload.addActionListener(e -> reload());
        black.addActionListener(e -> imgEffects.black());
        blur.addActionListener(e -> imgEffects.blur());
        sepia.addActionListener(e -> imgEffects.sepia());
        invert.addActionListener(e -> imgEffects.invert());
        emboss.addActionListener(e -> imgEffects.emboss());
        toggle.addActionListener(e -> stateValue());
        color.addActionListener(e -> chooseCol.load());
        size.addActionListener(e -> paint.setSize());
        shapes.addActionListener(e -> shapeUtils.load());
        fileoption.add(open);

        zoomIn.setBackground(bg);
        invert.setBackground(bg);
        emboss.setBackground(bg);
        blur.setBackground(bg);
        black.setBackground(bg);
        zoomOut.setBackground(bg);
        sepia.setBackground(bg);
        reload.setBackground(bg);
        open.setBackground(bg);
        exit.setBackground(bg);
        toggle.setBackground(bg);
        save.setBackground(bg);
        color.setBackground(bg);
        size.setBackground(bg);
        shapes.setBackground(bg);
        toggle.setForeground(Color.lightGray);
        color.setForeground(Color.lightGray);
        save.setForeground(Color.lightGray);
        size.setForeground(Color.lightGray);
        shapes.setForeground(Color.lightGray);
        zoomIn.setForeground(Color.lightGray);
        sepia.setForeground(Color.lightGray);
        emboss.setForeground(Color.lightGray);
        invert.setForeground(Color.lightGray);
        blur.setForeground(Color.lightGray);
        black.setForeground(Color.lightGray);
        zoomOut.setForeground(Color.lightGray);
        reload.setForeground(Color.lightGray);
        open.setForeground(Color.lightGray);
        exit.setForeground(Color.lightGray);

        renderImg.setBorder(new LineBorder(borderColor, 2));
        fileoption.add(exit);
        view.add(reload);
        view.add(zoomIn);
        view.add(zoomOut);
        editOption.add(blur);
        editOption.add(black);
        editOption.add(emboss);
        editOption.add(invert);
        editOption.add(sepia);
        paint1.add(toggle);
        paint1.add(color);
        paint1.add(size);
        paint1.add(shapes);
        menuBar.add(fileoption);
        menuBar.add(view);
        menuBar.add(editOption);
        menuBar.add(paint1);
        fileoption.add(save);
        readImg();
        saving();



        renderImg.requestFocusInWindow();
        renderImg.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        renderImg.setPreferredSize(new Dimension(20,30));
        editWindow.add(renderImg);
        editWindow.setJMenuBar(menuBar);
        editWindow.setResizable(true);
        editWindow.setSize(600, 400);
        editWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        editWindow.setVisible(true);
    }

    static void open() {
        editWindow.dispose();
        new Setup();
    }

    public static void readImg() {
        try {
            editWindow.setVisible(true);
            File file = new File(home + File.separator + "JEdit" + File.separator + "path.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            path = reader.readLine();
            reader.close();

            temp = new ImageIcon(newPath);

            otherUtils.load();

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
