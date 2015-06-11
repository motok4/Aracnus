package org.aracnus.controller;

public class Url {

	private String url;
	private int level;
	private Url parent;
	
	public Url(){}
	public Url( String url, int level, Url parent){
		this.url = url;
		this.level = level;
		this.parent = parent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Url getParent() {
		return parent;
	}
	public void setParent(Url parent) {
		this.parent = parent;
	}
	

}
