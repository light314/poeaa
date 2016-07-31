package com.poeaa.distribution.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.poeaa.distribution.dao.AlbumDao;
import com.poeaa.distribution.dao.generators.Generator;
import com.poeaa.distribution.domain.Album;
import com.poeaa.distribution.domain.Artist;

public class AlbumDaoImpl implements AlbumDao {
	private static ArrayList<Album> albumsList = new ArrayList<Album>();
	
	
	public AlbumDaoImpl() {
		super();
		albumsList = Generator.initializeAlbums();
	}

	@Override
	public List<Album> findAll() {
		return this.albumsList;
	}

	@Override
	public List<Album> findById(int id) {
		ArrayList<Album> returnList = new ArrayList<Album>();
		for (Album i: albumsList){
			if (i.getId() == id){
				returnList.add(i);
			}
		}
		return returnList;
	}

	@Override
	public boolean addAlbum(Album album) {
		this.albumsList.add(album);
		return true;
	}

	@Override
	public boolean updateAlbum(Album album) {
		for (Album i: albumsList){
			if (i.getId() == album.getId()){
				albumsList.remove(i);
				albumsList.add(album);
			}
		}
		return true;
	}

	@Override
	public boolean deleteAlbum(Album album) {
		for (Album i: albumsList){
			if (i.getId() == album.getId()){
				albumsList.remove(i);
			}
		}
		return true;
	}

/*	public static Artist findArtistByName(String artistName) {
		ArrayList<Artist> returnList = new ArrayList<Artist>();
		for (Album i: albumsList){
			if (i.getLeadArtist().getName() == artistName){
				returnList.add(i);
			}
		}
		return returnList;
	}*/

}
