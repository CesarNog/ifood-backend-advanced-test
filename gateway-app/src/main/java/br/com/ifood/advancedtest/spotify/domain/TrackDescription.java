package br.com.ifood.advancedtest.spotify.domain;

public class TrackDescription {
	private String artist;
	private String album;
	private String trackName;

	public TrackDescription(String artist, String album, String trackName) {
		this.artist = artist;
		this.album = album;
		this.trackName = trackName;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
}
