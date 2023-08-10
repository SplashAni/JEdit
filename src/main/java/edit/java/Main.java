package edit.java;

import edit.java.Config.Config;

import java.io.IOException;


public class Main {

    public static final String VERSION = "2.0";
    public static void main(String[] args) throws  IOException{
      //  if(!Config.init()) return;
       new Initializer();
     // new Editor();
    }

}
