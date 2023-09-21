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

                Object[] options = {"Update", "Exit"};
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Your version is outdated. Please update to the latest version.",
                        "Error: Version " + version + " < " + systemUtils.latestVersion(),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (choice == JOptionPane.YES_OPTION) {
                    systemUtils.openUrl("https://github.com/SplashAni/JEdit/releases");
                } else {
                    System.exit(0);
                }

                return;
            }
        }

        Config.INSTANCE.run();

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("ok");
        }));

    }
}