package com.poeaa.distribution.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.poeaa.distribution.dao.TrackDao;
import com.poeaa.distribution.dao.generators.Generator;
import com.poeaa.distribution.domain.Artist;
import com.poeaa.distribution.domain.Track;

public class TrackDaoImpl implements TrackDao{
	private static ArrayList<Track> tracksList = new ArrayList<Track>();
	
	public TrackDaoImpl() {
		super();
		tracksList = Generator.initializeTracks();
	}
	
	@Override
	public List<Track> findAll() {
		return this.tracksList;
	}

	

	@Override
	public List<Track> findById(int id) {
		ArrayList<Track> returnList = new ArrayList<Track>();
		for (Track i: this.tracksList){
			if (i.getId() == id){
				returnList.add(i);
			}
		}
		return returnList;
	}

	@Override
	public boolean addTrack(Track track) {
		tracksList.add(track);
		return true;
	}

	@Override
	public boolean updateTrack(Track track) {
		for(Track i : this.tracksList){
			if(i.getId() == track.getId())
				tracksList.remove(i);
				tracksList.add(track);
		}
		return true;
	}

	@Override
	public boolean deleteTrack(Track track) {
		for(Track i : tracksList){
			if(i.getId() == track.getId())
				tracksList.remove(i);
		}
		return true;
	}

	@Override
	public ArrayList<Track> findByTitleName(String titleName) {
		ArrayList<Track> returnList = new ArrayList<Track>();
		for (Track i: this.tracksList){
			if (i.getTitle() == titleName){
				returnList.add(i);
			}
		}
		return returnList;
	}

}
