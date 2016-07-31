package com.poeaa.distribution.dto;

public class AlbumDTO extends DataTransferObject {
	public String albumName;
	public String leadArtistName;
	public TrackDTO[] tracks;
	
	public String getAlbumName() {
		return albumName;
	}
	
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public String getLeadArtistName() {
		return leadArtistName;
	}
	
	public void setLeadArtistName(String leadArtistName) {
		this.leadArtistName = leadArtistName;
	}
	
	public TrackDTO[] getTracks() {
		return tracks;
	}
	
	public void setTracks(TrackDTO[] tracks) {
		this.tracks = tracks;
	}
}
