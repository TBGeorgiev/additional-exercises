import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private ArrayList<Playlist> playlists;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<String> listOfPlaylists;
    private String currentPlaylist;


    public Player() {
        this.playlists = new ArrayList<>();
        this.listOfPlaylists = new ArrayList<>();

    }

    public void createPlaylist() throws IOException {
        System.out.print("Please enter a name for the playlist you want to create: ");
        String playlistName = reader.readLine();
        this.currentPlaylist = playlistName;
        listOfPlaylists.add(playlistName);
        this.playlists.add(new Playlist(playlistName));
        System.out.println("Playlist created.");
    }

    public void createPlaylistForChoice(String name) {
        this.currentPlaylist = name;
        listOfPlaylists.add(name);
        this.playlists.add(new Playlist(name));
        System.out.println("Playlist created.");
    }

    public void addAlbumToPlaylist() throws IOException {
        System.out.print("Enter a name of the Album you want to create: ");
        String nameOfAlbum = reader.readLine();
        Playlist playlist = findPlaylist(currentPlaylist);
        if (playlist != null) {
            playlist.addAlbum(nameOfAlbum);
            System.out.printf("Album '%s' created.%n", nameOfAlbum);
        }
    }

    public void addSongsToAlbum() throws IOException {
        System.out.print("In which album do you want to store the song? Album name: ");
        String nameOfAlbum = reader.readLine();
        findPlaylist(currentPlaylist).findAlbum(nameOfAlbum);
        if (findPlaylist(currentPlaylist).isNull()) {
            System.out.println("No album was created. Returning to main menu.");
            return;
        }
        System.out.print("Enter the name of Song: ");
        String nameOfSong = reader.readLine();
        String durationOfSong = generateRandomLength();
        System.out.println("Duration of the song is: " + durationOfSong);
        Album album = findPlaylist(currentPlaylist).findAlbum(nameOfAlbum);
        album.addSongToAlbum(nameOfSong, durationOfSong);
        System.out.printf("Song was stored successfully in Album - %s%n", nameOfAlbum);
    }

    public void switchPlayList() throws IOException {
        System.out.println("Current playlist is: " + currentPlaylist);
        System.out.print("Enter the playlist you want to switch to: ");
        String nameOfPlaylistToSwitch = reader.readLine();
        if (listOfPlaylists.contains(nameOfPlaylistToSwitch)) {
            currentPlaylist = nameOfPlaylistToSwitch;
            System.out.println("Successfully switched to " + currentPlaylist);
        } else {
            System.out.println("Playlist not found. Do you want to create it?");
            System.out.print("Y / N: ");
            String answer = reader.readLine().toLowerCase();
            if (answer.equals("y")) {
                listOfPlaylists.add(nameOfPlaylistToSwitch);
                createPlaylistForChoice(nameOfPlaylistToSwitch);
                currentPlaylist = nameOfPlaylistToSwitch;
                System.out.printf("Playlist '%s' created. This is now the current playlist.%n", nameOfPlaylistToSwitch);
            } else {
                System.out.println("Returning to main menu.");
            }

        }
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
            System.out.printf("%d: %s (%d album/s)%n", i + 1, playlists.get(i).getPlaylistName(), playlists.get(i).getAlbums().size());
        }
        System.out.print("Please enter the number of the playlist: ");
        int numberOfPlaylist = Integer.parseInt(reader.readLine());
        Playlist playlistToRemove = playlists.get(numberOfPlaylist - 1);
        System.out.println("Please select which one you want to enter:");
        for (int i = 0; i < playlistToRemove.getAlbums().size(); i++) {
            System.out.printf("%d: %s (%d song/s)%n", i + 1, playlistToRemove.getAlbums().get(i).getNameOfAlbum(), playlistToRemove.getAlbums().get(i).getSongs().size());
        }
        System.out.print("Enter the album number: ");
        int input = Integer.parseInt(reader.readLine());
        Album albumToRemove = playlistToRemove.getAlbums().get(input - 1);
        for (int i = 0; i < albumToRemove.getSongs().size(); i++) {
            System.out.printf("%d: %s%n", i + 1, albumToRemove.getSongs().get(i).getSongTitle());
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
            if (songsToRemove.contains(i + 1)) {
                albumToRemove.getSongs().remove(i);
                songsRemoved++;
                System.out.printf("%d songs removed successfully.%n", songsRemoved);
            }
        }

    }

    public void toPlay() throws IOException {
        System.out.printf("Please select a playlist:%n");
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getAlbums().size() < 1) {
                System.out.printf("%d: %s (empty playlist)%n", i + 1, playlists.get(i).getPlaylistName());
            } else {
                System.out.printf("%d: %s (%d albums)%n", i + 1, playlists.get(i).getPlaylistName(), playlists.get(i).getAlbums().size());
            }
        }
        System.out.print("Enter playlist number: ");
        int playlistNumber = Integer.parseInt(reader.readLine());
        Playlist selectedPlaylist = this.playlists.get(playlistNumber - 1);
        for (int i = 0; i < selectedPlaylist.getAlbums().size(); i++) {
            System.out.printf("Album %d: %s (%d tracks)%n", i + 1, selectedPlaylist.getAlbums().get(i).getNameOfAlbum(), selectedPlaylist.getAlbums().get(i).getSongs().size());
        }
        System.out.print("Enter an album number: ");
        int albumNumber = Integer.parseInt(reader.readLine());
        Album selectedAlbum = selectedPlaylist.getAlbums().get(albumNumber - 1);
        for (int i = 0; i < selectedAlbum.getSongs().size(); i++) {
            System.out.printf("Now playing track %d: %s (%s)%n", i + 1, selectedAlbum.getSongs().get(i).getSongTitle(), selectedAlbum.getSongs().get(i).getSongDuration());
            System.out.println("Menu: 1 - skip track; 2 - rewind; 3 - stop");
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    if (i - 2 < -1) {
                        i = -1;
                    } else {
                        i -= 2;
                    }
                    break;
                case 3:
                    System.out.println("Player stopped. Back to main menu.");
                    return;
            }
        }
        System.out.println("No more tracks left. Returning to player menu.");
    }

    public void listAllPlaylistsAlbumsAndSongs() {
        System.out.printf("==Found %d playlists.==%n", playlists.size());
        for (int i = 0; i < playlists.size(); i++) {
            System.out.printf("%d: ^^^^^%s^^^^^%n", i + 1, playlists.get(i).getPlaylistName());
            Playlist playlist = this.playlists.get(i);
            if (playlist.getAlbums().size() < 1) {
                System.out.println("Playlist is empty.");
                continue;
            }
            System.out.printf("<<Contains %d albums>>%n", playlist.getAlbums().size());
            for (int j = 0; j < playlist.getAlbums().size(); j++) {
                System.out.printf(" **%s** (%d songs)%n", playlist.getAlbums().get(j).getNameOfAlbum(), playlist.getAlbums().get(j).getSongs().size());
                for (int k = 0; k < playlist.getAlbums().get(j).getSongs().size(); k++) {
                    System.out.printf("     %d: %s -> (%s)%n", k + 1, playlist.getAlbums().get(j).getSongs().get(k).getSongTitle(), playlist.getAlbums().get(j).getSongs().get(k).getSongDuration());
                }
            }
        }
    }

    public String generateRandomLength() {
        double rand2 = ThreadLocalRandom.current().nextDouble(3.00, 7.00);
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(rand2).replace(',', ':');
    }

    public boolean isEmpty() {
        return playlists.isEmpty();
    }
}
