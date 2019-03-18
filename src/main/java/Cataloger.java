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
