package JEdit.Windows;

import javax.swing.*;

public class EditorWindow extends JFrame {
    public EditorWindow(){
        super("Editor");


        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
