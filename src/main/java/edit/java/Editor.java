package edit.java;

import edit.java.Utils.Utils;
import edit.java.Utils.Visuals;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static edit.java.Utils.Utils.winSize;
import static edit.java.Utils.Visuals.border;
import static edit.java.Utils.Visuals.renderLabel;

public class Editor extends JFrame {
    public Editor(String icon) {
        super("Editor");
        setSize(850, 600);

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
        bottomPanel.setBackground(Visuals.background());
        this.getRootPane().setBorder(border(2));
        JPanel xyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        xyPanel.setBackground(Visuals.background());
        JLabel sizeLabel = renderLabel(winSize(this));
        sizeLabel.setForeground(new Color(-1)); //soon trademarks
        xyPanel.add(sizeLabel);
        JLabel formatLabel = renderLabel("Format: PNG");
        formatLabel.setForeground(new Color(-1));
        xyPanel.add(formatLabel);
        bottomPanel.add(xyPanel, BorderLayout.LINE_START);


        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setPreferredSize(new Dimension(5, 5));
        bottomPanel.add(separator, BorderLayout.CENTER);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setMajorTickSpacing(25);
        slider.setBackground(Visuals.background());
        bottomPanel.add(slider, BorderLayout.LINE_END);

        renderPanel.add(bottomPanel, BorderLayout.SOUTH);
        renderPanel.setBackground(Visuals.background());

        JLabel l = new JLabel();
        l.setForeground(Visuals.background());
        l.setIcon(new ImageIcon(icon));
        renderPanel.add(l, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setContentPane(renderPanel);
        setVisible(true);
    }
}

