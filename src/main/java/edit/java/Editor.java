package edit.java;

import edit.java.utils.config;

import javax.swing.*;

public class Editor {
    public static JFrame editWindow;
    public static JLabel renderImg;
    public Editor(){
        renderImg = new JLabel();
        editWindow = new JFrame("Java image editor");

        config.load();
    }
}
