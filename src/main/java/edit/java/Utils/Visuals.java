package edit.java.Utils;

import javax.swing.border.LineBorder;
import java.awt.*;

public class Visuals {
    public static Color Background = new Color(65, 61, 61, 247);

    public static LineBorder border(int thickness) {
        return new LineBorder(new Color(238, 130, 238), thickness);
    }

}