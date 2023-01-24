package edit.java;

import edit.java.utils.config;
import edit.java.utils.getImg;

import javax.swing.*;

public class Editor {
    public static JFrame editWindow;
    public static JLabel renderImg;
    public static JMenuBar options;
    public Editor(){
        renderImg = new JLabel();
        editWindow = new JFrame("Java image editor");
        options = new JMenuBar();


        config.load();
    }
}
