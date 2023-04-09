package edit.java.Utils;

import java.io.*;

import static edit.java.Utils.Utils.MainPath;
import static java.io.File.separator;

public class FileUtils {
    private static File loaderConfig = new File(MainPath + separator + "Loader");

    public static void init(int stage) {

        switch (stage) {
            case 1:
                if (!loaderConfig.exists()) {
                    loaderConfig.mkdir();
                }
                break;
        }
    }

    public static void write(int stage, String file, String content) throws IOException {
        FileWriter w = new FileWriter(loaderConfig + separator + file);
        switch (stage) {
            case 1:
                w.write(content);
                w.close();
                break;
        }
    }

    public static String read(int stage, String file) throws IOException {
        switch (stage) {
            case 1:
                File readable = new File(loaderConfig + File.separator + file);
                if (!readable.exists()) {
                    return null;
                }
                FileReader reader = new FileReader(readable);
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
                reader.close();
                return content.toString();
            default:
                return null;
        }
    }
}