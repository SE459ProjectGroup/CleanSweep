package se459.team2.CleanSweep.Navigation;

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

	INavigationChecker NavigationChecker = null;

	/*
	 * Send the navigator to the supplied coordinate. Should only be 1 grid space away
	 */
	boolean MoveTo(Coordinate c);

	/**
	 * Get the current location of the object
	 * @return Coordinate
	 */
	Coordinate CurrentLocation();

	void SetDestinationPoint(Coordinate c);

	Coordinate GetDestinationPoint();

	boolean MoveToDestination();

	void SetNavigationChecker(INavigationChecker inc);

	void addNavigationObserver(INavigationObserver observer);

	void returnToOrigin();

	//int GetWeightedCostToOrigin();

	void roam(int i);

	Coordinate PreviousLocation();

	NavigationState CurrentNavigationState();
	
}
