package edit.java;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

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

        JPanel contentPane = new JPanel(new BorderLayout());


        JLabel b = new JLabel("so smart ");
        b.setHorizontalAlignment(SwingConstants.CENTER);
        b.setBorder(new LineBorder(Color.gray, 2));
        contentPane.add(b, BorderLayout.SOUTH);

        JLabel l = new JLabel("");
        l.setBorder(new LineBorder(Color.gray, 2));
        contentPane.add(l, BorderLayout.CENTER);

        setContentPane(contentPane);
        setSize(650, 500);
        setVisible(true);
    }
}