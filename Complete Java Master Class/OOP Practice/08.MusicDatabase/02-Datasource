import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
	public static final String DB_NAME = "music.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Georgiev\\Downloads\\music\\" + DB_NAME;
	
	public static final String TABLE_ALBUMS = "albums";
	public static final String COLUMN_ALBUM_ID = "_id";
	public static final String COLUMN_ALBUM_NAME = "name";
	public static final String COLUMN_ALBUM_ARTIST = "artist";
	public static final int INDEX_ALBUM_ID = 1;
	public static final int INDEX_ALBUM_NAME = 2;
	public static final int INDEX_ALBUM_ARTIST = 3;
	
	public static final String TABLE_ARTISTS = "artists";
	public static final String COLUMN_ARTIST_ID = "_id";
	public static final String COLUMN_ARTIST_NAME = "name";
	public static final int INDEX_ARTIST_ID = 1;
	public static final int INDEX_ARTIST_NAME = 2;
	
	public static final String TABLE_SONGS = "songs";
	public static final String COLUMN_SONG_ID = "_id";
	public static final String COLUMN_SONG_TRACK = "track";
	public static final String COLUMN_SONG_TITLE = "title";
	public static final String COLUMN_SONG_ALBUM = "album";
	public static final int INDEX_SONG_ID = 1;
	public static final int INDEX_SONG_TRACK = 2;
	public static final int INDEX_SONG_TITLE = 3;
	public static final int INDEX_SONG_ALBUM = 4;
	
	private Connection conn;
	
	public boolean open() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
			return true;
		} catch (SQLException e) {
			System.out.println("Couldn't connect to database " + e.getMessage());
			return false;
		}
	}
	
	public void close() {
		try {
			if (conn!= null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Couldn't close connection: " + e.getMessage());
		}
	}
	
	public List<Artist> queryArtists() {
		
		Statement statement = null;
		ResultSet results = null;
		
		try {
			statement = conn.createStatement();
			results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS);
			
			List<Artist> artists = new ArrayList<Artist>();
			while (results.next()) {
				Artist artist = new Artist();
				artist.setId(results.getInt(INDEX_ARTIST_ID));
				artist.setName(results.getString(INDEX_ARTIST_NAME));
				artists.add(artist);
			}
			
			return artists;
			
		} catch (SQLException e) {
			System.out.println("Query failed");
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				if (results != null) {
					results.close();
				}
			} catch (SQLException e) {
				System.out.println("Error closing ResultSet");
			}
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("Error closing Statement");
			}
		}
		
	}
	
	public List<Album> queryAlbumsFromArtist(String name) throws SQLException {
		
		try(Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT albums._id, albums.name, albums.artist "
						+ "FROM albums INNER JOIN artists ON artists._id = albums.artist "
						+ "WHERE artists.name LIKE '" + name + "'" + " ORDER BY albums.name COLLATE NOCASE")) {
			
			List<Album> listOfAlbums = new ArrayList<Album>();
			while (resultSet.next()) {
				Album album = new Album();
				album.setId(resultSet.getInt(INDEX_ALBUM_ID));
				album.setName(resultSet.getString(INDEX_ALBUM_NAME));
				album.setArtistID(resultSet.getInt(INDEX_ALBUM_ARTIST));
				listOfAlbums.add(album);
			}
			ResultSet newResultCount = statement.executeQuery(
					"SELECT COUNT(albums.name) "
					+ "FROM albums INNER JOIN artists "
					+ "ON artists._id = albums.artist WHERE artists.name LIKE '" + name + "'");
			if (newResultCount.next()) {
				if (newResultCount.getInt(1) > 0) {
					System.out.println("Total number of albums: " + newResultCount.getInt(1));					
				}
			}
			
			return listOfAlbums;
		}
	}
	
	public List<Song> querySongsFromAlbum(String name) throws SQLException {
		
		try (Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT songs._id, songs.track, songs.title, songs.album FROM songs "
						+ "INNER JOIN albums ON songs.album = albums._id "
						+ "WHERE albums.name LIKE '" + name + "' ORDER BY songs.track;")) {
			List<Song> songs = new ArrayList<Song>();
			
			while (resultSet.next()) {
				Song song = new Song();
				song.setId(resultSet.getInt(INDEX_SONG_ID));
				song.setTrack(resultSet.getInt(INDEX_SONG_TRACK));
				song.setName(resultSet.getString(INDEX_SONG_TITLE));
				song.setAlbumId(resultSet.getInt(INDEX_SONG_ALBUM));
				songs.add(song);
				
			}
			ResultSet countOfSongs = statement.executeQuery(
					"SELECT COUNT(songs.title) FROM songs "
					+ "INNER JOIN albums ON songs.album = albums._id "
					+ "WHERE albums.name LIKE '" + name + "'");
			if (countOfSongs.next()) {
				if (countOfSongs.getInt(1) > 0) {
					System.out.println("Number of songs: " + countOfSongs.getInt(1));					
				}
			}
			
			return songs;
		}
		
	}
}
