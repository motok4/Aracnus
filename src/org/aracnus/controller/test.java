package org.aracnus.controller;

import java.util.ArrayList;


public class test {


	public static void main(String[] args)  {
		
		SimpleCore c = new SimpleCore();
		c.setMaxPageToFetch(1000);
		Aracnus ar = new Aracnus();
		ar.addSeed("http://localhost/crawllertest/");
		
		long ini = System.currentTimeMillis();
		ar.execute(c);
        long tempo = System.currentTimeMillis() - ini;
        System.out.println("TOTAL DE PAGINAS: "+c.getOutgoingLinks().size() );
        System.out.println("TEMPO TOTAL: "+ (float) tempo/1000 +" segundos");
//        
        /*
         * TOTAL DE PAGINAS: 87
TEMPO TOTAL: 90.136 segundos
         */
//		ar2.execute(d);
//		for(String url : c.getOutgoingLinks() ){
//			for( String url2 : d.getOutgoingLinks() ){
//				if( url.equals(url2)){
//					System.out.println("URL: "+url);
//				}
//			}
//		}
	}

}
