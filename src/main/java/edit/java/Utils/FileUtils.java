package edit.java.Utils;

import java.io.*;

import static edit.java.Utils.Utils.MainPath;
import static java.io.File.separator;

public class FileUtils {
    private static final File loaderConfig = new File(MainPath + separator + "Loader");
    private static final File tempConfig = new File(MainPath + separator + "Temp");


    public static void init(int stage, boolean clear) {

        switch (stage) {
            case 1:
                if (!loaderConfig.exists())
                    loaderConfig.mkdir();
                break;
            case 2:
                if (!tempConfig.exists() && !clear)
                    tempConfig.mkdir();
                else if (clear) {
                    tempConfig.delete();
                    break;
                }
        }
    }

    public static void write(int stage, String file, String content) throws IOException {
        FileWriter w = new FileWriter(loaderConfig + separator + file);
        FileWriter w1 = new FileWriter(loaderConfig + separator + file);
        switch (stage) {
            case 1:
                w.write(content);
                w.close();
                break;
            case 2:
                w1.write(content);
                w1.close();
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
            case 2:
                File tempFile = new File(tempConfig + File.separator + file);
                if (!tempFile.exists()) {
                    return null;
                }
                FileReader tempReader = new FileReader(tempFile);
                BufferedReader tempBufferedReader = new BufferedReader(tempReader);
                StringBuilder tempContent = new StringBuilder();
                String tempLine;
                while ((tempLine = tempBufferedReader.readLine()) != null) {
                    tempContent.append(tempLine);
                    tempContent.append(System.lineSeparator());
                }
                tempReader.close();
                return tempContent.toString();
            default:
                return null;
        }
    }
}