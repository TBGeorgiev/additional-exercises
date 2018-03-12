import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {
    private String nameOfAlbum;
    private ArrayList<Song> songs;

    public Album(String name) {
        this.nameOfAlbum = name;
        this.songs = new ArrayList<>();
    }

    public void addSongToAlbum(String songName, String songDuration) {
        Song song = new Song(songName, songDuration);
        songs.add(song);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public String getNameOfAlbum() {
        return nameOfAlbum;
    }
}
