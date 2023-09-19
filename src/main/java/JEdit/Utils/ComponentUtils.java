package JEdit.Utils;

import JEdit.Config.Config;
import JEdit.Windows.EditorWindow;
import JEdit.Windows.RetrieverWindow;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class ComponentUtils {
    public static ComponentUtils INSTANCE = new ComponentUtils();
    Config config = Config.INSTANCE;
    JLabel label;
    boolean isHovered = false;
    public ComponentUtils(){

    }
    public ComponentUtils(JLabel label) {
        this.label = label;
    }


    public void init() {

        label.setDropTarget(new DropTarget(label, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent event) {
                try {
                    Transferable tr = event.getTransferable();
                    if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        event.acceptDrop(DnDConstants.ACTION_COPY);
                        List<File> files = (List<File>) tr.getTransferData(DataFlavor.javaFileListFlavor);

                        if (!files.isEmpty()) {
                            File file = files.get(0);
                            ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                            config.saveString("path",file.getAbsolutePath());

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

            private final JLabel label = ComponentUtils.this.label;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isHovered) {

                    JFileChooser fileChooser = new JFileChooser();

                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Formats", "png", "jpeg", "jpg");
                    fileChooser.setFileFilter(filter);

                    fileChooser.setMultiSelectionEnabled(false);

                    int returnValue = fileChooser.showOpenDialog(null);

                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                        config.saveString("path",file.getAbsolutePath());

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
    public void registerLimit(JButton button, JTextField field) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = field.getText();
                button.setEnabled(text.length() >= 3);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = field.getText();
                button.setEnabled(text.length() >= 3);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }
}
