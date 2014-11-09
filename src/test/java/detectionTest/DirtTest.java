package test.java.detectionTest;

import static org.junit.Assert.*;
import main.java.Navigation.Coordinate;
import main.java.detection.Dirt;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import main.java.Sensor.XMLParser.*;
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
