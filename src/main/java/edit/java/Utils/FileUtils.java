package edit.java.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static edit.java.Utils.Utils.MainPath;
import static java.io.File.separator;

public class FileUtils {
    private static File loaderConfig = new File(MainPath + separator + "Loader");

    public static void init(int stage){

       switch (stage){
           case 1:
               if(!loaderConfig.exists()){
                   loaderConfig.mkdir();
               }
               break;
       }
    }
    public static void write(int stage,String file,String content) throws IOException {
        FileWriter w = new FileWriter(loaderConfig + separator + file);
        switch (stage){
            case 1:
                w.write(content);
                w.close();
                break;
        }
    }
}
