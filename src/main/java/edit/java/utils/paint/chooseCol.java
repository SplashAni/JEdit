package edit.java.utils.paint;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static edit.java.utils.config.home;

public class chooseCol {
    private static File path = new File(home + File.separator + "JEdit" + File.separator + "paintCol.txt");
    public static Color paintCol;

    public static void load() {
        JColorChooser colorChooser = new JColorChooser();
        Color color = colorChooser.showDialog(null, "Choose a color", Color.BLACK);
        if (color != null) {
            try {
                FileWriter writer = new FileWriter(path);
                writer.write(color.getRed() + "," + color.getGreen() + "," + color.getBlue());
                writer.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    static Color loadCol() {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] parts = line.split(",");
            int r = Integer.parseInt(parts[0]);
            int g = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);
            paintCol = new Color(r,g,b);
            br.close();
            fr.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Please select a color","Error",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}