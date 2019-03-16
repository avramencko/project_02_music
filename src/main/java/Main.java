import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.ContentHandler;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, TikaException, SAXException {
        String path = "D://Музыка";
        System.out.println(path);
        File directory = new File(path);
        ArrayList<File> files = listFilesForFolder(directory);

        InputStream input = new FileInputStream(files.get(2));
        ContentHandler handler = new DefaultHandler();
        Metadata metadata = new Metadata();
        Parser parser = new Mp3Parser();
        ParseContext parseCtx = new ParseContext();
        parser.parse(input, handler, metadata, parseCtx);
        System.out.println("----------------------------------------------");
        System.out.println("Title: " + metadata.get("title"));
        System.out.println("Artists: " + metadata.get("xmpDM:artist"));
        System.out.println("Composer : "+metadata.get("xmpDM:composer"));
        System.out.println("Genre : "+metadata.get("xmpDM:genre"));
        System.out.println("Album : "+metadata.get("xmpDM:album"));
        for(File file:files) {
            System.out.println(file.getName());

        }

        ArrayList<Performer> performers = new ArrayList<Performer>();
        /*File[] files = directory.listFiles();
        for (int i = 0; i<files.length;i++)
        System.out.println(files[i].getName());*/
    }
    public static ArrayList<File> listFilesForFolder(File folder){
        ArrayList<File> files = new ArrayList<File>();
        for(File fileEntry:folder.listFiles()){
            if(fileEntry.isDirectory()){
                files.addAll(listFilesForFolder(fileEntry));
            }else{
                if(fileEntry.getName().endsWith(".mp3"))
                files.add(fileEntry);
            }
        }
        return files;
    }
}
