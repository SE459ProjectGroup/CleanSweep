package Navigation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

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
		toNavigate.NavigateTo(c);
		
		assert(toNavigate.CurrentLocation().equals(c));
		
	}
	
	@Test
	public void TestInitialValueIsOrigin() {
		assert(toNavigate.CurrentLocation().equals(new Coordinate(0,0)));
	}
	
	@Test(expected=RuntimeException.class)
	public void TestExceptionIsThrownIfAttemptingToMoveMoreThanOneSpace() {
		
		Coordinate c = new Coordinate(2,5);
		toNavigate.NavigateTo(c);
		
	}
	
	@Test
	public void TestBeginAutoNavigationMovesObjectFromOrigin() {
		
		toNavigate.BeginAutoNavigation();
		boolean test = (new Coordinate(0,0).equals(toNavigate.CurrentLocation()));
		assertFalse(test);
		
	}

}
