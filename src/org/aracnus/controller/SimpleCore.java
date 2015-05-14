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
			+ "|png|mp3|mp3|zip|gz))$");
	private final static Pattern EXTENSION = Pattern.compile("[a-zA-Z0-9]*\\.[a-zA-Z0-9#&?]*");


	@Override
	public Boolean shouldVisit(String url) {
		String href = url.toLowerCase();        
        return !FILTERS.matcher(href).matches();// && href.startsWith("http://g1.globo.com/politica/noticia/");
	}

	@Override
	public void visited(String html) {
		
	}
	
	public void howtovisit( String url ){
		URL obj;
		try {
			obj = new URL(url);
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
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	}
