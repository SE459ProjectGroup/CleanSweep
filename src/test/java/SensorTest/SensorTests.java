package test.java.SensorTest;
import static org.junit.Assert.*;
import static org.mockito.Mockito.validateMockitoUsage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.Sensor.FloorType;
import main.java.Sensor.ISensorArray;
import main.java.Sensor.ISensorDataSource;
import main.java.Sensor.LocalSensorSource;
import main.java.Sensor.Navigatable;
import main.java.Sensor.SensorCell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class SensorTests {

	
	ISensorArray sensor;
	
	@Before
	public void setUp() throws Exception {
		
		sensor = Mockito.mock(ISensorArray.class);
		Mockito.when(sensor.GetSensorDataForCoordinate(0, 0)).thenReturn(new SensorCell() {{ setFloorType(FloorType.BareFloor); }});
	}

	@After
	public void validate() {
		
	    validateMockitoUsage();
	}

	@Test
	public void TestISensorReturnsObjectWhenPassedValidCoordinate() {
		
		Object result = sensor.GetSensorDataForCoordinate(0, 0);
		
		assertTrue(result != null);

		Mockito.verify(sensor).GetSensorDataForCoordinate(0, 0);
	}
	
	@Test(expected=RuntimeException.class)
	public void TestISensorThrowsRuntimeExceptionWhenPassedInvalidCoordinate() {
		Mockito.when(sensor.GetSensorDataForCoordinate(Mockito.anyInt(), Mockito.anyInt())).thenThrow(new RuntimeException());
		
		sensor.GetSensorDataForCoordinate(1, 1);
		
		Mockito.verify(sensor).GetSensorDataForCoordinate(1, 1);
	}
	
	@Test
	public void TestISensorReturnsSensorCellObjectWhenPassedValidCoordinate() {
		
		SensorCell sc = sensor.GetSensorDataForCoordinate(0, 0);
		
		assertTrue(sc instanceof SensorCell);
		
		Mockito.verify(sensor).GetSensorDataForCoordinate(0, 0);
	}
	
	@Test
	public void TestISensorReturnsCorrectFloorType() {
		
		SensorCell sc = sensor.GetSensorDataForCoordinate(0, 0);
		
		assertNotNull(sc.getFloorType());
		
	}
	
	@Test
	public void TestISensorReturnsCorrectDirtAmount() {
		final int testAmount = 10;
		Mockito.when(sensor.GetSensorDataForCoordinate(1, 0)).thenReturn(new SensorCell() {{
			setFloorType(FloorType.HighCarpet);
			setDirtAmount(testAmount);
		}});
		
		SensorCell sc = sensor.GetSensorDataForCoordinate(1, 0);
		
		assertTrue(sc.getDirtAmount() == testAmount);
		
		Mockito.verify(sensor).GetSensorDataForCoordinate(1, 0);
	}
	
	@Test
	public void TestISensorReturnsCorrectPathsData() {
		Mockito.when(sensor.GetSensorDataForCoordinate(1, 0)).thenReturn(new SensorCell() {{
			setFloorType(FloorType.HighCarpet);
			setDirtAmount(1);
			setRightNavigatableType(Navigatable.Open);
			setLeftNavigatableType(Navigatable.Stairs);
			setTopNavigatableType(Navigatable.Unknown);
			setBottomNavigatableType(Navigatable.Obstacle);
		}});
		
		SensorCell sc = sensor.GetSensorDataForCoordinate(1, 0);
		
		
		assertTrue(sc.getRightNavigatableType() == Navigatable.Open);
		assertTrue(sc.getLeftNavigatableType() == Navigatable.Stairs);
		assertTrue(sc.getTopNavigatableType() == Navigatable.Unknown);
		assertTrue(sc.getBottomNavigatableType() == Navigatable.Obstacle);
		
		Mockito.verify(sensor).GetSensorDataForCoordinate(1, 0);
		
	}
	
	@Test
	public void TestGetCorrectNavigatableFromValidInteger() {
		int[] validInts = new int[]{ 0, 1, 2 ,3 };
		Navigatable[] navTypes = new Navigatable[] { Navigatable.Unknown, Navigatable.Open, Navigatable.Obstacle, Navigatable.Stairs };
		for(int i : validInts) {
			Navigatable n = Navigatable.FromInt(i);
			assertTrue(n == navTypes[i]);
		}
		
	}
	
	
	@Test
	public void TestSensorCellAccuratelyParsesPathString1111() {
		
		SensorCell sc = new SensorCell();
		
		sc.setNavigatableDataFromPathsString("1111");
		
		assertTrue(sc.getRightNavigatableType() == Navigatable.Open);
		assertTrue(sc.getLeftNavigatableType() == Navigatable.Open);
		assertTrue(sc.getTopNavigatableType() == Navigatable.Open);
		assertTrue(sc.getBottomNavigatableType() == Navigatable.Open);
		
	}
	
	@Test
	public void TestSensorCellAccuratelyParsesComplexPathStrings() {
		
		HashMap<String,Navigatable[]> pathToResultMap = new HashMap<String,Navigatable[]>() {{
			put("1211", new Navigatable[] { Navigatable.Open, Navigatable.Obstacle, Navigatable.Open, Navigatable.Open });
			put("2133", new Navigatable[] { Navigatable.Obstacle, Navigatable.Open, Navigatable.Stairs, Navigatable.Stairs });
			put("1123", new Navigatable[] { Navigatable.Open, Navigatable.Open, Navigatable.Obstacle, Navigatable.Stairs });
			put("3211", new Navigatable[] { Navigatable.Stairs, Navigatable.Obstacle, Navigatable.Open, Navigatable.Open });		
		}};
		
		SensorCell sc = new SensorCell();
		
		for(String key : pathToResultMap.keySet()) {
			
			sc.setNavigatableDataFromPathsString(key);
			
			Navigatable[] expectedResults = pathToResultMap.get(key); 
			
			assertTrue(sc.getRightNavigatableType() == expectedResults[0]);
			assertTrue(sc.getLeftNavigatableType() == expectedResults[1]);
			assertTrue(sc.getTopNavigatableType() == expectedResults[2]);
			assertTrue(sc.getBottomNavigatableType() == expectedResults[3]);
			
		}
		
	}
	
	@Test(expected=RuntimeException.class)
	public void TestSensorCellThrowsExceptionIfStringIsTooLong() {
		
		SensorCell sc = new SensorCell();
		
		sc.setNavigatableDataFromPathsString("123456");
		
	}

	@Test(expected=RuntimeException.class)
	public void TestSensorCellThrowsExceptionIfStringIsTooShort() {
		
		SensorCell sc = new SensorCell();
		
		sc.setNavigatableDataFromPathsString("123");
		
	}
	
	@Test(expected=RuntimeException.class)
	public void TestSensorCellThrowsExceptionIfStringIsNonNumeric() {
		
		SensorCell sc = new SensorCell();
		
		sc.setNavigatableDataFromPathsString("111X");
		
	}
	
	@Test
	public void TestISensorDataSourceReturnsCorrectNumberOfSensorCells() {
		
		ISensorDataSource isds = Mockito.mock(ISensorDataSource.class);
		List<SensorCell> cells = new ArrayList<SensorCell>() {{
			add(new SensorCell() {{ setYCoordinate(0); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(0); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(0); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(0); setXCoordinate(0); }});
		}};
		Mockito.when(isds.LoadAllCellsFromSource()).thenReturn(cells);
		
		List<SensorCell> returnedCells = isds.LoadAllCellsFromSource();
		
		assertTrue(cells.size() == returnedCells.size());
		
		Mockito.verify(isds).LoadAllCellsFromSource();
		
	}
	
	@Test
	public void TestLocalSensorDataSourceReturnsNotNullDataForValidCoordinate() {
		
		ISensorDataSource isds = Mockito.mock(ISensorDataSource.class);
		List<SensorCell> cells = new ArrayList<SensorCell>() {{
			add(new SensorCell() {{ setYCoordinate(0); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(1); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(2); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(3); setXCoordinate(0); }});
		}};
		Mockito.when(isds.LoadAllCellsFromSource()).thenReturn(cells);
		
		
		
		ISensorArray sensorArray = new LocalSensorSource(isds);
		
		
		assertNotNull(sensorArray.GetSensorDataForCoordinate(0, 0));
		
		
	}
	
	@Test
	public void TestLocalSensorDataSourceReturnsNullForCoordinateNotInDS() {
		ISensorDataSource isds = Mockito.mock(ISensorDataSource.class);
		List<SensorCell> cells = new ArrayList<SensorCell>() {{
			add(new SensorCell() {{ setYCoordinate(0); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(1); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(2); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(3); setXCoordinate(0); }});
		}};
		Mockito.when(isds.LoadAllCellsFromSource()).thenReturn(cells);
		
		
		
		ISensorArray sensorArray = new LocalSensorSource(isds);
		
		
		assertNull(sensorArray.GetSensorDataForCoordinate(1, 1));
		
	}
	
	@Test
	public void TestLocalSensorDataSourceReturnsCorrectCellForValidCoordinate() {
		ISensorDataSource isds = Mockito.mock(ISensorDataSource.class);
		
		final SensorCell oneTwoCell = new SensorCell() {{ setXCoordinate(1); setYCoordinate(2); }};
		List<SensorCell> cells = new ArrayList<SensorCell>() {{
			add(new SensorCell() {{ setYCoordinate(0); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(1); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(2); setXCoordinate(0); }});
			add(new SensorCell() {{ setYCoordinate(3); setXCoordinate(0); }});
			add(oneTwoCell);
		}};
		Mockito.when(isds.LoadAllCellsFromSource()).thenReturn(cells);
		
		ISensorArray sensorArray = new LocalSensorSource(isds);
		
		assertTrue(oneTwoCell.equals(sensorArray.GetSensorDataForCoordinate(1, 2)));
		
	}	
	
}
