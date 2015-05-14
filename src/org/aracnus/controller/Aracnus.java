package org.aracnus.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;

public class Aracnus {

	private Set<String> outgoingLinks = new HashSet<String>();
	private LinkedTransferQueue<String > outgoingLinksToVisit = new LinkedTransferQueue<>();
	
	public void addSeed(String string) {
		if( !outgoingLinks.contains( string )){
			outgoingLinks.add(string);
			outgoingLinksToVisit.add(string);
		}
		
	}

	public void execute( AbstractCore aracnus ) {
		aracnus.setOutgoingLinks(outgoingLinks);
		aracnus.setOutgoingLinksToVisit(outgoingLinksToVisit);
		aracnus.execute();
		
	}

}
