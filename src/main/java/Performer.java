import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Performer {
    private String name;
    private TreeMap<String, Album> albums;

    public Performer(){}
    public Performer(String name){
        this.name = name;
        this.albums = new TreeMap<>();
    }
    public Performer(String name, Song song){
        this.name = name;
        this.albums = new TreeMap<>();
        addSong(song);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeMap<String, Album> getAlbums() {
        return albums;
    }

    public void setAlbums(TreeMap<String, Album> albums) {
        this.albums = albums;
    }

    public void addSong(Song song){
        if(albums.get(song.getNameAlbum())!=null)
            albums.get(song.getNameAlbum()).addSong(song);
        else {
            albums.put(song.getNameAlbum(),new Album(song));
        }
    }

    public String describe(){
        String string = "Performer: "+this.name+"\n";
        for(Album album:albums.values())
            string+=album.describe();
        return string;
    }
}
