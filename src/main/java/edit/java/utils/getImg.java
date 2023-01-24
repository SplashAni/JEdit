package edit.java.utils;

import edit.java.Editor;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import static edit.java.Editor.renderImg;
import static edit.java.utils.config.createCnfg;

public class getImg extends JFrame implements MouseListener {

    private JLabel dropLabel;
    private JButton exitButton;
    private JButton enterButton;
    private JLabel imageLabel;
    static Color bordderColor = new Color(238, 130, 238);
    static Color bgColor = new Color(65, 61, 61, 247);
    public static final Color txtColor  = new Color(58, 54, 54);
    public static File file;


    public getImg() {
        setLayout(new BorderLayout());

        dropLabel = new JLabel("Drop file below:");
        dropLabel.setHorizontalAlignment(JLabel.CENTER);
        add(dropLabel, BorderLayout.NORTH);
        dropLabel.setForeground(Color.lightGray);
        getContentPane().setBackground(bgColor);


        imageLabel = new JLabel();
        imageLabel.setBorder(new LineBorder(bordderColor, 3));
        imageLabel.setPreferredSize(new Dimension(300, 300));
        add(imageLabel, BorderLayout.CENTER);

        imageLabel.addMouseListener(this);
        imageLabel.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }
                try {
                    java.util.List<File> files = (java.util.List<File>) support.getTransferable()
                            .getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : files) {
                        if (file.getName().endsWith(".png") || file.getName().endsWith(".jpeg")) {
                            ImageIcon image = new ImageIcon(file.getAbsolutePath());
                            imageLabel.setIcon(image);
                            getImg.file = file;
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid file type. Please drop a PNG image file.");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }


        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.setLayout(new FlowLayout());

        exitButton = new JButton("Exit");
        exitButton.setBackground(Color.gray);
        exitButton.setForeground(txtColor);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        enterButton = new JButton("Enter");
        enterButton.setBackground(Color.gray);
        enterButton.setForeground(txtColor);
        enterButton.setBorderPainted(false);
        enterButton.setFocusPainted(false);
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createCnfg();

                System.out.println();
                FileWriter writer = null;
                try {
                    writer = new FileWriter("JPaint\\" + "loc.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (getImg.file != null) {
                    try {
                        writer.write(getImg.file.getAbsolutePath());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        writer.write(getImg.file1.getAbsolutePath());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                dispose();
                new Editor();

            }
        });
        buttonPanel.add(enterButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Image Drop GUI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    public static File file1;

    @Override
    public void mousePressed(MouseEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and JPEG Images", "png", "jpeg", "jpg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(getImg.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file1 = fileChooser.getSelectedFile();
            ImageIcon img = new ImageIcon(file1.getAbsolutePath());
            imageLabel.setIcon(img);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        dropLabel.setText("Click below to choose file:");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        dropLabel.setText("Drop file below:");
    }
}