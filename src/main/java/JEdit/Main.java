package JEdit;

import JEdit.Config.Config;
import JEdit.Config.ImgConfig;
import JEdit.Windows.EditorWindow;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Main{

    public static void main(String[] args) {

        for(String arg : args){
            if(arg.equals("Default")) Config.INSTANCE.defaultConfig();
            System.out.println("Created default config.");
            return;
        }

        FlatDarculaLaf.install();

     ///   Config.INSTANCE.run();
//        new EditorWindow();
        ImgConfig imgConfig = new ImgConfig(true);
        imgConfig.createDir();
        imgConfig.download();
    }
}