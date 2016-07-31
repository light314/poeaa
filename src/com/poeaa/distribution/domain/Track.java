package com.poeaa.distribution.domain;

import java.util.ArrayList;

public class Track {
	private int id;
	private String Title;
	private ArrayList<Artist> performersList = new ArrayList<Artist>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public ArrayList<Artist> getPerformersList() {
		return performersList;
	}
	
	
	public void setPerformersList(ArrayList<Artist> performersList) {
		this.performersList = performersList;
	}
	public void addPerformer(Artist arg){
		performersList.add(arg);
	}
	
	public void removePerformer(Artist arg){
		performersList.remove(arg);
	}
	public void clearPerformers() {
		performersList.clear();
		
	}
}
