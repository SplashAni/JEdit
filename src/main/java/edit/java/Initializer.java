package edit.java;


import edit.java.Utils.Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Initializer extends JFrame implements MouseListener { // guis will be done in 1 thread (doesnt take much time)

    private static JLabel heading;
    public Initializer() {
        super("Insert image");

        JPanel buttonManager = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel imgRenderer = new JLabel();
        imgRenderer.addMouseListener(this);
        heading = new JLabel("Click below to add image:", SwingConstants.CENTER);


        imgRenderer.setPreferredSize(new Dimension(600, 450));
        imgRenderer.setBorder(Visuals.border(3));

        add(imgRenderer, BorderLayout.CENTER);

        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Helvetica", Font.BOLD, 12));
        add(heading, BorderLayout.NORTH);

        JButton button1 = new JButton("ok"); // goign to be icon
        button1.setForeground(Color.black);
        JButton button2 = new JButton("ok");
        button2.setForeground(Color.black);

        buttonManager.setLayout(new FlowLayout());
        buttonManager.setBackground(Visuals.Background);
        buttonManager.add(button1);
        buttonManager.add(button2);

        setSize(750, 550);
        add(buttonManager, BorderLayout.SOUTH);
        getContentPane().setBackground(Visuals.Background);
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