package main.java.CleanSweep;

import main.java.Navigation.*;
import main.java.Sensor.ISensorArray;
import main.java.Sensor.LocalSensorSource;
import main.java.Sensor.SensorCell;

public class CleanSweep implements INavigationObserver, INavigationChecker {

	private INavigator navigationController;
	
	private ISensorArray sensor;
	
	public CleanSweep() {
		//create the navigation controller
		navigationController = new NavigationController();

		//hook up our navigation events
		navigationController.addNavigationObserver(this);
		navigationController.SetNavigationChecker(this);
		
		//create the sensor
		sensor = new LocalSensorSource();

		//roam around the area for 10 spaces
		navigationController.roam(10);
	}


	@Override
	public void didNavigate(Coordinate navigatedTo) {
		
		// get a notification each time the INavigator moves
		SensorCell currentCellInfo = sensor.GetSensorDataForCoordinate(navigatedTo.getX(), navigatedTo.getY()); 
		
		//we know where we are and can call dirt collection, get data, resolve battery life
		System.out.println(currentCellInfo);
		
		//ex: dirtCollection.CollectDirt(currentCellInfo)
		// or dirtCollection.CollectDirt(currentCellInfo.getDirtAmount(), currentCellInfo.getFloorType());
	
	}

	/**
	 * Method called by the navigation controller before it navigates.
	 * This gives the NavigationChecker(in this case the CS itself) a chance
	 * to stop us from going down stairs or running into an obstacle.
	 * 
	 * This would also seem to be a good place to check the floor type and
	 * figure out how much power we would need to go home.
	 */
	@Override
	public Boolean CheckCoordinate(Coordinate coordinate) {
		SensorCell currentCell = sensor.GetSensorDataForCoordinate(navigationController.CurrentLocation().getX(), navigationController.CurrentLocation().getY());
		
		SensorCell nextCell = sensor.GetSensorDataForCoordinate(coordinate.getX(), coordinate.getY());
		
		System.out.println("About to move to: " + nextCell);
		System.out.println("From current: " + currentCell);
		if(nextCell == null) {
			return false;
		}
		
		//determine direction
		int xDifference = currentCell.getxCoordinate() - nextCell.getxCoordinate();
		int yDifference = currentCell.getyCoordinate() - nextCell.getyCoordinate();
		
		boolean canWeMoveInThisDirection = false;
		
		if(xDifference > 0) {
			//we're moving left
			canWeMoveInThisDirection = currentCell.getLeftNavigatableType().CanMoveTo();
			
		} else if(xDifference < 0) {
			//we're moving right
			canWeMoveInThisDirection = currentCell.getRightNavigatableType().CanMoveTo();
		} else if(yDifference > 0) {
			//we're moving down
			canWeMoveInThisDirection = currentCell.getBottomNavigatableType().CanMoveTo();
		} else if(yDifference < 0) {
			//we're moving up
			canWeMoveInThisDirection = currentCell.getTopNavigatableType().CanMoveTo();
		} else {
			//we shouldnt be able to get here... throw exception?
			
		}
		

		return canWeMoveInThisDirection;
	}
	
	
}
