package edit.java.Config;

import edit.java.Initializer;
import edit.java.Utils.FileUtils;
import edit.java.Utils.Utils;
import edit.java.Utils.Visuals;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static edit.java.Utils.FileUtils.write;
import static edit.java.Utils.Utils.setupResizing;
import static edit.java.Utils.Visuals.*;


public class Windows {

    public static JFrame l;

    public static void loader() throws IOException {
        FileUtils.init(1, false);
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
            ls[i].setForeground(textCol());
            bs[i] = new JButton(options[i]);
            bs[i].setFont(new Font("Helvetica", Font.PLAIN, 18));
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
            case 1 -> {
                Color bg = JColorChooser.showDialog(null, "Choose a background color", new Color(65, 61, 61));
                if (bg != null) {
                    String background = String.format("%d,%d,%d", bg.getRed(), bg.getGreen(), bg.getBlue());
                    write(1, "bg.cfg", background);
                    l.repaint();
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

    public static String pathChooser(JLabel setThis) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("png / jpeg", "png", "jpeg", "jpg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            write(2, "img.temp", fileChooser.getSelectedFile().getAbsolutePath());
            setupResizing(setThis, new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath()));
        }
        return null;
    }

    public static void settingsGui() {
        JFrame f = new JFrame("My Frame");

        Color y = new Color(73, 70, 70);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(750, 500);

        JButton[] buttons = {new JButton("Option 1"), new JButton("Option 2"), new JButton("Option 3"), new JButton("Option 4"), new JButton("Option 5"), new JButton("Option 6")};
        JPanel lp = new JPanel(new GridLayout(buttons.length, 1));
        lp.setBackground(defaultButton("").getBackground());
        for (JButton b : buttons) {
            b.setPreferredSize(new Dimension(80, 30));
            b.setFont(b.getFont().deriveFont(12f)); //
            b.setMargin(new Insets(5, 10, 5, 10)); // set padding
            styleButton(b);
            lp.add(b);
        }

        JTextField sf = new JTextField();
        sf.setForeground(textCol());
        Font font = new Font("Arial", Font.BOLD, 14);
        sf.setFont(font);
        sf.setBackground(y);
        sf.setBorder(BorderFactory.createEmptyBorder());
        sf.setBorder(border(2));
        JButton fb = defaultButton("Search");

        fb.addActionListener(e -> { // to much of a pro
            String searchText = sf.getText();
            for (int i = 0; i < buttons.length; i++) {
                JButton b = buttons[i];
                if (b.getText().toLowerCase().contains(searchText.toLowerCase())) {
                    lp.remove(b);
                    lp.add(b, 0);
                }
            }
            lp.revalidate();
            lp.repaint();
        });

        JPanel tp = new JPanel(new BorderLayout());
        tp.setBackground(y);
        tp.add(sf, BorderLayout.CENTER);
        tp.add(fb, BorderLayout.EAST);

        JPanel rp = new JPanel(new BorderLayout());

        JSeparator s = new JSeparator(SwingConstants.HORIZONTAL);
        s.setPreferredSize(new Dimension(rp.getWidth(), 1));
        rp.add(s, BorderLayout.SOUTH);

        JPanel bp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bp.setBackground(y);

        JButton b1 = defaultButton("Close");
        bp.add(b1);

        JButton b2 = defaultButton("Apply");
        bp.add(b2);
        bp.setBorder(BorderFactory.createEmptyBorder());

        f.add(bp, BorderLayout.SOUTH);

        rp.setBackground(y);

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lp, rp);
        sp.setBackground(Color.lightGray);
        sp.setOneTouchExpandable(true);
        sp.setDividerLocation(200);
        sp.setDividerSize(10);

        BasicSplitPaneDivider d = (BasicSplitPaneDivider) sp.getComponent(2);
        d.setBorder(border(2));

        f.add(tp, BorderLayout.NORTH);
        f.add(sp, BorderLayout.CENTER);

        f.setVisible(true);
    }
}