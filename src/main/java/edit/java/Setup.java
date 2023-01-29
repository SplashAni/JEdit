package edit.java;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import static edit.java.utils.config.createCnfg;
import static edit.java.utils.config.home;
import static edit.java.utils.imgEffects.orgImage;

public class Setup extends JFrame implements MouseListener {

    private JLabel dropLabel;
    private JButton exitButton;
    private JButton enterButton;
    private JLabel imageLabel;
    static Color borderColor = new Color(238, 130, 238);
    static Color bgColor = new Color(65, 61, 61, 247);
    public static final Color txtColor  = new Color(58, 54, 54);
    public static File file;


    public Setup() {
        setLayout(new BorderLayout());

        dropLabel = new JLabel("Drop file below:");
        dropLabel.setHorizontalAlignment(JLabel.CENTER);
        add(dropLabel, BorderLayout.NORTH);
        dropLabel.setForeground(Color.lightGray);
        getContentPane().setBackground(bgColor);


        imageLabel = new JLabel();
        imageLabel.setBorder(new LineBorder(borderColor, 3));
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
                        if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg")) {
                            ImageIcon image = new ImageIcon(file.getAbsolutePath());
                            imageLabel.setIcon(image);
                            Setup.file = file;
                        } else {
                            JOptionPane.showMessageDialog(null, "Please drop a PNG / JPEG");
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

                FileWriter writer = null;
                try {
                    writer = new FileWriter(home + File.separator + "JEdit" + File.separator + "path.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (Setup.file != null) {
                    try {
                        writer.write(Setup.file.getAbsolutePath());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        writer.write(Setup.file1.getAbsolutePath());
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
                orgImage();
            }
        });
        buttonPanel.add(enterButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Select image");
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
        int returnVal = fileChooser.showOpenDialog(Setup.this);
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