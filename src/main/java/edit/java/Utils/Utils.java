package edit.java.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

import static java.io.File.separator;

public class Utils {
    public static final String MainPath = System.getProperty("user.name") + separator + "JPaint"; // do this once right pls
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
}