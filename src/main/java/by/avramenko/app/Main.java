package by.avramenko.app;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException, NoSuchAlgorithmException {
        String path = "D://Музыка";
        System.out.println(path);
        Cataloger cataloger = new Cataloger(path);
        cataloger.parseDirectories();
        cataloger.generateHTML(path,"index");
        cataloger.generateHashCodeDuplicateList(path,"duplicates_1");
        cataloger.generateNameDuplicateList(path,"duplicates_2");
    }
}
