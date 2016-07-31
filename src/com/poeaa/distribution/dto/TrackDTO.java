package com.poeaa.distribution.dto;

public class TrackDTO extends DataTransferObject{
	private String title;
	private String[] performers;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String[] getPerformers() {
		return performers;
	}
	
	public void setPerformers(String[] performers) {
		this.performers = performers;
	}
	
}
