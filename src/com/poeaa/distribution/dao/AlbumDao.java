package com.poeaa.distribution.dao;

import java.util.List;

import com.poeaa.distribution.domain.Album;
import com.poeaa.distribution.domain.Artist;

public interface AlbumDao {
	List<Album> findAll();
	List<Album>findById(int id);
	boolean addAlbum(Album album);
	boolean updateAlbum(Album album);
	boolean deleteAlbum(Album album);
}
