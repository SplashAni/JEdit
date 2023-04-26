package edit.java.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import static edit.java.Utils.FileUtils.write;

public class MenuUtils {

    public static void addAlignments(JMenuItem j) {

        JMenuItem center = new JMenuItem("Center");
        JMenuItem left = new JMenuItem("Left");
        JMenuItem right = new JMenuItem("Right");

        j.add(center);
        j.add(left);
        j.add(right);

        Component[] components = j.getComponents();
        for (Component c : components) {
            if (c instanceof JMenuItem item) {
                item.addActionListener(e -> {
                    try {
                        write(2,"alignment.tmp",item.getName().toLowerCase(Locale.ROOT));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }
    }
}
