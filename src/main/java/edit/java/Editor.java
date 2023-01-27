package edit.java;

import edit.java.utils.config;

import javax.swing.*;
import static edit.java.utils.imgEffects.orgImage;

public class Editor {
    public static JFrame editWindow;
    public static JLabel renderImg;
    public static JMenuBar options;
    public Editor(){
        renderImg = new JLabel();
        editWindow = new JFrame();

        options = new JMenuBar();

        config.load();

    }

}
