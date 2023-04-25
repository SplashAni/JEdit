package edit.java.Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static edit.java.Utils.Utils.MainPath;
import static java.io.File.separator;

public class FileUtils {
    private static final File loaderConfig = new File(MainPath + separator + "Loader");
    public static final File tempConfig = new File(MainPath + separator + "Temp");


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

    public static void write(int stage, String p, String content) throws IOException {
        FileWriter w = null;
        switch (stage) {
            case 1:
                w = new FileWriter(loaderConfig + separator + p);
                break;
            case 2:
                w = new FileWriter(tempConfig + separator + p);
                break;
        }
        w.write(content);
        w.close();
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
    public static String x() throws IOException { // this only works oh well
        String content = "";
        try {

            content = new String(Files.readAllBytes(Paths.get(System.getProperty("user.home") + separator + "JEdit\\Temp\\img.tmp"))); // for some reason it doesnt work with stages
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}