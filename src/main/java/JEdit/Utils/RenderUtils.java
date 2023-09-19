package JEdit.Utils;

import JEdit.Config.ConfigReader;

import javax.swing.*;

public class RenderUtils {
    public static RenderUtils INSTANCE = new RenderUtils();
    ConfigReader configReader = ConfigReader.INSTANCE;
    public void setFont(JButton... button){
        for(JButton b : button){
            b.setFont(configReader.font());
        }
    }
}
