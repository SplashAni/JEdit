package JEdit;

import JEdit.Config.Config;
import JEdit.Utils.SystemUtils;
import JEdit.Windows.EditorWindow;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;


public class Main {
    public static Main INSTANCE = new Main();
    static SystemUtils systemUtils = SystemUtils.INSTANCE;
    public static double version = 2.0;

    public static void main(String[] args) {

        boolean noCheck = false;

        for (String arg : args) {
            if (arg.equals("NoCheck")) {
                noCheck = true;
            }

            if (arg.equals("Default")) {
                Config.INSTANCE.defaultConfig();
                System.out.println("Created default config.");
                return;
            }
        }

        if(!noCheck) {

            FlatDarculaLaf.setup();

            if (!systemUtils.hasConnectivity()) {

                JOptionPane.showMessageDialog(null,
                        "Cannot connect to server, a wifi connection is required.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            if (!systemUtils.isOutdated()) {

                JOptionPane.showMessageDialog(null,
                        "You version is outdated please update to latest version.",
                        "Error".concat(" current version " + version + " < " + systemUtils.latestVersion()),
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }

        new EditorWindow();

    }
}