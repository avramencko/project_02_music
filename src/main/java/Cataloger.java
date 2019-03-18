import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Cataloger {

    private String path;
    private TreeMap<String, Performer> performers;

    private final String HEAD = "<head>\n" +
            "<meta charset=\"utf-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "<title>Catalog</title>\n" +
            "</head>\n";

    private final String HTML = "<body>\n";
    private final String HTML_END = "</body>\n";
    private final String BODY = "<body>\n";
    private final String BODY_END = "</body>\n";

    private final String UL = "<ul type=\"none\">\n";
    private final String UL_END = "</ul>\n";
    private final String LI = "<li>\n";
    private final String LI_END = "</li>\n";

    private final String B = "<b>\n";
    private final String B_END = "</b>\n";

    public Cataloger(String path){
        this.path = path;
        this.performers = new TreeMap<>();
    }


    public void parseDirectories () throws InvalidDataException, IOException, UnsupportedTagException {
        File directory = new File(path);
        ArrayList<File> files = listFilesForFolder(directory);
        for(File file:files) {
            Mp3File mp3file = new Mp3File(file);
            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                Song song = new Song(id3v2Tag.getTitle(),file.getPath(),id3v2Tag.getAlbum(),mp3file.getLengthInSeconds());
                /*System.out.println("Track: " + id3v2Tag.getTrack());
                System.out.println("Artist: " + id3v2Tag.getArtist());
                System.out.println("Title: " + id3v2Tag.getTitle());
                System.out.println("Album: " + id3v2Tag.getAlbum());*/
                if(performers.get(id3v2Tag.getArtist())!=null)
                    performers.get(id3v2Tag.getArtist()).addSong(song);
                else
                    performers.put(id3v2Tag.getArtist(),new Performer(id3v2Tag.getArtist(),song));
            }

        }
        for(Performer performer:performers.values())
            System.out.println(performer.describe());
    }

    public void generateHTML(String path, String filename) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(path+"/"+filename+".html", "UTF-8");
        String strHtml = HTML+HEAD+BODY;

        for(Performer performer:performers.values()){
            strHtml += UL+LI+B+performer.getName()+B_END+UL;
            for(Album album : performer.getAlbums().values()){
                strHtml += LI + B + album.getName() + B_END + UL;
                for(Song song : album.getSongList()){
                    strHtml += LI + song.getName() + " " + Song.secondsToDuration(song.getDuration())
                            +"(<a href=\"file:///"+song.getPath()+"\">"+song.getPath()+"</a>)"+LI_END;
                }
                strHtml += UL_END + LI_END;
            }
            strHtml +=UL_END+ LI_END + UL_END;
        }

        strHtml += BODY_END+HTML_END;
        writer.println(strHtml);
        writer.close();
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


}
