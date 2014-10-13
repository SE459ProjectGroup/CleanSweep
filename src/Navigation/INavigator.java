package Navigation;

/**
 * Interface used to expose navigation functionality to a consumer.
 * 
 * In our case, this interface will be implemented by a navigation controller which handles
 * the navigation logic. The CleanSweep class can then reference an object implementing this interface
 * so implementing changes and new version should be more simple.
 * @author johnreagan
 *
 */
public interface INavigator {

	/*
	 * Send the navigator to the supplied coordinate. Should only be 1 grid space away
	 */
	void NavigateTo(Coordinate c);

	/**
	 * Get the current location of the object
	 * @return Coordinate
	 */
	Coordinate CurrentLocation();

	/**
	 * Tell the INavigator object to begin moving independent of individual commands
	 */
	void BeginAutoNavigation();
	
}
