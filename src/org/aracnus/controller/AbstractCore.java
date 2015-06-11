package org.aracnus.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class AbstractCore {

	protected String path = null;
	private Set<Url> outgoingLinks = new HashSet<Url>();
	private LinkedTransferQueue<Url > outgoingLinksToVisit = new LinkedTransferQueue<>();
	protected int maxPageToFetch = 10;
	protected int depth = Integer.MAX_VALUE;
	
	public AbstractCore(){}
	public AbstractCore(Set<Url> outgoingLinks,
			LinkedTransferQueue<Url> outgoingLinksToVisit) {
		this.setOutgoingLinks(outgoingLinks);
		this.setOutgoingLinksToVisit(outgoingLinksToVisit);
	}
	
	public abstract Boolean shouldVisit( Url url);
	public abstract void visited( String html );
	public void tryAdd( Url url){
		if( outgoingLinks.size() < maxPageToFetch && !outgoingLinks.contains(url)){
			outgoingLinks.add(url);
			outgoingLinksToVisit.add(url);
		}
	}
	public void execute(){
		while( !outgoingLinksToVisit.isEmpty() ){
			Url  url = outgoingLinksToVisit.remove();
			url.setUrl( treatUrl(url.getUrl()));
			System.out.println("URL: "+url.getUrl());
			howtovisit(url);
		}
	}
	

	public Set<Url> getOutgoingLinks() {
		return outgoingLinks;
	}

	public void setOutgoingLinks(Set<Url> outgoingLinks) {
		this.outgoingLinks = outgoingLinks;
	}

	public LinkedTransferQueue<Url > getOutgoingLinksToVisit() {
		return outgoingLinksToVisit;
	}
	

	public void setOutgoingLinksToVisit(LinkedTransferQueue<Url > outgoingLinksToVisit) {
		this.outgoingLinksToVisit = outgoingLinksToVisit;
	}
	
	public void setMaxPageToFetch( int max){
		this.maxPageToFetch = max;
	}
	public void setPath(String url ){
		this.path = url;
	}
	public String treatUrl(String url) {
		if( path==null){
			path=url;
		}else if(!url.contains("http://") && !url.contains("https://")){
			String partialUrl = url.substring(0, url.lastIndexOf("/")+1 );
			if( path.contains(partialUrl)){
				path=path.replace( partialUrl , "");
			}
			int ultimabarra= path.lastIndexOf("/");
			String nurl = path.substring(ultimabarra, path.length() );
			if( nurl.contains(".")){
				nurl = path.substring(0,ultimabarra )+"/";
			}
			if( url.contains("../") || path.contains(".")){
				url = nurl+url;
			}else{
				url=path+url;
			}
		}
		path = url;
		return url;
	}

	public String getPath() {
		// TODO Auto-generated method stub
		return path;
	}
	public abstract void howtovisit(Url url);
	
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	

}
