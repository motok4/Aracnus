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
			url.setUrl( treatUrl(url));
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

	public String treatUrl(Url url) {
		String urlfinal = "";
		String path = "";
		if( url.getParent() != null ){
			path=url.getParent().getUrl();
		}else{
			path=url.getUrl();
		}
		//verifica se não tem um padrao http ou http que pode indicar url relativa
		if(!url.getUrl().contains("http://") && !url.getUrl().contains("https://")){
			//Verifica se na url existe o padrao parciais, por exemplo, 
			// evitar url: http://localhost/crawllertest/home/localhost/crawllertest/home/page1.html
			String partialUrl = url.getUrl().substring(0, url.getUrl().lastIndexOf("/")+1 );
			if( path.contains(partialUrl)){
				path=path.replace( partialUrl , "");
			}
			//encontrar a ultima barra da url para remover por exemplo as paginas.html
			//http://localhost/crawllertest/home/page1.html
			//ira ficar assim
			//http://localhost/crawllertest/home/
			int ultimabarra= path.lastIndexOf("/");
			String nurl = path.substring(ultimabarra, path.length() );
			if( nurl.contains(".")){
				nurl = path.substring(0,ultimabarra )+"/";
			}else{
				nurl = path;
			}
			urlfinal = nurl+url.getUrl();
		}else{
			urlfinal = url.getUrl();
		}
		urlfinal = urlfinal.replace("//", "/");
		urlfinal = urlfinal.replace(":/", "://");
		return urlfinal;
	}

	public abstract void howtovisit(Url url);
	
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	

}
