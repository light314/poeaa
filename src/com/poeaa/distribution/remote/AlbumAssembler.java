package com.poeaa.distribution.remote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.poeaa.distribution.dao.AlbumDao;
import com.poeaa.distribution.dao.ArtistDao;
import com.poeaa.distribution.dao.TrackDao;
import com.poeaa.distribution.dao.impl.AlbumDaoImpl;
import com.poeaa.distribution.dao.impl.ArtistDaoImpl;
import com.poeaa.distribution.dao.impl.TrackDaoImpl;
import com.poeaa.distribution.domain.Album;
import com.poeaa.distribution.domain.Artist;
import com.poeaa.distribution.domain.Track;
import com.poeaa.distribution.dto.AlbumDTO;
import com.poeaa.distribution.dto.TrackDTO;

public class AlbumAssembler {
	private AlbumDao albumDao = new AlbumDaoImpl();
	private ArtistDao artistDao = new ArtistDaoImpl();
	private TrackDao trackDao = new TrackDaoImpl();
	/* Method createAlbumDTO converts the domain Object Album into a DTO AlbumDTO for remote */
	/* Assumes that before creating the album, the user has taken care to create the Tracks and Artists that form the Album. */
	/* TODO: Write error handling code to handle cases where the tracks and artists are not already created. Current Decision: Throw an Exception*/
	public AlbumDTO createAlbumDTO(Album subject){
		AlbumDTO result = new AlbumDTO();
		result.setAlbumName(subject.getTitle());
		result.setLeadArtistName(subject.getLeadArtist().getName());
		writeTracks(result, subject);
		return result;
	}
	
	/* private method writeTracks() writes the Tracks data into AlbumDTO from the Album domain object*/
	private void writeTracks(AlbumDTO dto, Album subject) {
		ArrayList<TrackDTO> trackDTOList = new ArrayList<TrackDTO>();
		Iterator<Track> it = subject.getTrackList().iterator();
		while (it.hasNext()) {
			TrackDTO newTrackDTO = new TrackDTO();
			Track newTrack = it.next();
			newTrackDTO.setTitle(newTrack.getTitle());
			writePerformers(newTrackDTO,newTrack);
			trackDTOList.add(newTrackDTO);
		}
		TrackDTO[] tracks = trackDTOList.toArray(new TrackDTO[0]);
		dto.setTracks(tracks);
	}
	
	/* private method writePerformers() writes the Performers data into TrackDTO from the Track domain object*/
	private void writePerformers(TrackDTO dto,Track subject){
		dto.setTitle(subject.getTitle());
		ArrayList<String> performersList = new ArrayList<String>();
		Iterator<Artist> it = subject.getPerformersList().iterator();
		while(it.hasNext()){
			performersList.add(it.next().getName());
		}
		dto.setPerformers(performersList.toArray(new String[0]));
	}

	
	/* Creates a new Album record in the Store by reading the contents of the AlbumDTO*/
	public void createAlbum(String id, AlbumDTO source){
		// creating a Album object
		Album newAlbum = new Album();
		newAlbum.setId(Integer.parseInt(id));
		newAlbum.setLeadArtist(artistDao.findArtistsByName(source.getLeadArtistName()).get(0));		// Setting the lead artist - by finding the first artist with a matching name
		newAlbum.setTitle(source.getAlbumName()); // Setting Title
		createTracks(newAlbum, source.getTracks());
		albumDao.addAlbum(newAlbum);
	}
	
	private void createTracks(Album newAlbum, TrackDTO[] tracks) {
		ArrayList<TrackDTO> trackDTOList = (ArrayList<TrackDTO>) Arrays.asList(tracks); // Convert the TrackDTO objects into List of Track objects
		ArrayList<Track> trackList = new ArrayList<Track>();
		
		Iterator it = trackDTOList.iterator();
		while(it.hasNext()){
			TrackDTO dto = (TrackDTO) it.next();
			//Finding the Track object for every TrackDTO by title Name
			Track newTrack = trackDao.findByTitleName(dto.getTitle()).get(0);
			if (newTrack == null) 
				throw new RuntimeException("No Track Named"+dto.getTitle());
			newAlbum.AddTrack(newTrack);
			createPerformers(newTrack, dto.getPerformers());	
		}	
	}

			
	private void createPerformers(Track newTrack, String[] performers) {
		for (int i=0; i<performers.length; i++){
			Artist artist = artistDao.findArtistsByName(performers[i]).get(0);
			if (artist == null) 
				throw new RuntimeException("No Artist Named"+performers[i]);
			newTrack.addPerformer(artist);
		}
	}

	public void updateAlbum(String id, AlbumDTO source){
		Album current = albumDao.findById(Integer.parseInt(id)).get(0);
		if (current == null) 
			throw new RuntimeException("Album with id:"+ id + " does not exist.");
		
		if(current.getLeadArtist().getName() != source.getLeadArtistName()){
			ArrayList<Artist> artistsWithGivenName = artistDao.findArtistsByName(source.getLeadArtistName());
			if (artistsWithGivenName == null)
				throw new RuntimeException("No artists with given Name:"+ source.leadArtistName + " exists.");
			current.setLeadArtist(artistsWithGivenName.get(0));
		}
		
		if(current.getTitle()!=source.getAlbumName()) 
			current.setTitle(source.getAlbumName());
			
		updateTracks(source.getTracks(), current);
	}

	private void updateTracks(TrackDTO[] tracks, Album current) {
		for (int i=0; i<tracks.length; i++){
			TrackDTO trackDTO = tracks[i];
			ArrayList<Track> currTracksWithGivenTitle = trackDao.findByTitleName(trackDTO.getTitle());
			if(currTracksWithGivenTitle == null)
				throw new RuntimeException("No Track with given name:"+trackDTO.getTitle()+" exists.");
			Track currTrack = currTracksWithGivenTitle.get(0);
			currTrack.clearPerformers();
			createPerformers( currTrack, tracks[i].getPerformers());
		}
		
	}

}
