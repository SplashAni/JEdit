package JEdit.Windows;

import JEdit.Config.Config;
import JEdit.Config.Downloader;
import JEdit.Utils.ComponentUtils;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
public class SetupWindow extends JFrame {
    int stage;
    Config config = Config.INSTANCE;

    String username, theme;
    Color iconColor;
    boolean buttonOutline;

    public SetupWindow() {

        Downloader imgConfig = new Downloader(true);
        imgConfig.createDir();
        imgConfig.download();

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
            case 1 -> buttonStyle();
            case 2 -> theme();
            case 3 -> style();
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
    public void buttonStyle() {
        getContentPane().removeAll();
        revalidate();

        setSize(500, 300);
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Button Style");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        JLabel headingLabel = new JLabel("Select a button style");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPane.add(headingLabel, gbc);

        JButton colorPickerButton = new JButton("Icon color");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        contentPane.add(colorPickerButton, gbc);

        JColorChooser colorChooser = new JColorChooser();
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPane.add(colorChooser, gbc);
        colorChooser.setVisible(false);

        JRadioButton includeBordersRadio = new JRadioButton("Include Borders");
        includeBordersRadio.setSelected(true);
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        contentPane.add(includeBordersRadio, gbc);

        colorPickerButton.addActionListener(e -> {
            colorChooser.setVisible(!colorChooser.isVisible());
        });

        JButton backButton = new JButton(" < Back");
        JButton cancelButton = new JButton("Cancel");
        JButton nextButton = new JButton("Next >");

        backButton.addActionListener(e -> {
            stage = 0;
            render();
        });

        cancelButton.addActionListener(e -> {
            System.exit(1);
        });

        nextButton.addActionListener(e -> {
            this.iconColor = colorChooser.getColor();
            this.buttonOutline = includeBordersRadio.isSelected();

            stage = 2;
            render();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(nextButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
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
            stage = 1;
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
            stage = 3;
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
        fontDropdown.setSelectedItem("Arial");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        contentPane.add(fontLabel, gbc);
        gbc.gridx = 1;
        contentPane.add(fontDropdown, gbc);

        JButton backButton = new JButton(" < Back");
        JButton cancelButton = new JButton("Cancel");
        JButton finishButton = new JButton("Finish");

        backButton.addActionListener(e -> {
            stage = 2;
            render();
        });

        cancelButton.addActionListener(e -> {
            System.exit(1);
        });

        finishButton.addActionListener(e -> {
            if (!isNull(username, theme, (String) fontDropdown.getSelectedItem())) {
                config.saveString("username", username);
                config.saveString("theme", theme);
                config.saveString("font", Objects.requireNonNull(fontDropdown.getSelectedItem()).toString());
                config.saveInt("fontSize", (Integer) fontSizeDropdown.getSelectedItem());
                config.saveColor("iconColor", iconColor);
                config.saveBoolean("buttonOutline", buttonOutline);

                setVisible(false);

            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all requirements.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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