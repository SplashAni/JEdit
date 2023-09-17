package JEdit;

import JEdit.Config.ConfigWindow;
import com.formdev.flatlaf.FlatDarculaLaf;

public class Main{

    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        //new Initializer();
        new ConfigWindow();
     }
}