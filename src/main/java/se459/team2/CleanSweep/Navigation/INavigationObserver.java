/**
 * 
 */
package se459.team2.CleanSweep.Navigation;

/**
 * Interface implemented by objects that would like to be notified when 
 * an INavigator object navigates to a new coordinate
 *
 */
public interface INavigationObserver {

	void didNavigate(Coordinate navigatedTo);
	
}
