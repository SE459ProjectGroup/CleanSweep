package Navigation.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import static org.mockito.Mockito.*;

import Navigation.Coordinate;
import Navigation.*;

public class NavigationTests {

	
	private INavigator toNavigate;
	
	@Before
	public void setUp() throws Exception {
		
		//toNavigate = mock(INavigatable.class);
		toNavigate = new NavigationController();
	}

	@After
	public void tearDown() throws Exception {
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
	public void TestBeginAutoNavigationMovesObjectFromOrigin() {
		
		toNavigate.BeginAutoNavigation();
		boolean test = (new Coordinate(0,0).equals(toNavigate.CurrentLocation()));
		assertFalse(test);
		
	}

	@Test
	public void TestINavigatorSetDestinationPointIsSaved() {
		
		Coordinate c = new Coordinate(10,10);
		toNavigate.SetDestinationPoint(c);
		assert(c.equals(toNavigate.GetDestinationPoint()));
		
	}
	
}
