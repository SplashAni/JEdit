package JEdit;

import JEdit.Config.Config;
import JEdit.Config.ConfigWindow;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {

        FlatDarculaLaf.setup();

        Config.INSTANCE.run();

     }
}