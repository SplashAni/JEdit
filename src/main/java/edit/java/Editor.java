package edit.java;

import edit.java.Utils.Utils;
import edit.java.Utils.Visuals;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static edit.java.Utils.Utils.winSize;
import static edit.java.Utils.Visuals.*;

public class Editor extends JFrame {

    public Editor(String icon) {
        super("JEdit " + Main.VERSION);
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
        bottomPanel.setBorder(nonTop(0));
        bottomPanel.setBackground(Visuals.background());
        JPanel xyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        xyPanel.setBackground(Visuals.background());
        xyPanel.setBorder(nonTop(1));
        JLabel sizeLabel = renderLabel(winSize(this));
        sizeLabel.setForeground(Color.LIGHT_GRAY);
        xyPanel.add(sizeLabel);
        JLabel formatLabel = renderLabel("Format: PNG");
        formatLabel.setForeground(Color.lightGray);
        xyPanel.add(formatLabel);
        bottomPanel.add(xyPanel, BorderLayout.LINE_START);


        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setVisible(false);
        separator.setPreferredSize(new Dimension(5, 5));
        bottomPanel.add(separator, BorderLayout.CENTER);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setBackground(Color.lightGray);
        slider.setBorder(nonTop(1));
        slider.setMajorTickSpacing(25);
        slider.setBackground(Visuals.background());
        bottomPanel.add(slider, BorderLayout.LINE_END);

        renderPanel.add(bottomPanel, BorderLayout.SOUTH);
        renderPanel.setBackground(Visuals.background());

        JLabel l = new JLabel();
        l.setBorder(border(2));
        l.setForeground(Visuals.background());
        l.setIcon(new ImageIcon(icon));
        renderPanel.add(l, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setContentPane(renderPanel);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                sizeLabel.setText(winSize(Editor.this));
            }
        });

        setVisible(true);
    }
}