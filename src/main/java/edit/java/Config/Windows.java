package edit.java.Config;

import edit.java.Initializer;
import edit.java.Utils.FileUtils;
import edit.java.Utils.Utils;
import edit.java.Utils.Visuals;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;

import static edit.java.Utils.FileUtils.write;
import static edit.java.Utils.Utils.imgPath;
import static edit.java.Utils.Utils.setupResizing;
import static edit.java.Utils.Visuals.background;


public class Windows {

    public static JFrame l;

    public static void loader() throws IOException {
        FileUtils.init(1,false);
        l = new JFrame("Edit Window");
        l.getContentPane().setBackground(background());
        l.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l.setSize(650, 500);
        l.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        l.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Edit window");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        l.add(titleLabel, BorderLayout.NORTH);

        JPanel textPanel = new JPanel(new GridLayout(4, 2));
        textPanel.setPreferredSize(new Dimension(500, 200));
        textPanel.setBackground(background());
        textPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createEmptyBorder()));
        textPanel.setBorder(Visuals.border(1));

        JLabel[] ls = new JLabel[4];
        JButton[] bs = new JButton[4];

        String[] titles = {"Background", "Border", "Size", "Button"};
        String[] options = {Utils.i(), Utils.i1(), Utils.i2(), Utils.i3()};

        for (int i = 0; i < 4; i++) {
            ls[i] = new JLabel(titles[i]);
            ls[i].setFont(new Font("Helvetica", Font.PLAIN, 18));
            ls[i].setForeground(Color.LIGHT_GRAY);
            bs[i] = new JButton(options[i]);
            bs[i].setFont(new Font("Helvetica", Font.PLAIN, 18));
            bs[i].setBorderPainted(false);
            bs[i].setFocusPainted(false);
            bs[i].setForeground(Color.LIGHT_GRAY);
            bs[i].setBackground(background());
            textPanel.add(ls[i]);
            textPanel.add(bs[i]);
        }
        for (int i = 0; i < bs.length; i++) {
            int z = i + 1;
            bs[i].addActionListener(e -> {
                try {
                    loaderGui(z);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        l.add(textPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(background());

        JButton cancel = Visuals.defaultButton("Default");

        cancel.addActionListener(e -> {
            l.dispose();
            try {
                new Initializer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(cancel);


        JButton apply = Visuals.defaultButton("Apply");

        apply.addActionListener(e -> {
            l.dispose();
            try {
                new Initializer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(apply);

        l.add(buttonPanel, BorderLayout.SOUTH);

        l.setVisible(true);
    }
    public static void loaderGui(int state) throws IOException { // this is actually an window :nerd:
        switch (state) {
            case 1:
                Color bg = JColorChooser.showDialog(null, "Choose a background color", new Color(65, 61, 61));
                if (bg != null) {
                    String background = String.format("%d,%d,%d", bg.getRed(), bg.getGreen(), bg.getBlue());
                    write(1, "bg.cfg", background);
                    l.repaint();
                }
                break;
            case 2:
                Color border = JColorChooser.showDialog(null, "Choose a background color", new Color(238, 130, 238));
                if (border != null) {
                    String b = String.format("%d,%d,%d", border.getRed(), border.getGreen(), border.getBlue());
                    write(1, "border.cfg", b);
                }
                break;
            case 3:
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
                break;
            case 4:
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
                    write(1,"button.cfg","Default");
                }
                break;
        }
    }
    public static String pathChooser(JLabel setThis) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("png / jpeg", "png", "jpeg", "jpg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            write(2,"img.temp",fileChooser.getSelectedFile().getAbsolutePath());
            setupResizing(setThis,new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath()));
        }
        return null;
    }
}
