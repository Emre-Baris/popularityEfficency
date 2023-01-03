import java.util.Arrays;
import java.util.List;

public class Album {

    public List<Track> albumSongList;

    public int albumDuration;

    public int albumPopularity;

    public Track[] albumOrder;

    public Album(){

    }


    public int getAlbumDuration() {
        return albumDuration;
    }

    public void setAlbumDuration(int albumDuration) {
        this.albumDuration = albumDuration;
    }

    public int getAlbumPopularity() {
        return albumPopularity;
    }

    public void setAlbumPopularity(int albumPopularity) {
        this.albumPopularity = albumPopularity;
    }

    public Track[] getAlbumOrder() {
        return albumOrder;
    }

    public void setAlbumOrder(Track[] albumOrder) {
        this.albumOrder = albumOrder;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumSongList=" + albumSongList +
                ", albumDuration=" + albumDuration +
                ", albumPopularity=" + albumPopularity +
                ", albumOrder=" + Arrays.toString(albumOrder) +
                '}';
    }
}
