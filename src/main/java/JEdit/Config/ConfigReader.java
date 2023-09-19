package JEdit.Config;

import java.awt.*;

public class ConfigReader {
    public static ConfigReader INSTANCE = new ConfigReader();
    public Config config = Config.INSTANCE;

    public String username() {
        return config.loadString("username") == null ? "Username" : config.loadString("username");
    }
    public String theme() {
        return config.loadString("theme") == null ? "Dark" : config.loadString("theme");
    }
    public String fontFamily(){
        return config.loadString("font");
    }
    public Font font(){
        return new Font(fontFamily(), Font.PLAIN, config.loadInt("fontSize"));
    }


}
