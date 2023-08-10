package edit.java;


import edit.java.Config.Config;
import edit.java.Gui.ConfigGui;
import edit.java.Gui.Editor;
import edit.java.Utils.FileUtils;
import edit.java.Utils.Utils;
import edit.java.Utils.Visuals;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import static edit.java.Utils.FileUtils.write;
import static edit.java.Utils.Utils.setupResizing;
import static edit.java.Utils.Visuals.background;
import static edit.java.Utils.Visuals.settingButton;

public class Initializer extends JFrame implements MouseListener { // guis will be done in 1 thread (doesnt take much time)

    private static JLabel heading;
    JLabel imgRenderer;

    public Initializer() throws IOException {
        super("Insert image");


        FileUtils.init(2,false);

        JPanel buttonManager = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        imgRenderer = new JLabel();
        imgRenderer.addMouseListener(this);
        heading = new JLabel("Click below to add image:", SwingConstants.CENTER);


        imgRenderer.setPreferredSize(new Dimension(600, 450));

        Utils.setDroppable(imgRenderer);

        imgRenderer.setBorder(Visuals.border(3));
        add(imgRenderer, BorderLayout.CENTER);

        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Helvetica", Font.BOLD, Visuals.size()));
        add(heading, BorderLayout.NORTH);


        JButton enter = Visuals.defaultButton("Enter");

        enter.addActionListener(e -> {
            if (imgRenderer.getIcon() != null) {
                try {
                        this.dispose();
                        new Editor();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        JButton setting = settingButton();
        setting.addActionListener(e -> {
            try {
                dispose();
                new ConfigGui();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonManager.setLayout(new FlowLayout());
        buttonManager.setBackground(background());
        buttonManager.add(setting);
        buttonManager.add(enter);

        setSize(750, 520);
        add(buttonManager, BorderLayout.SOUTH);
        getContentPane().setBackground(background());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // zoom
    }

    @Override
    public void mousePressed(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("png / jpeg", "png", "jpeg", "jpg");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    write(2, "img.temp", path);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                imgRenderer.setIcon(new ImageIcon(path));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        heading.setText("Drop file below:");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        heading.setText("Click below to add image:");
    }
}