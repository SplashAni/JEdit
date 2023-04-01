package edit.java.Config;

import javax.swing.*;

import static edit.java.Utils.Utils.*;

public class Config {
    private JProgressBar progressBar;

    public void init() {
        if (!hasWifi()) JOptionPane.showMessageDialog(null, "You need wifi to setup", "):", JOptionPane.ERROR_MESSAGE);
        else if (!hasDir()) MainFile.mkdir();
    }
}