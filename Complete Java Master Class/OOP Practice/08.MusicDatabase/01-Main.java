import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;


public class Main {
	private static boolean artistFound;

	public static void main(String[] args) throws SQLException, IOException {
		
		Datasource datasource = new Datasource();
		if (!datasource.open()) {
			System.out.println("Can't open datasource");
			return;
		}
		boolean quit = false;
		JOptionPane.showMessageDialog(null, "Welcome to the mighty Music Database!");
		
		
		while (!quit) {
			
			String[] choices = {"List all albums from a band", "List all bands", "Quit"};
			
			String choice = (String) JOptionPane.showInputDialog(null, "Please select an option from the main menu below", "Main menu", JOptionPane.DEFAULT_OPTION, null, choices, choices[0]);
			
			if (choice == null) {
				JOptionPane.showMessageDialog(null, "ERROR!", "Warning", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
			if (choice.equals("List all bands")) {
				listArtists(datasource);
			} else if (choice.equals("List all albums from a band")) {
				String bandName = JOptionPane.showInputDialog("Enter the name of the band:");
				if (bandName != null) {
					listAlbumsOfGroup(bandName, datasource);				
				}
				if (artistFound) {
					int toListOrNot = JOptionPane.showConfirmDialog(null, "Do you want to list all the tracks of an album?", 
							null, JOptionPane.YES_NO_OPTION);
					if (toListOrNot == JOptionPane.YES_OPTION) {
						String nameOfAlbum = JOptionPane.showInputDialog("Enter the name of the Album: ");
						boolean albumFound = listSongsOfAlbum(nameOfAlbum, datasource);
						while (!albumFound) {
							JOptionPane.showMessageDialog(null, "Incorrect album name, please try again.");
							nameOfAlbum = JOptionPane.showInputDialog("Enter the name of the Album: ");
							albumFound = listSongsOfAlbum(nameOfAlbum, datasource);
						}
					}
				}
				
				int toContinue = JOptionPane.showConfirmDialog(null, "Do you want to look for another bands and albums?",
						null, JOptionPane.YES_NO_OPTION);
				if (toContinue == JOptionPane.NO_OPTION) {
					quit = true;
				}
			}
			
			else {
				quit = true;
			}
			
		}
		
		datasource.close();
	}


	private static void listArtists(Datasource datasource) {
		List<Artist> artists = datasource.queryArtists();
		for (Artist artist : artists) {
			System.out.println(artist.getName());
		}
	}
	
	
	private static boolean listSongsOfAlbum(String nameOfAlbum, Datasource datasource)
			throws SQLException {
		List<Song> songs = datasource.querySongsFromAlbum(nameOfAlbum);
		for (Song song : songs) {
			System.out.println("\t" + song.getTrack() + ": " + song.getName());
		}
		if (songs.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	private static void listAlbumsOfGroup(String name, Datasource datasource)
			throws SQLException {
		
		List<Album> albums = datasource.queryAlbumsFromArtist(name);
		if (albums.isEmpty()) {
			System.out.println("No records found of " + name);
			artistFound = false;
		} else {
			artistFound = true;
			for (Album album : albums) {
				System.out.println("\tName: " + album.getName());
			}
		}
		
	}

}
