package main.java.CleanSweep;

import main.java.Navigation.*;

public class CleanSweep implements INavigationObserver {

	private INavigator navigationController;
	
	
	public CleanSweep() {
		
		navigationController = new NavigationController();
		
		navigationController.addNavigationObserver(this);
		
		//roam around the area for 10 spaces
		navigationController.roam(10);
	}


	@Override
	public void didNavigate(Coordinate navigatedTo) {
		// get a notification each time the INavigator moves
		
		//we know where we are and can call dirt collection, get data, resolve battery life
		
	}
	
	
	
	
	
}
