/**
 * 
 */
package com.poeaa.distribution.dao;

import java.util.ArrayList;
import java.util.List;

import com.poeaa.distribution.domain.Artist;

/**
 * @author vinodh
 *
 */
public interface ArtistDao {
	List<Artist> findAll();
	List<Artist>findById(int id);
	boolean addArtist(Artist artist);
	boolean updateArtist(Artist artist);
	boolean deleteArtist(Artist artist);
	ArrayList<Artist> findArtistsByName(String leadArtistName);
}
