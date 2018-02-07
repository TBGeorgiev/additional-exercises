import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainPlayer {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Do you remember cassette players? This vintage app runs one!");
        System.out.printf("Press 1 to create a playlist.%nPress 2 to create an album in the current playlist.%nPress 3 to store a song inside an album.%nPress 4 to remove songs from a Playlist.%nPress 5 to list everything.%nPress 6 to switch to another playlist.%nPress 7 to see this message again or press 9 to quit the application.%n");
        String currentPlaylist = "";
        List<String> playlists = new ArrayList<>();
        Player player = new Player();
        boolean quit = false;
        while (!quit) {
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1:
                    System.out.print("Please enter a name for the playlist you want to create: ");
                    String playlistName = reader.readLine();
                    playlists.add(playlistName);
                    currentPlaylist = playlistName;
                    player.createPlaylist(playlistName);
                    break;
                case 2:
                    System.out.print("Enter a name of the Album you want to create: ");
                    String nameOfAlbum = reader.readLine();
                    player.addAlbumToPlaylist(currentPlaylist, nameOfAlbum);
                    System.out.printf("Album '%s' created.%n", nameOfAlbum);
                    break;

                case 3:
                    System.out.print("In which album do you want to store the song? Album name: ");
                    nameOfAlbum = reader.readLine();
                    player.findPlaylist(currentPlaylist).findAlbum(nameOfAlbum);
                    if (player.findPlaylist(currentPlaylist).isNull()) {
                        System.out.println("No album was created. Returning to main menu.");
                        break;
                    }
                    System.out.print("Enter the name of Song: ");
                    String nameOfSong = reader.readLine();
                    System.out.print("Enter the duration of the song: ");
                    String durationOfSong = reader.readLine();
                    player.addSongsToAlbum(currentPlaylist, nameOfAlbum, nameOfSong, durationOfSong);
                    System.out.printf("Song was stored successfully in Album - %s%n", nameOfAlbum);
                    break;

                case 4:
                    player.removeSong();
                    break;

                case 5:
                    player.listAllPlaylistsAlbumsAndSongs();
                    break;

                case 6:
                    System.out.println("Current playlist is: " + currentPlaylist);
                    System.out.print("Enter the playlist you want to switch to: ");
                    String nameOfPlaylistToSwitch = reader.readLine();
                    if (playlists.contains(nameOfPlaylistToSwitch)) {
                        currentPlaylist = nameOfPlaylistToSwitch;
                        System.out.println("Successfully switched to " + currentPlaylist);
                    }
                    else {
                        System.out.println("Playlist not found. Do you want to create it?");
                        System.out.print("Y / N: ");
                        String answer = reader.readLine().toLowerCase();
                        if (answer.equals("y")) {
                            playlists.add(nameOfPlaylistToSwitch);
                            player.createPlaylist(nameOfPlaylistToSwitch);
                            currentPlaylist = nameOfPlaylistToSwitch;
                            System.out.printf("Playlist '%s' created. This is now the current playlist.%n", nameOfPlaylistToSwitch);
                        }
                        else {
                            System.out.println("Returning to main menu.");
                        }

                    }
                    break;

                case 9:
                    quit = true;
                    break;

                default:
                    System.out.printf("Press 1 to create a playlist.%nPress 2 to create an album in the current playlist.%nPress 3 to store a song inside an album.%nPress 4 to remove songs from a Playlist.%nPress 5 to list everything.%nPress 6 to switch to another playlist.%nPress 7 to see this message again or press 9 to quit the application.%n");
                    break;
            }
        }
        System.out.println("Program closed.");
    }
}
