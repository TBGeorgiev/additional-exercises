import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Player {
    private ArrayList<Playlist> playlists;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public Player() {
        this.playlists = new ArrayList<>();

    }

    public void createPlaylist(String name) {
        this.playlists.add(new Playlist(name));
        System.out.println("Playlist created.");
    }

    public void addAlbumToPlaylist(String playlistName, String albumName) {
        Playlist playlist = findPlaylist(playlistName);
        if (playlist != null) {
            playlist.addAlbum(albumName);
        }
    }

    public void addSongsToAlbum(String playlistName, String albumName, String songName, String duration) throws IOException {
        Album album = findPlaylist(playlistName).findAlbum(albumName);
        album.addSongToAlbum(songName, duration);
    }

    public Playlist findPlaylist(String name) {
        for (int i = 0; i < playlists.size(); i++) {
            Playlist checkedPlaylist = playlists.get(i);
            if (checkedPlaylist.getPlaylistName().equals(name)) {
                return checkedPlaylist;
            }
        }
        return null;
    }

    public void removeSong() throws IOException {
        System.out.printf("Found %d playlists:%n", playlists.size());
        for (int i = 0; i < playlists.size(); i++) {
            System.out.printf("%d: %s (%d album/s)%n", i+1, playlists.get(i).getPlaylistName(), playlists.get(i).getAlbums().size());
        }
        System.out.print("Please enter the number of the playlist: ");
        int numberOfPlaylist = Integer.parseInt(reader.readLine());
        Playlist playlistToRemove = playlists.get(numberOfPlaylist - 1);
        System.out.println("Please select which one you want to enter:");
        for (int i = 0; i < playlistToRemove.getAlbums().size(); i++) {
            System.out.printf("%d: %s (%d song/s)%n", i+1, playlistToRemove.getAlbums().get(i).getNameOfAlbum(), playlistToRemove.getAlbums().get(i).getSongs().size());
        }
        System.out.print("Enter the album number: ");
        int input = Integer.parseInt(reader.readLine());
        Album albumToRemove = playlistToRemove.getAlbums().get(input - 1);
        for (int i = 0; i < albumToRemove.getSongs().size(); i++) {
            System.out.printf("%d: %s%n", i+1, albumToRemove.getSongs().get(i).getSongTitle());
        }
        System.out.println("Please select which song/s you want to remove. Enter 'end' to submit your selection.");
        ArrayList<Integer> songsToRemove = new ArrayList<>();
        String toSubmit = "";
        while (!(toSubmit = reader.readLine()).equals("end")) {
            int toRemove = Integer.parseInt(toSubmit);
            songsToRemove.add(toRemove);
        }
        int songsRemoved = 0;
        for (int i = 0; i < albumToRemove.getSongs().size(); i++) {
            if (songsToRemove.contains(i+1)) {
                albumToRemove.getSongs().remove(i);
                songsRemoved++;
                System.out.printf("%d songs removed successfully.%n", songsRemoved);
            }
        }

    }

    public void listAllPlaylistsAlbumsAndSongs() {
        System.out.printf("==Found %d playlists.==%n", playlists.size());
        for (int i = 0; i < playlists.size(); i++) {
            System.out.printf("%d: %s%n", i+1, playlists.get(i).getPlaylistName());
            Playlist playlist = this.playlists.get(i);
            if (playlist.getAlbums().size() < 1) {
                System.out.println("Playlist is empty.");
                continue;
            }
            System.out.printf("<<Total of %d albums found.>>%n", playlist.getAlbums().size());
            for (int j = 0; j < playlist.getAlbums().size(); j++) {
                System.out.printf("  %d: **%s**%n", j+1, playlist.getAlbums().get(j).getNameOfAlbum());
                System.out.printf("Contains %d songs:%n", playlist.getAlbums().get(j).getSongs().size());
                for (int k = 0; k < playlist.getAlbums().get(j).getSongs().size(); k++) {
                    System.out.printf("     %d: %s%n", k+1, playlist.getAlbums().get(j).getSongs().get(k).getSongTitle());
                    System.out.printf("     Duration: %s%n", playlist.getAlbums().get(j).getSongs().get(k).getSongDuration());
                }
            }
        }
    }
}
