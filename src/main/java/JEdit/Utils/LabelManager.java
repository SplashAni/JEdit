package JEdit.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class LabelManager {
    JLabel label;
    boolean isHovered = false;

    public LabelManager(JLabel label) {
        this.label = label;
    }

    public void init() {

        this.label.addMouseListener(new MouseAdapter() {

            private final JLabel label = LabelManager.this.label;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isHovered) {
                    ThemeUtils.setTheme();

                    JFileChooser fileChooser = new JFileChooser();

                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Formats", "png", "jpeg", "jpg");
                    fileChooser.setFileFilter(filter);

                    fileChooser.setMultiSelectionEnabled(false);

                    int returnValue = fileChooser.showOpenDialog(null);

                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                        this.label.setIcon(imageIcon);
                        
                        this.label.setText("");

                        if (imageIcon.getIconWidth() > this.label.getWidth()) {
                            this.label.setPreferredSize(new Dimension(imageIcon.getIconWidth(), this.label.getHeight()));
                            this.label.revalidate();
                        }

                        if (imageIcon.getIconHeight() > this.label.getHeight()) {
                            this.label.setPreferredSize(new Dimension(this.label.getWidth(), imageIcon.getIconHeight()));
                            this.label.revalidate();
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;

                if (this.label.getIcon() == null) {
                    this.label.setText("Click to open file selector.");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;

                if (this.label.getIcon() == null) {
                    this.label.setText("Drag or click to add an image here");
                }
            }
        });
    }
}
