package JEdit.Windows;

import JEdit.Config.Config;
import JEdit.Utils.ComponentUtils;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
public class SetupWindow extends JFrame {
    int stage;
    Config config = Config.INSTANCE;


    /* Components */
    String username, theme;

    public SetupWindow() {
        FlatDarculaLaf.setup();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setVisible(true);

        this.stage = 0;
        render();

    }

    public void render() {
        switch (stage) {
            case 0 -> username();
            case 1 -> theme();
            case 2 -> style();
            default -> System.exit(0);
        }
    }

    public void username() {

        setSize(445, 230);
        setTitle("Name");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        JLabel headingLabel = new JLabel("Enter a username");

        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPane.add(headingLabel, gbc);

        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        contentPane.add(textField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton cancelButton = new JButton("Cancel");
        JButton nextButton = new JButton("Next >");

        nextButton.setEnabled(textField.getText().length() > 1);


        cancelButton.addActionListener(e -> System.exit(0));

        nextButton.addActionListener(e -> {

            username = textField.getText();
            stage = 1;
            render();
        });

        ComponentUtils.INSTANCE.registerLimit(nextButton,textField);

        buttonPanel.add(cancelButton);
        buttonPanel.add(nextButton);
        gbc.gridy = 2;
        contentPane.add(buttonPanel, gbc);
    }

    public void theme() {

        getContentPane().removeAll();
        revalidate();

        setSize(460, 220);
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Theme");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        JLabel headingLabel = new JLabel("Select a theme");

        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        contentPane.add(headingLabel, gbc);

        JButton nextButton = new JButton("Next >");
        nextButton.setEnabled(false);

        JRadioButton[] radioButtons = new JRadioButton[3];
        radioButtons[0] = new JRadioButton("Light");
        radioButtons[1] = new JRadioButton("Dark");
        radioButtons[2] = new JRadioButton("System");

        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < radioButtons.length; i++) {
            buttonGroup.add(radioButtons[i]);
            radioButtons[i].addActionListener(e -> nextButton.setEnabled(true));
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridx = i;
            contentPane.add(radioButtons[i], gbc);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton(" < Back");
        JButton cancelButton = new JButton("Cancel");

        backButton.addActionListener(e -> {
            stage = 0;
            render();
        });

        cancelButton.addActionListener(e -> {
            System.exit(1);
        });

        nextButton.addActionListener(e -> {

            if (radioButtons[0].isSelected()) {
                theme = "Light";
            } else if (radioButtons[1].isSelected()) {
                theme = "Dark";
            } else if (radioButtons[2].isSelected()) {
                theme = "System";
            }
            stage = 2;
            render();
        });

        buttonPanel.add(backButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(nextButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        contentPane.add(buttonPanel, gbc);

        revalidate();
    }

    public void style() {
        getContentPane().removeAll();
        revalidate();

        setSize(500, 245);
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Style");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        JLabel headingLabel = new JLabel("Select a style");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        contentPane.add(headingLabel, gbc);

        JLabel fontSizeLabel = new JLabel("Font Size:");
        JComboBox<Integer> fontSizeDropdown = new JComboBox<>();
        for (int i = 5; i <= 36; i++) {
            fontSizeDropdown.addItem(i);
        }
        fontSizeDropdown.setSelectedItem(12);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPane.add(fontSizeLabel, gbc);
        gbc.gridx = 1;
        contentPane.add(fontSizeDropdown, gbc);

        JLabel fontLabel = new JLabel("Font:");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] availableFonts = ge.getAvailableFontFamilyNames();
        JComboBox<String> fontDropdown = new JComboBox<>(availableFonts);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        contentPane.add(fontLabel, gbc);
        gbc.gridx = 1;
        fontDropdown.setSelectedItem("Arial");
        contentPane.add(fontDropdown, gbc);

        JButton backButton = new JButton(" < Back");
        JButton cancelButton = new JButton("Cancel");
        JButton finishButton = new JButton("Finish");

        backButton.addActionListener(e -> {
            stage = 1;
            render();
        });

        cancelButton.addActionListener(e -> {
            System.exit(1);
        });

        finishButton.addActionListener(e -> {
            if (isNull(username, theme, fontDropdown.getSelectedItem().toString())) {
                System.exit(0);
            }

            config.saveString("username", username);
            config.saveString("theme", theme);
            config.saveString("font", fontDropdown.getSelectedItem().toString());
            config.saveInt("fontSize", (Integer) fontSizeDropdown.getSelectedItem());

            System.exit(0);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(finishButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        contentPane.add(buttonPanel, gbc);

        revalidate();
    }

    public boolean isNull(String ... s){
        for(String string : s){
            if(string == null) return true;
        }
        return false;
    }
}