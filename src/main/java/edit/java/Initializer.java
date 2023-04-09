package edit.java;


import edit.java.Utils.Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import static edit.java.Utils.Visuals.background;

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



        ImageIcon icon = Visuals.transparentIcon("setting.png", 25, 25); // if im being honest this took forever

        JButton settingGear = Visuals.transparentButton(icon);
        settingGear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    settingGear.setIcon(Visuals.transparentIcon("setting1.png", 25, 25));
                    repaint();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingGear.setIcon(icon);
            }
        });

        JButton button2 = new JButton("Enter");
        button2.setForeground(Color.GRAY);
        button2.setForeground(new Color(58, 54, 54));
        button2.setBackground(Color.gray);
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);

        buttonManager.setLayout(new FlowLayout());
        buttonManager.setBackground(background());
        buttonManager.add(settingGear);
        buttonManager.add(button2);

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