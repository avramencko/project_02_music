import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException {
        String path = "D://Музыка/ccc";
        System.out.println(path);
        Cataloger cataloger = new Cataloger(path);
        cataloger.parseDirectories();
        cataloger.generateHTML(path,"index");
    }
}
