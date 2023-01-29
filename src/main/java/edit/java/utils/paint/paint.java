package edit.java.utils.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Stack;

import static edit.java.Editor.renderImg;
import static edit.java.utils.config.home;
import static edit.java.utils.config.toggle;
import static edit.java.utils.imgEffects.newPath;
import static edit.java.utils.imgEffects.reload;
import static edit.java.utils.paint.chooseCol.paintCol;
import static edit.java.utils.paint.shapeUtils.line;
import static edit.java.utils.paint.shapeUtils.readShape;

public class paint {
    private static int x;
    private static int y;
    private static int width1;
    private static int height1;

    public static BufferedImage image = new BufferedImage(renderImg.getWidth(), renderImg.getHeight(), BufferedImage.TYPE_INT_ARGB);


    private static boolean drawing = false;

    public static void enable() {
        renderImg.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                if (drawing) {
                    drawPixel(x,y);
                }
            }
        });
        renderImg.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (drawing) {
                    x = e.getX();
                    y = e.getY();
                    drawPixel(x,y);
                }
            }
        });

    }

    private static void drawPixel(int x, int y) {
        Graphics g = renderImg.getGraphics();
        chooseCol.loadCol();
        g.setColor(paintCol);
        readPixelSize();
        g.fillRect(x, y, width1, height1);
    }
    public static void drawTriangle(int x , int y){
        Graphics2D g = image.createGraphics();
        chooseCol.loadCol();
        g.setColor(paintCol);
        int[] xPoints = {x, x+50, x-50};
        int[] yPoints = {y, y+50, y-50};
        g.fillPolygon(xPoints, yPoints, 3);
        renderImg.getGraphics().drawImage(image, 0, 0, null);
    }

    public static void stateValue() {
        drawing = !drawing;
        if (drawing) {
            toggle.setText("Paint [T]");
            paint.enable();
        } else if (!drawing) {
            toggle.setText("Paint [F]");
        }
    }

    public static void setSize() {
        JFrame frame = new JFrame("Enter Value");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 1);
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                try {
                    FileWriter fw = new FileWriter(home + File.separator + "JEdit" + File.separator + "pixelSize.txt");
                    fw.write(Integer.toString(value) + "," +  Integer.toString(value));
                    fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        frame.add(slider);
        frame.pack();
        frame.setVisible(true);
    }
    public static void readPixelSize(){
        try {
            FileReader fr = new FileReader(home + File.separator + "JEdit" + File.separator + "pixelSize.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] values = line.split(",");
            width1 = Integer.parseInt(values[0]);
            height1 = Integer.parseInt(values[1]);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
