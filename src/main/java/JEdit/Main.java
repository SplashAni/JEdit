package JEdit;

import JEdit.Config.Config;
import JEdit.Config.Downloader;
import JEdit.Utils.ImageUtils;
import JEdit.Windows.EditorWindow;
import JEdit.Windows.RetrieverWindow;
import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.*;
import java.io.File;

public class Main{

    public static void main(String[] args) {

        for(String arg : args){
            if(arg.equals("Default")) Config.INSTANCE.defaultConfig();
            System.out.println("Created default config.");
            return;
        }

        FlatDarculaLaf.setup();


        new EditorWindow();

    }
}