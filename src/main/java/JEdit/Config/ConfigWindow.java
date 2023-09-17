package JEdit.Config;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;

public class ConfigWindow extends JFrame {
    int stage;

    /*components*/
    JTextField textField;

    public ConfigWindow() {

        FlatDarculaLaf.setup();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);


        setVisible(true);

        render();

    }
    public void render() {
        switch (stage) {
            case 0 -> x();
            case 1 -> y();
            case 2 -> z();
            default -> System.exit(0);
        }
    }


    public void x() {

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

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        contentPane.add(textField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton cancelButton = new JButton("Cancel");
        JButton nextButton = new JButton("Next >");

        cancelButton.addActionListener(e -> System.exit(0));

        nextButton.addActionListener(e -> {
            String enteredText = textField.getText();
            System.out.println(enteredText);
            y();
        });

        buttonPanel.add(cancelButton);
        buttonPanel.add(nextButton);
        gbc.gridy = 2;
        contentPane.add(buttonPanel, gbc);
    }
    public void y() {
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
            radioButtons[i].addActionListener(e -> {
                nextButton.setEnabled(true);
            });
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridx = i;
            contentPane.add(radioButtons[i], gbc);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton(" < Back");
        JButton cancelButton = new JButton("Cancel");

        backButton.addActionListener(e -> {
            x();
        });

        cancelButton.addActionListener(e -> {
            System.exit(1);
        });

        nextButton.addActionListener(e -> {
            z();
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

    public void z() {
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
        JComboBox<String> fontSizeDropdown = new JComboBox<>(new String[]{"Small", "Medium", "Large"});
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPane.add(fontSizeLabel, gbc);
        gbc.gridx = 1;
        contentPane.add(fontSizeDropdown, gbc);

        JLabel fontStyleLabel = new JLabel("Font Style:");
        JComboBox<String> fontStyleDropdown = new JComboBox<>(new String[]{"Regular", "Bold", "Italic"});
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        contentPane.add(fontStyleLabel, gbc);
        gbc.gridx = 1;
        contentPane.add(fontStyleDropdown, gbc);

        JButton backButton = new JButton(" < Back");
        JButton cancelButton = new JButton("Cancel");
        JButton finishButton = new JButton("Finish");

        backButton.addActionListener(e -> {
            y();
        });

        cancelButton.addActionListener(e -> {
            System.exit(1);
        });

        finishButton.addActionListener(e -> {
            System.exit(1);
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

}