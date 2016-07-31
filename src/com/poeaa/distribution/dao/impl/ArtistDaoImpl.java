package com.poeaa.distribution.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.poeaa.distribution.dao.ArtistDao;
import com.poeaa.distribution.domain.Album;
import com.poeaa.distribution.domain.Artist;
import com.poeaa.distribution.dao.generators.*;

public class ArtistDaoImpl implements ArtistDao {
	private static ArrayList<Artist> artistsList = new ArrayList<Artist>();
	
	public ArtistDaoImpl(){
		super();
		artistsList = Generator.InitializeArtists(); 
	}
	
	
	@Override
	public List<Artist> findAll() {
		return this.artistsList;
	}

	@Override
	public List<Artist> findById(int id) {
		ArrayList<Artist> returnList = new ArrayList<Artist>();
		
		for(Artist artist : artistsList){
			if(artist.getId() == id)
				returnList.add(artist);
		}
		return returnList;
	}

	@Override
	public boolean addArtist(Artist artist) {
		artistsList.add(artist);
		return true;
	}

	@Override
	public boolean updateArtist(Artist artist) {
		for(Artist i : artistsList){
			if(i.getId() == artist.getId())
				artistsList.remove(i);
				artistsList.add(artist);
		}
		return true;
	}

	@Override
	public boolean deleteArtist(Artist artist) {
		for(Artist i : artistsList){
			if(i.getId() == artist.getId())
				artistsList.remove(i);
		}
		return true;
	}
	

	public ArrayList<Artist> findArtistsByName(String artistName) {
		ArrayList<Artist> returnList = new ArrayList<Artist>();
		for (Artist i: artistsList){
			if (i.getName() == artistName){
				returnList.add(i);
			}
		}
		return returnList;
	}

}
