package JEdit.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class LabelManager {
    JLabel label;
    boolean isHovered = false;

    public LabelManager(JLabel label) {
        this.label = label;
    }

    public void init() {

        // Enable drop support on the label
        label.setDropTarget(new DropTarget(label, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent event) {
                try {
                    Transferable tr = event.getTransferable();
                    if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        event.acceptDrop(DnDConstants.ACTION_COPY);
                        List<File> files = (List<File>) tr.getTransferData(DataFlavor.javaFileListFlavor);

                        // Handle the dropped files (you can choose to handle multiple files here)
                        if (!files.isEmpty()) {
                            File file = files.get(0); // Get the first file (you can handle multiple if needed)
                            ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                            label.setIcon(imageIcon);
                            label.setText("");

                            if (imageIcon.getIconWidth() > label.getWidth() ||
                                    imageIcon.getIconHeight() > label.getHeight()) {
                                label.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
                                label.revalidate();
                            }
                        }

                        event.dropComplete(true);
                    } else {
                        event.rejectDrop();
                    }
                } catch (Exception e) {
                    event.rejectDrop();
                }
            }
        }));

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

                        if (imageIcon.getIconWidth() > this.label.getWidth() ||
                                imageIcon.getIconHeight() > this.label.getHeight()) {
                            this.label.setPreferredSize(new Dimension(imageIcon.getIconWidth(), this.label.getHeight()));
                            this.label.revalidate();
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;

                if (this.label.getIcon() == null) {
                    this.label.setText("Click to open file selector or drag and drop an image here.");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;

                if (this.label.getIcon() == null) {
                    this.label.setText("Drag and drop an image or click to add an image here");
                }
            }
        });
    }
}
