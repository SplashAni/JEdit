package edit.java.Config;

import edit.java.Utils.Utils;

import javax.swing.*;

import java.io.IOException;

import static edit.java.Utils.Utils.*;

public class Config {
    private JProgressBar progressBar;

    public static void init() throws IOException {
        if (!hasWifi()) JOptionPane.showMessageDialog(null, "You need wifi to setup", "):", JOptionPane.ERROR_MESSAGE);
        else if (!hasDir()) MainFile.mkdir();

        if(!imgPath.exists()) {
            downloadImg("https://assets.stickpng.com/images/6002fa9051c2ec00048c6c7a.png", "setting.png");
        }

    }
}