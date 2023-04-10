package edit.java;

import javax.swing.*;
import java.awt.*;

public class Editor {

    public static JFrame frame;

    public Editor() {
        frame = new JFrame("JEdit");
        frame.setSize(650, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu paint = new JMenu("Paint");
        JMenu help = new JMenu("Help");
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(paint);
        menuBar.add(help);
        frame.setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JLabel rendering = new JLabel();
        rendering.setHorizontalAlignment(SwingConstants.CENTER);
        rendering.setVerticalAlignment(SwingConstants.CENTER);
        mainPanel.add(rendering, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel bottomLabel = new JLabel("widhtxheight");
        bottomPanel.add(bottomLabel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}