package main.java.CleanSweep;

import main.java.Navigation.*;
import main.java.Sensor.ISensorArray;
import main.java.Sensor.LocalSensorSource;
import main.java.Sensor.SensorCell;

public class CleanSweep implements INavigationObserver {

	private INavigator navigationController;
	
	private ISensorArray sensor;
	
	public CleanSweep() {
		
		//create the sensor
		sensor = new LocalSensorSource();
		
		//create the navigation controller
		NavigationController nc = new NavigationController();
		nc.SetNavigationChecker(nc);
		nc.SetISensorArray(sensor);
		navigationController = nc;
		
		//hook up our navigation events
		navigationController.addNavigationObserver(this);
		
		
		
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


	
	
}
