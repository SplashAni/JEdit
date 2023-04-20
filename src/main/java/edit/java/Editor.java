package edit.java;

import edit.java.Utils.Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import static edit.java.Utils.Utils.readImage;
import static edit.java.Utils.Utils.winSize;
import static edit.java.Utils.Visuals.*;

public class Editor extends JFrame {
    boolean border = true; // will be an option soon

    public Editor(String icon){
        super("JEdit " + Main.VERSION);
        setSize(850, 600);
        icon = String.valueOf(readImage))
        JMenuBar m = new JMenuBar();
        m.setBackground(background());

        m.setBorder(borderLayout(2,border));
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu paint = new JMenu("Paint");
        JMenu window = new JMenu("Window");

        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem properties = new JMenuItem("Properties");
        JMenuItem exit = new JMenuItem("Exit");


        file.add(save);
        file.add(saveAs);
        file.add(properties);
        file.add(exit);

        m.add(file);
        m.add(edit);
        m.add(paint);
        m.add(window);

        setJMenuBar(m);
        styleMenus(m);


        JPanel renderPanel = new JPanel(new BorderLayout());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(borderLayout(0,border));
        bottomPanel.setBackground(Visuals.background());
        JPanel xyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        xyPanel.setBackground(Visuals.background());
        xyPanel.setBorder(borderLayout(1, border));
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
        slider.setBorder(borderLayout(1, border));
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