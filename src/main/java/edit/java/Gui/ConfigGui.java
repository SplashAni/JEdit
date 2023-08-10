package edit.java.Gui;

import edit.java.Initializer;
import edit.java.Utils.FileUtils;
import edit.java.Utils.Utils;
import edit.java.Utils.Visuals;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;

import static edit.java.Utils.FileUtils.write;
import static edit.java.Utils.Utils.setupResizing;
import static edit.java.Utils.Visuals.*;


public class ConfigGui extends JFrame{


    public ConfigGui() throws IOException {
        super("Config window");
        setLocationRelativeTo(null);
        FileUtils.init(1, false);
        getContentPane().setBackground(background());
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setSize(730, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Config  window");
        titleLabel.setBorder(Visuals.borderLayout(2,true));
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        JPanel textPanel = new JPanel(new GridLayout(4, 2));
        textPanel.setPreferredSize(new Dimension(500, 200));
        textPanel.setBackground(background());
        textPanel.setBorder(Visuals.borderLayout(1,true));



        JLabel[] ls = new JLabel[4];
        JButton[] bs = new JButton[4];

        String[] titles = {"Background", "Border", "Size", "Button"};
        String[] options = {Utils.i(), Utils.i1(), Utils.i2(), Utils.i3()};

        for (int i = 0; i < 4; i++) {
            ls[i] = new JLabel(titles[i]);
            ls[i].setFont(new Font("Helvetica", Font.PLAIN, 18));
            ls[i].setForeground(textCol());
            bs[i] = new JButton(options[i]);


            bs[i].setFont(new Font("Helvetica", Font.PLAIN, 18));
            ls[i].setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
            bs[i].setBorderPainted(false);
            bs[i].setFocusPainted(false);
            bs[i].setForeground(textCol());
            bs[i].setBackground(background());
            textPanel.add(ls[i]);
            textPanel.add(bs[i]);
        }
        for (int i = 0; i < bs.length; i++) {
            int z = i + 1;
            bs[i].addActionListener(e -> {
                try {
                    loadOption(z);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        add(textPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(background());
        buttonPanel.setBorder(Visuals.borderLayout(3,true));

        JButton cancel = Visuals.defaultButton("Default");

        cancel.addActionListener(e -> {
            dispose();
            try {
                new Initializer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(cancel);


        JButton apply = Visuals.defaultButton("Apply");

        apply.addActionListener(e -> {
            dispose();
            try {
                new Initializer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(apply);


        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void loadOption(int state) throws IOException { // loads the option for the setting
        switch (state) {
            case 1 -> {
                Color bg = JColorChooser.showDialog(null, "Choose a background color", new Color(65, 61, 61));
                if (bg != null) {
                    String background = String.format("%d,%d,%d", bg.getRed(), bg.getGreen(), bg.getBlue());
                    write(1, "bg.cfg", background);
                    repaint();
                }
            }
            case 2 -> {
                Color border = JColorChooser.showDialog(null, "Choose a background color", new Color(238, 130, 238));
                if (border != null) {
                    String b = String.format("%d,%d,%d", border.getRed(), border.getGreen(), border.getBlue());
                    write(1, "border.cfg", b);
                }
            }
            case 3 -> {
                Integer[] range = new Integer[16];
                for (int i = 0; i < 16; i++) {
                    range[i] = i + 1;
                }
                JComboBox<Integer> comboBox = new JComboBox<>(range);
                comboBox.setSelectedItem(12);
                int result = JOptionPane.showOptionDialog(null,
                        comboBox,
                        "Font size",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new String[]{"Enter", "Cancel"},
                        "Enter");
                if (result == JOptionPane.OK_OPTION) {
                    int value = (int) comboBox.getSelectedItem();
                    write(1, "size.cfg", String.valueOf(value));
                }
            }
            case 4 -> {
                int option = JOptionPane.showOptionDialog(
                        null,
                        "Choose a type:",
                        "Button type",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new Object[]{"Flat", "Default"},
                        "Flat");
                if (option == 0) {
                    write(1, "button.cfg", "Flat");
                } else if (option == 1) {
                    write(1, "button.cfg", "Default");
                }
            }
        }
    }
}