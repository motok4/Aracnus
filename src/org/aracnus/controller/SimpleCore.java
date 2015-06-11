package org.aracnus.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import sun.net.www.http.HttpClient;

public class SimpleCore extends AbstractCore {

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
			+ "|png|mp3|mp3|zip|gz|pdf|ppt|pptx|docx))$");
	private final static Pattern EXTENSION = Pattern.compile("[a-zA-Z0-9]*\\.[a-zA-Z0-9#&?]*");


	@Override
	public Boolean shouldVisit(Url url) {
		String href = url.getUrl().toLowerCase();        
        return !FILTERS.matcher(href).matches() ;//&& href.startsWith("http://programo.com.br/");
	}

	@Override
	public void visited(String html) {
		
	}
	@Override
	public void howtovisit( Url url ){
		URL obj;
		try {
			obj = new URL(url.getUrl());
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();			
			// optional default is GET
			con.setRequestMethod("GET");			
			//add request header
			con.setRequestProperty("User-Agent", "Mozila");
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			String html = response.toString();
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
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	}
