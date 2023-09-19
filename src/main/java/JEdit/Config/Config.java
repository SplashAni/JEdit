package JEdit.Config;

import JEdit.Windows.ImageWindow;
import JEdit.Utils.SystemUtils;
import JEdit.Windows.SetupWindow;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.io.File.separator;

public class Config {
    public static Config INSTANCE = new Config();
    String dir = System.getProperty("user.home") + separator + "JEdit";
    File dirFile = new File(dir);
    private final String path = dir + separator + "config.json";
    public final File pathFile = new File(path);
    private final Gson gson;
    private final JsonObject data;

    private Config() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        data = loadConfig();
    }
    public boolean isNew(){
        createDir();
        return !pathFile.exists();
    }
    public void createDir(){
       if(!dirFile.exists()){
           dirFile.mkdir();
       }
    }
    public void run() {

        if (isNew()) {

            SwingUtilities.invokeLater(SetupWindow::new);

        } else {
            if (!hasProperties(
                    "username",
                    "theme",
                    "font",
                    "fontSize"
            )) {

                pathFile.delete();

                SwingUtilities.invokeLater(SetupWindow::new);

                return;
            }

            SystemUtils.INSTANCE.installTheme();


            SwingUtilities.invokeLater(ImageWindow::new);
        }
    }
    public void saveString(String name, String value) {
        data.addProperty(name, value);
        saveConfig();
    }


    public String loadString(String name) {
        if (data.has(name)) {
            return data.get(name).getAsString();
        }
        return null;
    }
    public boolean hasProperties(String... names) {
        for (String name : names) {
            if (!data.has(name)) {
                return false;
            }
        }
        return true;
    }
    public void saveInt(String name, int value) {
        data.addProperty(name, value);
        saveConfig();
    }

    public int loadInt(String name) {
        if (data.has(name)) {
            return data.get(name).getAsInt();
        }
        return 12;
    }


    public void saveBoolean(String name, boolean value) {
        data.addProperty(name, value);
        saveConfig();
    }

    public boolean loadBoolean(String name) {
        if (data.has(name)) {
            return data.get(name).getAsBoolean();
        }
        return false;
    }


    private JsonObject loadConfig() {
        try {
            byte[] encodedBytes = Files.readAllBytes(Paths.get(path));
            String json = new String(encodedBytes, StandardCharsets.UTF_8);
            return gson.fromJson(json, JsonObject.class);
        } catch (IOException e) {
            return new JsonObject();
        }
    }

    private void saveConfig() {
        try (Writer writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void defaultConfig() {

        if (!pathFile.exists()) { /* this is gson's job :Sob: bruh*/
            try {
                pathFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (FileWriter writer = new FileWriter(pathFile)) {

            writer.write("""
                    {
                      "username": "User",
                      "theme": "System",
                      "font": "Arial",
                      "fontSize": 12
                    }""");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
