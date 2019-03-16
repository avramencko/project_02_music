import java.util.ArrayList;
import java.util.List;

public class Album {
    private String name;
    private ArrayList<Song> songList;

    public Album(){}

    public Album(String name){
        this.name = name;
        this.songList = new ArrayList<Song>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public void setSongList(ArrayList<Song> songList) {
        this.songList = songList;
    }
    public void addSong(Song song){
        songList.add(song);
    }
}
