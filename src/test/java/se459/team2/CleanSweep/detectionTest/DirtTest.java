package se459.team2.CleanSweep.detectionTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import se459.team2.CleanSweep.Navigation.Coordinate;
import se459.team2.CleanSweep.XMLParser.*;
import se459.team2.CleanSweep.detection.Dirt;
public class DirtTest {
	
	Dirt d;
	Coordinate c;

	@Test
	public void getSetDirtTest() {
		d = new Dirt();
		d.setDirt(2);
		int y = d.getDirt();
		assertTrue(y ==2);
	}
	@Before
	public void create() {
	
		d = Mockito.mock(Dirt.class);	
		c= new Coordinate(0, 0);
		when(d.getDirtFromCoordinate(c)).thenReturn(ParseXML.getDirtFromCoordinates(0, 0));

	}
	@Test
	public void getDirtFormCoordinateTest() {
		assertEquals(1, d.getDirtFromCoordinate(c));
	
	}
}
