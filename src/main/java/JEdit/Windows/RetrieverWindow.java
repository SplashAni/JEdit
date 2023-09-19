package JEdit.Windows;

import JEdit.Config.ConfigReader;
import JEdit.Utils.ComponentUtils;
import JEdit.Utils.RenderUtils;

import javax.swing.*;
import java.awt.*;

public class RetrieverWindow extends JFrame {
    ConfigReader configReader = ConfigReader.INSTANCE;
    public RetrieverWindow() {
        setTitle("JEdit | " + configReader.username());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(820, 505);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel headingLabel = new JLabel("Image Loader");
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        headingLabel.setFont(new Font(configReader.fontFamily(), Font.BOLD, 20));
        panel.add(headingLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setBorder(null);

        JLabel imageLabel = new JLabel("Drag or click to add an image here");
        
        imageLabel.setFont(configReader.font());
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        scrollPane.setViewportView(imageLabel);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        ComponentUtils hoverUtils = new ComponentUtils(imageLabel);
        hoverUtils.init();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> imageLabel.setIcon(null));

        JButton enterButton = new JButton("Enter");

        enterButton.addActionListener(e -> {
            if(configReader.hasPath()){
                SwingUtilities.invokeLater(EditorWindow::new);
                this.dispose();
            }
        });

        buttonPanel.add(clearButton);
        buttonPanel.add(enterButton);

        RenderUtils.INSTANCE.setFont(clearButton, enterButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
