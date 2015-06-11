package test;

import static org.junit.Assert.*;

import org.aracnus.controller.SimpleCore;
import org.aracnus.controller.Url;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestURL {

	public TestURL() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
//	public void test2() {
//			SimpleCore s = new SimpleCore();
//			
//
//			String url = s.treatUrl( new Url("home/page1.html", 1, new Url("http://localhost/crawllertest/", 0, null) ) );
//			assertTrue( url.equals( "http://localhost/crawllertest/home/page1.html") );
////			assertTrue( s.getPath().equals("http://localhost/crawllertest/home/page1.html"));
//			
//			url = s.treatUrl( new Url("home/page2.html", 1, new Url("http://localhost/crawllertest/", 0, null) ));
//			System.out.println(url);
//			assertTrue( url.equals( "http://localhost/crawllertest/home/page2.html") );
////			assertTrue( s.getPath().equals("http://localhost/crawllertest/home/page2.html"));
//			
//			url = s.treatUrl( new Url("home/page3.html", 1, new Url("http://localhost/crawllertest/", 0, null) ));
//			System.out.println(url);
//			assertTrue( url.equals( "http://localhost/crawllertest/home/page3.html") );
////			assertTrue( s.getPath().equals("http://localhost/crawllertest/home/page3.html"));
//			
//			url = s.treatUrl( new Url("../page1/page6.html", 2, new Url("http://localhost/crawllertest/home/page1.html", 1, null) ) );
//			assertTrue( url.equals("http://localhost/crawllertest/home/../page1/page6.html" ));
////			assertTrue( s.getPath().equals("http://localhost/crawllertest/home/../page1/page6.html"));
//			
//			url = s.treatUrl( new Url("page2/page6.html",3, new Url("http://localhost/crawllertest/page1/page6.html", 2, null));
//			System.out.println(url);
//			assertTrue( url.equals("http://localhost/crawllertest/home/../page1/page6.html" ));
////			assertTrue( s.getPath().equals("http://localhost/crawllertest/home/../page1/page2/page6.html"));
//			
//			
//		}
//	@Test
	public void test() {
		SimpleCore s = new SimpleCore();
		
		
		String url = s.treatUrl( new Url("home/page1.html", 1, new Url("http://localhost/crawllertest/", 0, null) ) );
		assertTrue( url.equals( "http://localhost/crawllertest/home/page1.html") );
//		assertTrue( s.getPath().equals("http://localhost/crawllertest/home/page1.html"));
		
		url = s.treatUrl( new Url("home/page2.html", 1, new Url("http://localhost/crawllertest/", 0, null) ));
		System.out.println(url);
		assertTrue( url.equals( "http://localhost/crawllertest/home/page2.html") );
//		assertTrue( s.getPath().equals("http://localhost/crawllertest/home/page2.html"));
		
		url = s.treatUrl( new Url("home/page3.html", 1, new Url("http://localhost/crawllertest/", 0, null) ));
		System.out.println(url);
		assertTrue( url.equals( "http://localhost/crawllertest/home/page3.html") );
//		assertTrue( s.getPath().equals("http://localhost/crawllertest/home/page3.html"));
		
		url = s.treatUrl( new Url("../page1/page6.html", 2, new Url("http://localhost/crawllertest/home/page1.html", 1, null) ) );
		assertTrue( url.equals("http://localhost/crawllertest/home/../page1/page6.html" ));
//		assertTrue( s.getPath().equals("http://localhost/crawllertest/home/../page1/page6.html"));
		
		url = s.treatUrl( new Url("page2/page6.html",3, new Url("http://localhost/crawllertest/page1/page6.html", 2, null));
		System.out.println(url);
		assertTrue( url.equals("http://localhost/crawllertest/home/../page1/page6.html" ));
//		assertTrue( s.getPath().equals("http://localhost/crawllertest/home/../page1/page2/page6.html"));
		
		
	}

}
