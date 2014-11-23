package se459.team2.CleanSweep.CleanSweepControlTests;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import se459.team2.CleanSweep.CleanSweep.*;
import se459.team2.CleanSweep.Navigation.Coordinate;
import se459.team2.CleanSweep.Navigation.INavigationObserver;
import se459.team2.CleanSweep.Navigation.NavigationState;

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
		
 		
	}
	
	@Test
	public void TestCleanSweepEmptiesDirtWhenAtDockingStation() {
		
		baseCS.getNavigationController().SetDestinationPoint(new Coordinate(1,1));
		
		baseCS.getNavigationController().MoveToDestination();
		
		baseCS.getDirtCollection().setDirtCount(50);
		
		baseCS.getNavigationController().returnToOrigin();
		//baseCS.getNavigationController().roam(1);
		
		//assertTrue(baseCS.getNavigationController().CurrentLocation().equals(new Coordinate(0,0)));
		assertTrue(baseCS.getDirtCollection().getDirtCount() == 0);
	}
	
	@Test
	public void TestCleanSweepReturnsToDockingStationAndContinuesToDestinationWhenFull() {
		
		class ReturnToOriginObserver implements INavigationObserver {

			public int totalMoves = 0;
			public boolean didSetNavigaitonStateToReturnToOrgin = false;
			public boolean didReturnToOrigin = false;
			
			@Override
			public void didNavigate(Coordinate navigatedTo) {
				
				totalMoves++;
				if(baseCS.getNavigationController().CurrentNavigationState() == NavigationState.ReturningToOrgin) {
					
					didSetNavigaitonStateToReturnToOrgin = true;
				}
				
				if(navigatedTo.equals(new Coordinate(0,0))) {
					didReturnToOrigin = true;
				}
				
			}
			
		}
		
		baseCS.getNavigationController().SetDestinationPoint(new Coordinate(0,1));
		
		baseCS.getNavigationController().MoveToDestination();
		
		baseCS.getDirtCollection().setDirtCount(50);
		ReturnToOriginObserver observer = new ReturnToOriginObserver();
		baseCS.getNavigationController().addNavigationObserver(observer);
		
		baseCS.getNavigationController().SetDestinationPoint(new Coordinate(1,1));
		baseCS.getNavigationController().MoveToDestination();
		
		assertTrue(observer.didSetNavigaitonStateToReturnToOrgin);
		assertTrue(observer.didReturnToOrigin);
		assertTrue(observer.totalMoves > 1);
		
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
