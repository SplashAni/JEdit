package edit.java.Config;

import edit.java.Utils.Visuals;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Windows {

    static JFrame l;

    public static void loader() {
        JFrame l = new JFrame("Edit Window");
        l.getContentPane().setBackground(Visuals.Background);
        l.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l.setSize(600, 400);
        l.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        l.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Edit window");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        l.add(titleLabel, BorderLayout.NORTH);


        JPanel textPanel = new JPanel(new GridLayout(4, 2));
        textPanel.setPreferredSize(new Dimension(500, 200));
        textPanel.setBackground(Visuals.Background);
        textPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createEmptyBorder()));
        textPanel.setBorder(Visuals.border(3));

        JLabel[] ls = new JLabel[4];
        JButton[] bs = new JButton[4];


        String[] titles = {"Background", "Text", "Size", "Flat"};
        String[] options = {"Default [Gray]", "Default [Light Gray]", "Default [+ / - 0]", "Default [F]"};

        for (int i = 0; i < 4; i++) {
            ls[i] = new JLabel(titles[i]);
            ls[i].setFont(new Font("Arial", Font.PLAIN, 18));
            ls[i].setForeground(Color.LIGHT_GRAY);
            bs[i] = new JButton(options[i]);
            bs[i].setFont(new Font("Arial", Font.PLAIN, 18));
            bs[i].setBorderPainted(false);
            bs[i].setFocusPainted(false);
            bs[i].setForeground(Color.LIGHT_GRAY);
            bs[i].setBackground(new Color(65, 63, 63));
            textPanel.add(ls[i]);
            textPanel.add(bs[i]);
        }

        l.add(textPanel, BorderLayout.CENTER);

        l.setVisible(true);
    }
}
