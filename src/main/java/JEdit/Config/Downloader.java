package JEdit.Config;

import JEdit.Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static java.io.File.separator;

public class Downloader {
    String path = Config.INSTANCE.dir + separator + "Assets";
    public static Downloader INSTANCE = new Downloader();
    ConfigReader configReader = ConfigReader.INSTANCE;
    List<String> assets = new ArrayList<>();
    File filePath = new File(path);
    boolean downloadAll;
    String name;

    public Downloader() {
    }

    public Downloader(boolean downloadAll) {
        this.downloadAll = downloadAll;
    }

    public Downloader(String name) {
        this.downloadAll = false;
        this.name = name;
    }
    public List<JButton> buttons() {
        List<JButton> btns = new ArrayList<>();
        for (String n : assets) {
            btns.add(new JButton(n));
        }
        return btns;
    }

    public void download() {
        createDir();
        if (downloadAll) {
            add("backward", "color", "forward", "lowercase", "paintbrush", "pencil");

            for (String s : assets) {
                downloadImage(s);
                ImageUtils.INSTANCE.replaceColor(new File(this.path, s.concat(".png")), Color.BLACK,Color.PINK);
            }
        }
    }


    public void createDir() {
        if (!filePath.exists()) filePath.mkdir();
    }

    public boolean exists(String name) {
        return new File(filePath, name.concat(".png")).exists();
    }

    public ImageIcon icon(String name) {
        return new ImageIcon(filePath + separator +  name.concat(".png"));
    }

    public void downloadImage(String name) {
        URL url;
        try {
            url = new URL("https://github.com/SplashAni/JEdit/raw/main/assets/" + name.concat(".png"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try (InputStream in = url.openStream()) {
            Path outputPath = Path.of(this.path, name.concat(".png"));
            Files.copy(in, outputPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void add(String... names) {
        for (String name : names) {
            if (!exists(name)) {
                assets.add(name);
            }
        }
    }
}
