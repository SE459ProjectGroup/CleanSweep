package test.java.CleanSweepControlTests;

import static org.junit.Assert.*;

import main.java.CleanSweep.*;
import main.java.Navigation.Coordinate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CleanSweepControlTest {

	
	CleanSweep baseCS;
	
	@Before
	public void setup() {
		
		baseCS = new CleanSweep();
	
	}
	
	@Test
	public void TestCleanSweepReturnsToDockingStationBeforeRunningOutOfBattery() {
		
		double originalBatteryLife = baseCS.getPower().GetBatteryLevel();
		
		baseCS.getNavigationController().roam(1);
		
		double costOfNav = originalBatteryLife - baseCS.getPower().GetBatteryLevel();
		
		baseCS.getPower().RequestEnergy(baseCS.getPower().GetBatteryLevel() - costOfNav);
		
		baseCS.getNavigationController().roam(1);
		
		assertTrue(baseCS.getNavigationController().CurrentLocation().equals(new Coordinate(0,0)));
		
	}
	
	@Test
	public void TestCleanSweepReturnsToOriginWhenDirtFull() {
		
		baseCS.getNavigationController().SetDestinationPoint(new Coordinate(1,1));
		
		baseCS.getNavigationController().MoveToDestination();
		
		baseCS.getDirtCollection().setDirtCount(50);
		
		baseCS.getNavigationController().roam(1);
		
		assertTrue(baseCS.getNavigationController().CurrentLocation().equals(new Coordinate(0,0)));
		
	}
	
	@Test
	public void TestCleanSweepChargesWhenReturnsToDockingStation() {
		
		double startingBatteryLife = baseCS.getPower().GetBatteryLevel();
		
		baseCS.getNavigationController().SetDestinationPoint(new Coordinate(1,5));
		
		baseCS.getNavigationController().MoveToDestination();
		
		baseCS.getNavigationController().returnToOrigin();
		
		assertTrue(startingBatteryLife == baseCS.getPower().GetBatteryLevel());
		
	}
	
}
