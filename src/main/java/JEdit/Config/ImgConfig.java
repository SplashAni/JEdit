package JEdit.Config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static java.io.File.separator;

public class ImgConfig {
    String path = Config.INSTANCE.dir + separator + "Assets";
    File filePath = new File(path);
    boolean downloadAll;
    String name;

    public ImgConfig(boolean downloadAll) {
        this.downloadAll = downloadAll;
    }

    public ImgConfig(String name) {
        this.downloadAll = false;
        this.name = name;
    }

    public void createDir() {
        if (!filePath.exists()) filePath.mkdir();
    }

    public void download() {
        if (downloadAll) {
            downloadImage("color");
        }
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
}
