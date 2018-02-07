import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Playlist {
    private String playlistName;
    private ArrayList<Album> albums;
    private boolean isNull = false;

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public Playlist(String playlistName) {
        this.playlistName = playlistName;
        this.albums = new ArrayList<>();
    }

    public boolean isNull() {
        return isNull;
    }

    public void addAlbum(String name) {
        this.albums.add(new Album(name));
    }

    public Album findAlbum(String albumName) throws IOException {
        isNull = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < this.albums.size(); i++) {
            Album checkedAlbum = albums.get(i);
            if (checkedAlbum.getNameOfAlbum().equals(albumName)) {
                return checkedAlbum;
            }
        }
        System.out.printf("Album doesn't exist. Do you want to create the new album?%nUse Y or N: %n");
        String answer = reader.readLine().toLowerCase();
        switch (answer) {
            case "y":
                this.albums.add(new Album(albumName));
                return albums.get(0);
            case "n":
                isNull = true;
                return null;
        }
        return null;
    }
}
