package test.java.detectionTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import main.java.Navigation.Coordinate;
import main.java.detection.Surface;

import org.junit.Test;


public class SurfaceTest {

		
		Surface s;
		Coordinate c;

		@Test
		public void getSetSurf_typeTest() {
			s = new Surface(2);
			s.setSurf_type(2);
			int y = s.getSurf_type();
			assertTrue(y ==2);
		}
		
		@Test
		public void getSurfaceFromCoordinateTest() {
		s= new Surface(2);
		c= new Coordinate(0, 0);
			
			String t= s.getSurfaceFromCoordinate(c);
			assertEquals("low_carpet", t);

		}

}
