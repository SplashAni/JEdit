package edit.java;

import edit.java.Utils.Utils;
import edit.java.Utils.Visuals;

import javax.swing.*;
import java.awt.*;

import static edit.java.Utils.Utils.winSize;
import static edit.java.Utils.Visuals.border;
import static edit.java.Utils.Visuals.renderLabel;

public class Editor  {
    int x1;
    int y1;
    public Editor(String icon) {
        JFrame f = new JFrame(); // done cuz window sizes dont work if extended ):
        f.setTitle("Editor");

        JMenuBar m = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu paint = new JMenu("Paint");
        JMenu help = new JMenu("Help");
        m.add(file);
        m.add(edit);
        m.add(paint);
        m.add(help);

        f.setJMenuBar(m);

        JPanel renderPanel = new JPanel(new BorderLayout());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(border(1));

        JPanel xyPanel = new JPanel(new GridLayout(1, 4, 5, 0));
        xyPanel.add(renderLabel(winSize(f)));
        xyPanel.add(Box.createHorizontalStrut(10));
        xyPanel.add(renderLabel("X:"+x1));
        xyPanel.add(renderLabel("Y:"+y1));
        bottomPanel.add(xyPanel, BorderLayout.LINE_START);

        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setPreferredSize(new Dimension(5, 5));
        bottomPanel.add(separator, BorderLayout.CENTER);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setMajorTickSpacing(25);
        bottomPanel.add(slider, BorderLayout.LINE_END);

        renderPanel.add(bottomPanel, BorderLayout.SOUTH);

        JLabel l = new JLabel();
        l.setForeground(Visuals.background());
        l.setIcon(new ImageIcon(icon));
        renderPanel.add(l, BorderLayout.CENTER);

        f.setContentPane(renderPanel);
        f.setSize(650, 500);
        f.setVisible(true);
    }
}
