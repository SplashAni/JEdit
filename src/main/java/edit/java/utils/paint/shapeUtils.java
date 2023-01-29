package edit.java.utils.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static edit.java.Editor.renderImg;
import static edit.java.utils.config.home;
import static edit.java.utils.paint.chooseCol.paintCol;
import static edit.java.utils.paint.paint.image;
import static edit.java.utils.paint.paint.readPixelSize;

public class shapeUtils {
    public static String line;
    public static void load() {
        JFrame shapeFrame = new JFrame("Shapes");

        JRadioButton triangleButton = new JRadioButton("Triangle");
        JRadioButton circleButton = new JRadioButton("Circle");
        JRadioButton squareButton = new JRadioButton("Square");
        JRadioButton rectangleButton = new JRadioButton("Rectangle");

        triangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeToFile("Triangle");
            }
        });

        circleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeToFile("Circle");
            }
        });

        squareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeToFile("Square");
            }
        });

        rectangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeToFile("Rectangle");
            }
        });

        ButtonGroup shapeGroup = new ButtonGroup();
        shapeGroup.add(triangleButton);
        shapeGroup.add(circleButton);
        shapeGroup.add(squareButton);
        shapeGroup.add(rectangleButton);

        JPanel shapePanel = new JPanel();
        shapePanel.add(triangleButton);
        shapePanel.add(circleButton);
        shapePanel.add(squareButton);
        shapePanel.add(rectangleButton);

        shapeFrame.add(shapePanel);

        shapeFrame.setSize(300, 150);
        shapeFrame.setLocationRelativeTo(null);
        shapeFrame.pack();
        shapeFrame.setVisible(true);
    }
    static void writeToFile(String shape){
        FileWriter writer = null;
        try {
            writer = new FileWriter(home + File.separator + "JEdit" + File.separator + "shape.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.write(shape);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void readShape(){
        try {
            FileReader fr = new FileReader(home + File.separator + "JEdit" + File.separator + "shape.txt");
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        Graphics2D g = image.createGraphics();
        chooseCol.loadCol();
        g.setColor(paintCol);
        readPixelSize();
        int[] xPoints = {x1, x2, x3};
        int[] yPoints = {y1, y2, y3};
        g.fillPolygon(xPoints, yPoints, 3);
        renderImg.getGraphics().drawImage(image, 0, 0, null);
    }
    public static void drawShape(){


        switch(line) {
            case "Triangle":

                break;
            case "Circle":
                System.out.println("Circle selected");
                break;
            case "Square":
                System.out.println("Sqaure selected");
                break;
            case "Rectangle":
                System.out.println("Rectangle selected");
                break;
        }
    }
}

