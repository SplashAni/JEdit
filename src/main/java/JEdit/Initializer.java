package JEdit;

import JEdit.Utils.LabelManager;

import javax.swing.*;
import java.awt.*;

public class Initializer extends JFrame {


    public Initializer() {
        super("JEdit");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(820, 505);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel headingLabel = new JLabel("Image Loader");
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(headingLabel, BorderLayout.NORTH);

        JLabel imageLabel = new JLabel("Drag or click to add an image here");
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        LabelManager hoverUtils = new LabelManager(imageLabel);

        hoverUtils.init();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton clearButton = new JButton("Clear");

        clearButton.addActionListener(e -> imageLabel.setIcon(null));

        JButton enterButton = new JButton("Enter");
        buttonPanel.add(clearButton);
        buttonPanel.add(enterButton);

        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

}
