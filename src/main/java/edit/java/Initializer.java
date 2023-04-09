package edit.java;


import edit.java.Utils.Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import static edit.java.Utils.Visuals.background;
import static edit.java.Utils.Visuals.settingButton;

public class Initializer extends JFrame implements MouseListener { // guis will be done in 1 thread (doesnt take much time)

    private static JLabel heading;

    public Initializer() throws IOException {
        super("Insert image");

        JPanel buttonManager = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel imgRenderer = new JLabel();
        imgRenderer.addMouseListener(this);
        heading = new JLabel("Click below to add image:", SwingConstants.CENTER);


        imgRenderer.setPreferredSize(new Dimension(600, 450));
        imgRenderer.setBorder(Visuals.border(3));

        add(imgRenderer, BorderLayout.CENTER);

        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Helvetica", Font.BOLD, Visuals.size()));
        add(heading, BorderLayout.NORTH);




        JButton enter = new JButton("Enter");
        enter.setForeground(Color.GRAY);
        enter.setForeground(new Color(58, 54, 54));
        enter.setBackground(Color.gray);
        enter.setBorderPainted(false);
        enter.setFocusPainted(false);

        JButton setting = settingButton();
        setting.addActionListener(e -> System.out.println("yes"));

        buttonManager.setLayout(new FlowLayout());
        buttonManager.setBackground(background());
        buttonManager.add(setting);
        buttonManager.add(enter);

        setSize(750, 550);
        add(buttonManager, BorderLayout.SOUTH);
        getContentPane().setBackground(background());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        heading.setText("Drop file below:");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        heading.setText("Click below to add image:");
    }
}