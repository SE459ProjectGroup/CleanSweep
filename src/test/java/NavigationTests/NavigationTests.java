package test.java.NavigationTests;

import static org.junit.Assert.*;

import main.java.Navigation.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class NavigationTests implements INavigationObserver {

	
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
	
	
	
	
	@Test
	public void TestNavigatorReturnsAccurateWeightedPointsToOrigin() {
		
		Coordinate destination = new Coordinate(10,10);
		
		toNavigate.SetDestinationPoint(destination);
		
		toNavigate.MoveToDestination();
		
		toNavigate.addNavigationObserver(this);
		
		int weightedToHome = toNavigate.GetWeightedCostToOrigin();
		
		toNavigate.returnToOrigin();
		
		assertTrue(this.manualOriginWeightTracker == weightedToHome);
	}

	private int manualOriginWeightTracker;
	
	@Override
	public void didNavigate(Coordinate navigatedTo) {
		
		manualOriginWeightTracker++;
		
		
	}

	
	
}
