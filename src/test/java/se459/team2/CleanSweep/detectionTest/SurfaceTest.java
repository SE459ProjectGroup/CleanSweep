package se459.team2.CleanSweep.detectionTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import se459.team2.CleanSweep.Navigation.Coordinate;
import se459.team2.CleanSweep.detection.Surface;


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
