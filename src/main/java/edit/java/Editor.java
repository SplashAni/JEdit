package edit.java;

import edit.java.Utils.Visuals;

import javax.swing.*;
import java.awt.*;

import static edit.java.Utils.Visuals.border;

public class Editor extends JFrame {

    public Editor(String icon) {
        setTitle("Editor");

        JMenuBar m = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu paint = new JMenu("Paint");
        JMenu help = new JMenu("Help");
        m.add(file);
        m.add(edit);
        m.add(paint);
        m.add(help);

        setJMenuBar(m);

        JPanel renderPanel = new JPanel(new BorderLayout());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(border(1));

        JPanel xyPanel = new JPanel(new GridLayout(1, 4, 5, 0));
        xyPanel.add(createLabel("X:"));
        xyPanel.add(createLabel("Y:"));
        xyPanel.add(Box.createHorizontalStrut(10));
        xyPanel.add(createLabel("X:"));
        xyPanel.add(createLabel("Y:"));
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

        setContentPane(renderPanel);
        setSize(650, 500);
        setVisible(true);
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }
}
