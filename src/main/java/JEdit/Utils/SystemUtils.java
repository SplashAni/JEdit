package JEdit.Utils;

import JEdit.Config.ConfigReader;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.jthemedetecor.OsThemeDetector;

public class SystemUtils {
    public static SystemUtils INSTANCE = new SystemUtils();
    public ConfigReader configReader = ConfigReader.INSTANCE;
    public final OsThemeDetector detector = OsThemeDetector.getDetector();

    public void installTheme(){ /* all credits due to : https://stackoverflow.com/questions/60321706/is-there-a-way-to-detect-whether-mac-os-or-windows-os-is-in-dark-mode-in-java*/

        switch (configReader.theme()){
            case "Dark" -> FlatDarculaLaf.setup();
            case "Light" -> FlatLightLaf.setup();
            case "System" -> {
                boolean  x = detector.isDark() ? FlatDarculaLaf.setup() : FlatLightLaf.setup();;
            }
        }
    }
}
