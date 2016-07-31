package com.poeaa.distribution.dao;

import java.util.ArrayList;
import java.util.List;

import com.poeaa.distribution.domain.Track;
import com.poeaa.distribution.dto.TrackDTO;


public interface TrackDao {
	
	List<Track> findAll();
	List<Track>findById(int id);
	boolean addTrack(Track track);
	boolean updateTrack(Track track);
	boolean deleteTrack(Track track);
	ArrayList<Track> findByTitleName(String title);


}
