package edit.java.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static edit.java.Utils.Visuals.*;
import static java.io.File.separator;

public class Utils {
    public static final String MainPath = System.getProperty("user.home") + separator + "JEdit"; // do this once right pls
    public static final File MainFile = new File(MainPath);
    public static File imgPath = new File(MainPath + separator + "Images");

    public static boolean hasWifi() {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.getContent();
            urlConnect.disconnect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean hasDir() {
        File directory = new File(MainPath);
        return directory.exists() && directory.isDirectory();
    }

    public static void downloadImg(String imageUrl, String name) throws IOException { // we not using try chatches ok??
        if (!imgPath.exists()) {
            imgPath.mkdir();
        }
        URL url = new URL(imageUrl);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int n = 0;
        int totalBytes = 0;
        while (-1 != (n = in.read(buffer))) {
            out.write(buffer, 0, n);
            totalBytes += n;
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream(imgPath + separator + name);
        fos.write(response);
        fos.close();
    }

    public static Image readImage(String name) throws IOException {
        File file = new File(MainPath + separator + "Images" + separator + name);
        return ImageIO.read(file);
    }

    public static String i() {
        Color background = background();
        String colorStr = background.equals(new Color(65, 61, 61)) ? "Gray" : String.format("%d,%d,%d", background.getRed(), background.getGreen(), background.getBlue());
        return String.format("%s [%s]", background.equals(new Color(65, 61, 61)) ? "Default" : "Custom", colorStr);
    }

    public static String i1() {
        Color lineColor = border(1).getLineColor();
        String colorStr = lineColor.equals(new Color(238, 130, 238)) ? "Purple" : String.format("%d,%d,%d", lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue());
        return String.format("%s [%s]", lineColor.equals(new Color(238, 130, 238)) ? "Default" : "Custom", colorStr);
    }

    public static String i2() {
        int size = size();
        return String.format("%s [%d]", size == 12 ? "Default" : "Custom", size);
    }

    public static String i3() throws IOException {
        JButton button = Visuals.settingButton();
        return String.format("%s [%s]", button.isOpaque() ? "Custom" : "Default", button.isOpaque() ? "Flat" : "Icon");
    }

    public static void setupResizing(JLabel label, ImageIcon image) {
        label.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    Image scaledImage = image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(scaledImage));
                });
            }
        });
    }


    public static void setDroppable(JLabel label) {
        label.setTransferHandler(new TransferHandler() {
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
                        if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg")) {
                            ImageIcon image = new ImageIcon(file.getAbsolutePath());
                            Image scaledImage = image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                            label.setIcon(new ImageIcon(scaledImage));
                            System.out.println(image);

                            setupResizing(label, new ImageIcon(scaledImage));

                        } else {
                            JOptionPane.showMessageDialog(null, "Only png and jpg files are supported!");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        });
    }
    public static String winSize(JFrame frame) {
        int width = frame.getWidth();
        int height = frame.getHeight();
        return width + "x" + height;
    }
}