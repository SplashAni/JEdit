package JEdit.Utils;

import JEdit.Config.Config;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.jthemedetecor.OsThemeDetector;

public class SystemUtils {
    public Config config = Config.INSTANCE;
    public final OsThemeDetector detector = OsThemeDetector.getDetector();

    public void installTheme(){ /*all credits due to : https://stackoverflow.com/questions/60321706/is-there-a-way-to-detect-whether-mac-os-or-windows-os-is-in-dark-mode-in-java*/

        switch (config.loadString("theme")){
            case "Dark" -> FlatDarculaLaf.setup();
            case "Light" -> FlatLightLaf.setup();
            case "System" -> {
                boolean  x = detector.isDark() ? FlatDarculaLaf.setup() : FlatLightLaf.setup();;
            }
        }
    }

}
