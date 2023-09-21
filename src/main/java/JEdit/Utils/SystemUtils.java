package JEdit.Utils;

import JEdit.Config.ConfigReader;
import JEdit.Main;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.jthemedetecor.OsThemeDetector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

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
    public boolean hasConnectivity() {
        try {
            URL url = new URL("https://github.com/SplashAni/JEdit");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();

            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            return false;
        }
    }


    public boolean isOutdated(){
        return latestVersion() == Main.INSTANCE.version;
    }
    public double latestVersion(){
        try {
            return Double.parseDouble(new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/SplashAni/JEdit/main/version").openStream())).readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
