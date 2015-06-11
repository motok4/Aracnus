package org.aracnus.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;

public class Aracnus {

	private Set<Url> outgoingLinks = new HashSet<Url>();
	private LinkedTransferQueue<Url> outgoingLinksToVisit = new LinkedTransferQueue<>();
	
	public void addSeed(String string) {
		Url url = new Url( string,0,null);
		if( !outgoingLinks.contains( url )){
			outgoingLinks.add(url);
			outgoingLinksToVisit.add(url);
		}
		
	}

	public void execute( AbstractCore aracnus ) {
		aracnus.setOutgoingLinks(outgoingLinks);
		aracnus.setOutgoingLinksToVisit(outgoingLinksToVisit);
		aracnus.execute();
		
	}

}
