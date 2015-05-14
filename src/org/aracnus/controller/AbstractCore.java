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
	private Set<String> outgoingLinks = new HashSet<String>();
	private LinkedTransferQueue<String > outgoingLinksToVisit = new LinkedTransferQueue<>();
	protected int maxPageToFetch = 10;
	
	public AbstractCore(){}
	public AbstractCore(Set<String> outgoingLinks,
			LinkedTransferQueue<String> outgoingLinksToVisit) {
		this.setOutgoingLinks(outgoingLinks);
		this.setOutgoingLinksToVisit(outgoingLinksToVisit);
	}
	
	public abstract Boolean shouldVisit( String url);
	public abstract void visited( String html );
	public void tryAdd( String url){
		if( outgoingLinks.size() < maxPageToFetch && !outgoingLinks.contains(url)){
			outgoingLinks.add(url);
			outgoingLinksToVisit.add(url);
		}
	}
	public void execute(){
		
		while( !outgoingLinksToVisit.isEmpty() ){
			String  url = outgoingLinksToVisit.remove();
			url = treatUrl(url);
			System.out.println("URL: "+url);
			howtovisit(url);
		}
	}
	public void howtovisit( String url ){
		WebDriver driver = new FirefoxDriver();
	    driver.get(url);
	    
//	    List<WebElement> list = driver.findElements( By.cssSelector("Button.glbComentarios-btn.glbComentarios-link-cor.glbComentarios-bt-lista-respostas"));
//	    for( int i=0; i<list.size(); i++){
//	    	String id = list.get(i).getText().trim().split(" ")[0];
//	    	id = !id.isEmpty() && id != null ? id: "0"; 
//	    	int n = Integer.parseInt(id);
//	    	if( n > 0){	  
//	    		try{
//	    			list.get(i).click();
//	    		}catch(Exception e){
//	    		}
//    			List<WebElement> mais= driver.findElements( By.cssSelector("Button.glbComentarios-lista-bt-paginar"));
//    			for( int j=0; j<list.size(); j++){
//    				try{
//    					mais.get(j).click();
//    				}catch(Exception e){
//    					
//    				}
//    			}
//
//	    	}
//        }
	    String html = driver.getPageSource();
	    driver.quit();
	    Document doc = Jsoup.parse(html);
	    org.jsoup.select.Elements alist = doc.select("a");
	    for( org.jsoup.nodes.Element a:alist){
	    	String aurl = a.attr("href");
	    	if( !aurl.isEmpty() && aurl != null && aurl.length() > 3 ){
	    		if( shouldVisit(aurl) ){
	    			tryAdd(aurl);
	    		}
	    	}
	    }
	    visited(html);
	}

	public Set<String> getOutgoingLinks() {
		return outgoingLinks;
	}

	public void setOutgoingLinks(Set<String> outgoingLinks) {
		this.outgoingLinks = outgoingLinks;
	}

	public LinkedTransferQueue<String > getOutgoingLinksToVisit() {
		return outgoingLinksToVisit;
	}

	public void setOutgoingLinksToVisit(LinkedTransferQueue<String > outgoingLinksToVisit) {
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
	

}
