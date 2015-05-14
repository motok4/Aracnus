package org.aracnus.controller;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class test {

	
	public static void main(String[] args)  {
		DriverCore d = new DriverCore();
		SimpleCore c = new SimpleCore();
		Aracnus ar = new Aracnus();
		Aracnus ar2 = new Aracnus();
		d.setMaxPageToFetch(100);
		ar.addSeed("http://localhost/crawllertest/");
		ar2.addSeed("http://localhost/crawllertest/");
		ar.execute(d);
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
