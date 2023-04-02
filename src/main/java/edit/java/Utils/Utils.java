package edit.java.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.io.File.separator;

public class Utils {
    public static final String MainPath = System.getProperty("user.home") + separator + "JEdit"; // do this once right pls
    public static final File MainFile = new File(MainPath);

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
        File imgPath = new File(MainPath + separator + "Images");
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
}