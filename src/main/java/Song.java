public class Song {
    private String name;
    private String path;
    private String nameAlbum;
    private Float duration;
//TODO длительность
    public Song(){}
    public Song(String name, String path, String nameAlbum, float duration){
        this.name = name;
        this.path = path;
        this.nameAlbum = nameAlbum;
        this.duration = duration;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
    public String describe(){
        return "\t\tName: "+this.name+"; time: "+ Cataloger.durationToString(this.duration.toString())+"; ("+this.path +")\n";
    }
}
