public class Song {
    private String songTitle;
    private String songDuration;

    public Song(String songTitle, String songDuration) {
        this.songTitle = songTitle;
        this.songDuration = songDuration;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongDuration() {
        return songDuration;
    }
}

