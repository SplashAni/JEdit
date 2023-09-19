package JEdit.Config;

public class ConfigVariables {
    public static ConfigVariables INSTANCE = new ConfigVariables();
    public Config config = Config.INSTANCE;

    public String username() {
        return config.loadString("username") == null ? "Username" : config.loadString("username");
    }

    public String theme() {
        return config.loadString("theme") == null ? "Dark" : config.loadString("theme");
    }

    public String fontStyle() {
        return config.loadString("fontStyle") == null ? "Regular" : config.loadString("fontStyle");
    }

    public int fontSize() {
        return config.loadInt("fontSize") == -1 ? 10 : config.loadInt("fontSize");
    }
}
