package se459.team2.CleanSweep.NavigationTests;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import se459.team2.CleanSweep.Navigation.*;

import static org.mockito.Mockito.*;

public class NavigationTest implements INavigationObserver {

	
	private INavigator toNavigate;
	
	@Before
	public void setUp() throws Exception {
		
		//toNavigate = mock(INavigatable.class);
		toNavigate = new NavigationController();
	}

	@After
	public void validate() {
	    validateMockitoUsage();
	}

	@Test
	public void TestCurrentLocationUpdatesAfterNavigateTo() {
		
		Coordinate c = new Coordinate(0, 1);
		toNavigate.MoveTo(c);
		
		assert(toNavigate.CurrentLocation().equals(c));
		
	}
	
	@Test
	public void TestInitialValueIsOrigin() {
		assert(toNavigate.CurrentLocation().equals(new Coordinate(0,0)));
	}
	
	@Test(expected=RuntimeException.class)
	public void TestExceptionIsThrownIfAttemptingToMoveMoreThanOneSpace() {
		
		Coordinate c = new Coordinate(2,5);
		toNavigate.MoveTo(c);
		
	}

	@Test(expected=RuntimeException.class)
	public void TestExceptionIsThrownIfAttemptingToMoveDiagonally() {
		
		Coordinate c = new Coordinate(1,1);
		toNavigate.MoveTo(c);
		
	}

	@Test
	public void TestINavigatorSetDestinationPointIsSaved() {
		
		Coordinate c = new Coordinate(10,10);
		toNavigate.SetDestinationPoint(c);
		assert(c.equals(toNavigate.GetDestinationPoint()));
		
	}
	
	@Test
	public void TestINavigatorMovesToDestinationPoint() {
		
		Coordinate c = new Coordinate(5,5);
		toNavigate.SetDestinationPoint(c);
		
		toNavigate.MoveToDestination();
		boolean res = c.equals(toNavigate.CurrentLocation());
		assertTrue(res);
		
	}
	
	@Test
	public void TestINavigatorCallsINavigationChecker() {
		INavigationChecker inc = Mockito.mock(INavigationChecker.class);
		
		toNavigate.SetNavigationChecker(inc);
		Coordinate d = new Coordinate(1,0);
		toNavigate.MoveTo(d);
		
		verify(inc).CheckCoordinate(d);
	}
	
	
	@Test
	public void TestINavigatorDoesNotMoveToPointIfCheckerReturnsFalse() {
		INavigationChecker inc = Mockito.mock(INavigationChecker.class);
		Coordinate destination = new Coordinate(0,1);
		when(inc.CheckCoordinate(destination)).thenReturn(false);

		toNavigate.SetNavigationChecker(inc);
		
		toNavigate.MoveTo(destination);
		
		assertFalse(toNavigate.CurrentLocation().equals(destination));
		//verify(inc);
	}
	
	@Test
	public void TestINavigatorMovesToCoordinateIfCheckerReturnsTrue() {
		INavigationChecker inc = Mockito.mock(INavigationChecker.class);
		Coordinate destination = new Coordinate(0,1);
		when(inc.CheckCoordinate(destination)).thenReturn(true);
		
		toNavigate.SetNavigationChecker(inc);
		
		toNavigate.MoveTo(destination);
		
		assertTrue(toNavigate.CurrentLocation().equals(destination));
		
	
	}
	
	
	@Test
	public void TestINavigatorMovesAroundObjectIfItCannotNavigateToASpotInItsDefaultPath() {
	
		INavigationChecker inc = Mockito.mock(INavigationChecker.class);
		
		Coordinate obstacle = new Coordinate(0,1);
		Coordinate destination = new Coordinate(0,2);
		
		when(inc.CheckCoordinate((Coordinate) anyObject())).thenReturn(true);
		when(inc.CheckCoordinate(obstacle)).thenReturn(false);
		
		toNavigate.SetNavigationChecker(inc);
		
		toNavigate.SetDestinationPoint(destination);
		
		toNavigate.MoveToDestination();
		
		assertTrue(toNavigate.CurrentLocation().equals(destination));
		
	}
	
	@Test
	public void TestINavigatorNotifiesWhenItHasNavigatedToACoordinate() {
		
		
		INavigationObserver observer = mock(INavigationObserver.class);
	
		toNavigate.addNavigationObserver(observer);
		
		Coordinate destination = new Coordinate(0,1);
		
		toNavigate.MoveTo(destination);
		
		verify(observer).didNavigate(destination);
		
	}
	
