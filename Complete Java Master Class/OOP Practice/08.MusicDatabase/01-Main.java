import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;


public class Main {
	private static boolean artistFound;

	public static void main(String[] args) throws SQLException, IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Datasource datasource = new Datasource();
		if (!datasource.open()) {
			System.out.println("Can't open datasource");
			return;
		}
		boolean quit = false;
		JOptionPane.showMessageDialog(null, "Welcome to the mighty Music Database!");
		
		
		while (!quit) {
			
			String bandName = JOptionPane.showInputDialog("Enter the name of the band:");
			listAlbumsOfGroup(bandName, datasource);
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
		
		datasource.close();
	}
	
	
	private static boolean listSongsOfAlbum(String nameOfAlbum, Datasource datasource)
			throws IOException, SQLException {
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
			throws IOException, SQLException {
		
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
