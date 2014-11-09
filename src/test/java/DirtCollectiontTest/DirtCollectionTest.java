package test.java.DirtCollectiontTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import main.java.DirtCollection.DirtCollection;
import main.java.Navigation.Coordinate;
import main.java.Sensor.XMLParser.ParseXML;
import main.java.detection.Dirt;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DirtCollectionTest {

	
	DirtCollection d1;
	DirtCollection d2;
	DirtCollection d3;
	@Test
	public void collectDirtTest1() {
		DirtCollection d1= new DirtCollection();
		assertTrue(d1.collectDirt());	
		assertEquals(d1.getCurrentDirt(),1);

	}
	
	@Test
	public void collectDirtTest2() {
		 d2 = new DirtCollection();
		for (int i = 0; i < 49; i++) {
			 d2.collectDirt();
		}
		assertTrue(d2.collectDirt());
		assertEquals(d2.getCurrentDirt(),50);
	}
	
	@Test
	public void collectDirtTest3() {
	 d3 = new DirtCollection();
		for (int i = 0; i < 51; i++) {
			 d3.collectDirt();
		}
	
		assertFalse(d3.collectDirt());	
		assertEquals(d3.getCurrentDirt(),50);
	}

	
	
}
