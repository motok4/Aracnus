package test;

import static org.junit.Assert.*;

import org.aracnus.controller.SimpleCore;
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
	public void test() {
		SimpleCore s = new SimpleCore();
		
		s.setPath("http://localhost/crawllertest/");
		String url = s.treatUrl( "home/page1.html");
		assertTrue( url.equals( "http://localhost/crawllertest/home/page1.html") );
		assertTrue( s.getPath().equals("http://localhost/crawllertest/home/page1.html"));
		
		url = s.treatUrl( "home/page2.html");
		System.out.println(url);
		assertTrue( url.equals( "http://localhost/crawllertest/home/page2.html") );
		assertTrue( s.getPath().equals("http://localhost/crawllertest/home/page2.html"));
		
		url = s.treatUrl( "home/page3.html");
		System.out.println(url);
		assertTrue( url.equals( "http://localhost/crawllertest/home/page3.html") );
		assertTrue( s.getPath().equals("http://localhost/crawllertest/home/page3.html"));
		
		url = s.treatUrl( "../page1/page6.html");
		assertTrue( url.equals("http://localhost/crawllertest/home/../page1/page6.html" ));
		assertTrue( s.getPath().equals("http://localhost/crawllertest/home/../page1/page6.html"));
		
		url = s.treatUrl( "page2/page6.html");
		System.out.println(url);
		assertTrue( url.equals("http://localhost/crawllertest/home/../page1/page2/page6.html" ));
		assertTrue( s.getPath().equals("http://localhost/crawllertest/home/../page1/page2/page6.html"));
		
		
	}

}
