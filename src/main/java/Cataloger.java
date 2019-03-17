import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Cataloger {

    private String path;

    public Cataloger(String path){
        this.path = path;
    }

    public void parseDirectories () throws IOException, TikaException, SAXException {
        File directory = new File(path);
        ArrayList<File> files = listFilesForFolder(directory);
        TreeMap<String, Performer> performers = new TreeMap<>();
        for(File file:files) {
            InputStream input = new FileInputStream(file);
            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            Song song = new Song(metadata.get("title"),file.getPath(),metadata.get("xmpDM:album"),Float.parseFloat(metadata.get("xmpDM:duration")));

            if(performers.get(metadata.get("xmpDM:artist"))!=null)
                performers.get(metadata.get("xmpDM:artist")).addSong(song);
            else
                performers.put(metadata.get("xmpDM:artist"),new Performer(metadata.get("xmpDM:artist"),song));

            System.out.println("----------------------------------------------");
            System.out.println("Title: " + metadata.get("title"));
            System.out.println("Artists: " + metadata.get("xmpDM:artist"));
            System.out.println("Duration : "+durationToString(metadata.get("xmpDM:duration")));
            System.out.println("Album : "+metadata.get("xmpDM:album"));
            System.out.println(file.getName());


        }
        for(Performer performer:performers.values())
            System.out.println(performer.describe());
    }
    private ArrayList<File> listFilesForFolder(File folder){
        ArrayList<File> files = new ArrayList<>();
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
    public static String durationToString(String duration){
        if(Float.parseFloat(duration)/1000%60<10)
            return String.format("%2.0f:0%1.0f",Float.parseFloat(duration)/1000/60,Float.parseFloat(duration)/1000%60);
        else
            return String.format("%2.0f:%2.0f",Float.parseFloat(duration)/1000/60,Float.parseFloat(duration)/1000%60);
    }
}
