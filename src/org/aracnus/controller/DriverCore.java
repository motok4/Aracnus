package org.aracnus.controller;

import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;
import java.util.regex.Pattern;


public class DriverCore extends AbstractCore{
	

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
			+ "|png|mp3|mp3|zip|gz))$");

	 

	public DriverCore(){}

	@Override
	public Boolean shouldVisit(String url) {
		String href = url.toLowerCase();        
        return !FILTERS.matcher(href).matches();// && href.startsWith("http://g1.globo.com/politica/noticia/");
	}

	@Override
	public void visited(String html) {
		
	}

}
