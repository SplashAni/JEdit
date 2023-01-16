package edit.java.utils;

import static edit.java.Editor.editWindow;
import static edit.java.Editor.renderImg;

public class config {
    public static void load(){

        editWindow.add(renderImg);
        editWindow.setResizable(false);
        editWindow.setSize(600,400);
        editWindow.setVisible(true);
    }
}
