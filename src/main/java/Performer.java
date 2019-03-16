import java.util.ArrayList;
import java.util.List;

public class Performer {
    private String name;
    private ArrayList<Album> albumList;

    public Performer(){}
    public Performer(String name){
        this.name = name;
        this.albumList = new ArrayList<Album>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(ArrayList<Album> albumList) {
        this.albumList = albumList;
    }
}
