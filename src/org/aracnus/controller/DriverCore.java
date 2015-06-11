package org.aracnus.controller;

import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverCore extends AbstractCore{
	

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
			+ "|png|mp3|mp3|zip|gz))$");

	 

	public DriverCore(){}

	@Override
	public Boolean shouldVisit(Url url) {
		String href = url.getUrl().toLowerCase();        
        return !FILTERS.matcher(href).matches();// && href.startsWith("http://g1.globo.com/politica/noticia/");
	}

	@Override
	public void visited(String html) {
		
	}
	public void howtovisit( Url url ){
		WebDriver driver = new FirefoxDriver();
	    driver.get(url.getUrl());
	    
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
	    //trata a profundidade. Se o level da url corrente for menor ou igual ao nível
		//coletamos os outgoingLinks, caso contrário, já estamos na profundidade máxima
		// e não fazemos nada
	    if( url.getLevel() <= this.getDepth() ){
		    Document doc = Jsoup.parse(html);
		    org.jsoup.select.Elements alist = doc.select("a");
		    for( org.jsoup.nodes.Element a:alist){
		    	String _aurl = a.attr("href");
		    	//cria um objeto url a partir das url coletadas na pagina
		    	//e linka esse "novo url" ao pai "url" e acrenta mais um ao level
		    	//que representa a profundidade
		    	Url aurl = new Url( _aurl, url.getLevel()+1, url );
		    	if( !_aurl.isEmpty() && _aurl != null && _aurl.length() > 3 ){
		    		if( shouldVisit(aurl) ){
		    			tryAdd(aurl);
		    		}
		    	}
		    }
	    }
	    visited(html);
	}

}
