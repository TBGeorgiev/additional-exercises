import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainPlayer {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Player player = new Player();
        System.out.println("Do you remember cassette players? This vintage app runs one!");
        initialChoice();
        boolean quit = false;
        while (!quit) {
            int menuOrTrack = Integer.parseInt(reader.readLine());
            switch (menuOrTrack) {
                case 1:
                    boolean mainMenu = false;
                    printInstructions();
                    while (!mainMenu) {
                        int choice = Integer.parseInt(reader.readLine());
                        switch (choice) {
                            case 1:
                                player.createPlaylist();
                                break;

                            case 2:
                                if (checkIfPlaylistIsEmpty(player)) break;
                                player.addAlbumToPlaylist();
                                break;

                            case 3:
                                if (checkIfPlaylistIsEmpty(player)) break;
                                player.addSongsToAlbum();
                                break;

                            case 4:
                                if (checkIfPlaylistIsEmpty(player)) break;
                                player.removeSong();
                                break;

                            case 5:
                                if (checkIfPlaylistIsEmpty(player)) break;
                                player.listAllPlaylistsAlbumsAndSongs();
                                break;

                            case 6:
                                if (checkIfPlaylistIsEmpty(player)) break;
                                player.switchPlayList();
                                break;

                            case 9:
                                mainMenu = true;
                                break;

                            default:
                                printInstructions();
                                break;
                        }
                    }
                    System.out.println("Back to main menu.");
                    initialChoice();
                    break;

                case 2:
                    if (player.isEmpty()) {
                        System.out.println("No playlist present. Create a playlist first.");
                        System.out.println("Returning to main menu.");
                        break;
                    }
                    player.toPlay();
                    initialChoice();
                    break;
                case 3:
                    quit = true;
                    break;
            }
        }
        System.out.println("Program closed.");
    }

    private static boolean checkIfPlaylistIsEmpty(Player player) {
        if (player.isEmpty()) {
            System.out.println("No playlist present. Create a playlist first.");
            System.out.println("Back to player menu.");
            return true;
        }
        return false;
    }


    private static void initialChoice() {
        System.out.printf("Press 1 if you want to enter the player menu.%nPress 2 if you want to play a track.%n");
    }

    private static void printInstructions() {
        System.out.printf("Press 1 to create a playlist.%nPress 2 to create an album in the current playlist.%nPress 3 to store a song inside an album.%nPress 4 to remove songs from a Playlist.%nPress 5 to list everything.%nPress 6 to switch to another playlist.%nPress 7 to see this message again.%nPress 9 to go back to the main menu.%n");
    }
}
