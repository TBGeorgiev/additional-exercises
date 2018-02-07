import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainPlayer {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Do you remember cassette players? This vintage app runs one!");
        printInstructions();
        Player player = new Player();
        boolean quit = false;
        while (!quit) {
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1:
                    player.createPlaylist();
                    break;

                case 2:
                    player.addAlbumToPlaylist();
                    break;

                case 3:
                    player.addSongsToAlbum();
                    break;

                case 4:
                    player.removeSong();
                    break;

                case 5:
                    player.listAllPlaylistsAlbumsAndSongs();
                    break;

                case 6:
                    player.switchPlayList();
                    break;

                case 9:
                    quit = true;
                    break;

                default:
                    printInstructions();
                    break;
            }
        }
        System.out.println("Program closed.");
    }

    private static void printInstructions() {
        System.out.printf("Press 1 to create a playlist.%nPress 2 to create an album in the current playlist.%nPress 3 to store a song inside an album.%nPress 4 to remove songs from a Playlist.%nPress 5 to list everything.%nPress 6 to switch to another playlist.%nPress 7 to see this message again or press 9 to quit the application.%n");
    }
}