	@Test
	public void TestNavigatorReturnsToOrgin() {
	
		Coordinate destination = new Coordinate(10,10);
		
		toNavigate.SetDestinationPoint(destination);
		
		toNavigate.MoveToDestination();
		
		assertTrue(toNavigate.CurrentLocation().equals(destination));
		
		toNavigate.returnToOrigin();
		
		assertTrue(toNavigate.CurrentLocation().equals(new Coordinate(0,0)));
	
	}
	
	
	
	
//	@Test
//	public void TestNavigatorReturnsAccurateWeightedPointsToOrigin() {
//		
//		Coordinate destination = new Coordinate(10,10);
//		
//		toNavigate.SetDestinationPoint(destination);
//		
//		toNavigate.MoveToDestination();
//		
//		toNavigate.addNavigationObserver(this);
//		
//		int weightedToHome = toNavigate.GetWeightedCostToOrigin();
//		
//		toNavigate.returnToOrigin();
//		
//		assertTrue(this.manualOriginWeightTracker == weightedToHome);
//	}

	private int manualOriginWeightTracker;
	
	@Override
	public void didNavigate(Coordinate navigatedTo) {
		
		manualOriginWeightTracker++;
		
		
	}

	@Test
	public void TestPreviousLocationIsAccurate() {
		
		Coordinate origin = new Coordinate(0,0);
		
		Coordinate next = new Coordinate(0,1);
		
		Coordinate moveTwo = new Coordinate(1,1);
		
		toNavigate.MoveTo(next);
		
		assertTrue(toNavigate.PreviousLocation().equals(origin));
		
		toNavigate.MoveTo(origin);
		
		assertTrue(toNavigate.PreviousLocation().equals(next));
		
		toNavigate.MoveTo(next);
		toNavigate.MoveTo(moveTwo);
		
		assertTrue(toNavigate.PreviousLocation().equals(next));
		
	}
	

	@Test 
	public void TestAutoRoamMovesINavigator() {
		
		toNavigate.addNavigationObserver(this);
		toNavigate.roam(10);
		
		assertFalse(toNavigate.CurrentLocation().equals(new Coordinate(0,0)));
		
	}
	
	@Test
	public void TestNavigatorStopsRoamingWhenInstructedToReturnToOrigin() {
		
		class RoamingObserver implements INavigationObserver {

			
			public int roamedCoordinates = 0;
			
			public final int stopAfterCount = 10;
			
			@Override
			public void didNavigate(Coordinate navigatedTo) {
				if (toNavigate.CurrentNavigationState() != NavigationState.ReturningToOrgin) roamedCoordinates++;
				
				if (roamedCoordinates >= stopAfterCount) {
					toNavigate.returnToOrigin();
				}
			}
			
			
		}
		
		RoamingObserver ro = new RoamingObserver();
		toNavigate.addNavigationObserver(ro);
		
		toNavigate.roam(20);
		
		assertTrue(ro.roamedCoordinates == ro.stopAfterCount);
		
	}
	
	
	@Test
	public void TestNavigatorReturnsToHomeAndStopsWhenInstructed() {
		
		class OriginObserver implements INavigationObserver {

			int returnAfterCount = 10;
			
			int spacesNavigated = 0;
			
			@Override
			public void didNavigate(Coordinate navigatedTo) {
				spacesNavigated++;
				
				if (spacesNavigated >= returnAfterCount) {
					
					toNavigate.returnToOrigin();
				}
				
				
			}
			
			
		}
		
		toNavigate.addNavigationObserver(new OriginObserver());
		
		toNavigate.roam(20);
		
		assertTrue(toNavigate.CurrentLocation().equals(new Coordinate(0,0)));
		
	}
	
	
	@Test
	public void TestNavigatorDefaultsToMovingToAnUnexploredCoordinate() {
		
		Coordinate origin = new Coordinate(0,0);
		
		toNavigate.roam(1);
		
		Coordinate move1 = toNavigate.CurrentLocation();

		toNavigate.MoveTo(origin);
		
		toNavigate.roam(1);
		
		assertFalse(toNavigate.CurrentLocation().equals(move1));
		
	}
	
	
	
}
