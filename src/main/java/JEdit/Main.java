package JEdit;

import JEdit.Config.Config;
import com.formdev.flatlaf.FlatDarculaLaf;

public class Main{

    public static void main(String[] args) {

        for(String arg : args){
            if(arg.equals("Default")) Config.INSTANCE.defaultConfig();
            System.out.println("Created default config.");
            return;
        }

        FlatDarculaLaf.setup();

        Config.INSTANCE.run();

    }
}