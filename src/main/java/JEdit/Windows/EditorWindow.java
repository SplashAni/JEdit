package JEdit.Windows;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditorWindow extends JFrame {
    public EditorWindow() {
        super("Editor");

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu windowMenu = new JMenu("Window");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        fileMenu.add(exitMenuItem);
        fileMenu.add(saveMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(windowMenu);

        setJMenuBar(menuBar);

        getContentPane().add(splitPane);

        splitPane.setResizeWeight(0.2);
        splitPane.setDividerLocation(55);


        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JPanel rightPanel = new JPanel();

        ArrayList<JButton> buttonList = new ArrayList<>();

        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        buttonList.add(styledButton("C:\\Users\\User\\Downloads\\icons8-pencil-50.png"));
        buttonList.add(styledButton("C:\\Users\\User\\Downloads\\icons8-lowercase-t-50.png"));
        buttonList.add(styledButton("C:\\Users\\User\\Downloads\\icons8-paintbrush-50.png"));
        buttonList.add(styledButton("C:\\Users\\User\\Downloads\\icons8-color-50.png"));
        buttonList.add(styledButton("C:\\Users\\User\\Downloads\\icons8-forward-50.png"));
        buttonList.add(styledButton("C:\\Users\\User\\Downloads\\icons8-back-50.png"));

        for (JButton button : buttonList) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftPanel.add(button);
        }

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(leftPanel);

        splitPane.setLeftComponent(centerPanel);

        splitPane.setRightComponent(rightPanel);

        setLocationRelativeTo(null);
        setSize(975, 620);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JButton styledButton(String path) {
        JButton b = new JButton();
        ImageIcon imageIcon = new ImageIcon(path);
        Image scaled = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resized = new ImageIcon(scaled);
        b.setIcon(resized);

        b.setFocusPainted(false);
        b.setBackground(null);


        return b;
    }

}