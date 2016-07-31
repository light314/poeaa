package com.poeaa.distribution.dao.generators;

import com.poeaa.distribution.domain.Album;
import com.poeaa.distribution.domain.Artist;
import com.poeaa.distribution.domain.Track;

import java.util.ArrayList;


public class Generator {
	
	public static ArrayList<Artist> InitializeArtists(){
		ArrayList<Artist> returnList = new ArrayList<Artist>();
		
		Artist artist1 = new Artist(1,"Shankar Mahadevan");	
		Artist artist2 = new Artist(2,"Ehsaan Noorani" );
		Artist artist3 = new Artist(3, "Loy Mendonsa");
		
		returnList.add(artist1);
		returnList.add(artist2);
		returnList.add(artist3);
				
		return returnList;
		
	}

	public static ArrayList<Track> initializeTracks(){
		ArrayList<Track> returnList = new ArrayList<Track>();
		
		Track track1 = new Track();
		track1.setId(1);
		track1.setTitle("Dhoka");
		ArrayList<Artist> performersList = InitializeArtists();
		Artist artist4 = new Artist(4,"Anousha Mani");
		performersList.add(artist4);
		track1.setPerformersList(performersList);
		
		Track track2 = new Track();
		track2.setId(2);
		track2.setTitle("Johnny in the House");
		Artist artist5 = new Artist(5,"Dj. Shane");
		performersList = new ArrayList<Artist>();
		performersList.add(artist5);
		track2.setPerformersList(performersList);
		
		
		returnList.add(track1);
		returnList.add(track2);
		
		return returnList;
	}

	public static ArrayList<Album> initializeAlbums() {
		ArrayList<Album> returnList = new ArrayList<Album>();
		Artist leadArtist = new Artist(1,"Shankar Mahadevan");
		Album album1 = new Album(1, "Johnny_Gaddaar", leadArtist);
		album1.setTrackList(Generator.initializeTracks());
		returnList.add(album1);
		
		return returnList;
	}
}
