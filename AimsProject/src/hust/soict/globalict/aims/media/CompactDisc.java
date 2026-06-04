package hust.soict.globalict.aims.media;

import hust.soict.globalict.aims.exception.PlayerException;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private List<Track> tracks = new ArrayList<>();

    public CompactDisc(int id, String title, String category, float cost, int length, String director, String artist) {
        super(id, title, category, cost, length, director);
        this.artist = artist;
    }

    public String getArtist() { return artist; }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
        } else {
            System.out.println("Track already in CD.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.remove(track)) {
            System.out.println("Track removed.");
        } else {
            System.out.println("Track not found in CD.");
        }
    }

    // The CD length is the sum of all track lengths, so it is overridden.
    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: CD length is non-positive: \"" + this.getTitle() + "\"");
        }
        System.out.println("Playing CD: " + this.getTitle() + " by " + this.artist);
        System.out.println("CD Length: " + this.getLength());
        for (Track track : tracks) {
            try {
                track.play();
            } catch (PlayerException e) {
                // Re-wrap the failing track's error so the caller knows the CD failed.
                throw new PlayerException("ERROR when playing a track in CD \"" + this.getTitle()
                        + "\": " + e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "CD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + " - " + getArtist() + " - " + getCost() + "$";
    }
}
