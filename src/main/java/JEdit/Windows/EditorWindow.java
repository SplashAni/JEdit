package JEdit.Windows;

import JEdit.Config.ImgConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EditorWindow extends JFrame {
    private final JSplitPane splitPane;

    public EditorWindow() {
        super("Editor");

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        initializeSplitPane();

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

        setLocationRelativeTo(null);
        setSize(975, 620);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeSplitPane() {
        splitPane.setResizeWeight(0.2);
        splitPane.setDividerLocation(55);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JPanel rightPanel = new JPanel();

        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        ArrayList<JButton> buttonList;

        String[] names = {"pencil", "lowercase", "paintbrush", "color", "forward", "backward"};

        buttonList = Arrays.stream(names).map(this::styledButton).collect(Collectors.toCollection(ArrayList::new));

        for (JButton button : buttonList) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftPanel.add(button);
        }

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(leftPanel);

        splitPane.setLeftComponent(centerPanel);
        splitPane.setRightComponent(rightPanel);

        splitPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int currentDividerLocation = splitPane.getDividerLocation();
                if (currentDividerLocation > 55) {
                    splitPane.setDividerLocation(55);
                }
            }
        });
    }

    public JButton styledButton(String name) {
        JButton b = new JButton();
        ImageIcon imageIcon = (ImgConfig.INSTANCE.icon(name));
        Image scaled = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resized = new ImageIcon(scaled);
        b.setIcon(resized);

        b.setFocusPainted(false);
        b.setBackground(null);

        return b;
    }
}
