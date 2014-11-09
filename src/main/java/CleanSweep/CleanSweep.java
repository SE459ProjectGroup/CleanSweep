package main.java.CleanSweep;

import main.java.DirtCollection.DirtCollection;
import main.java.Navigation.*;
import main.java.PowerManagement.IPowerManager;
import main.java.PowerManagement.TempPowerManager;
import main.java.Sensor.ISensorArray;
import main.java.Sensor.LocalSensorSource;
import main.java.Sensor.SensorCell;

public class CleanSweep implements INavigationObserver, INavigationChecker {

	private INavigator navigationController;
	
	private ISensorArray sensor;
	
	private IPowerManager power;
	
	private DirtCollection dirtCollection;
	
	private CleanSweepAnalytics analytics = new CleanSweepAnalytics();
	
	public CleanSweep() {
		//create the navigation controller
		navigationController = new NavigationController();

		//hook up our navigation events
		navigationController.addNavigationObserver(this);
		navigationController.SetNavigationChecker(this);
		
		//create the sensor
		sensor = new LocalSensorSource();

		//create the power management object
		power = new TempPowerManager();
		
		//create dirt collection
		dirtCollection = new DirtCollection();
		try{
		//roam around the area for 10 spaces
		for (int i = 0; i < 10; i++) {
			navigationController.roam(10);
		}
		}catch(Exception e) {
			
			System.out.println(e);
		}
		System.out.println("**********************************");
		System.out.println("Roaming Complete! Statistics Below:");
		System.out.print(this.analytics);
		
	}


	@Override
	public void didNavigate(Coordinate navigatedTo) {
		
		// get a notification each time the INavigator moves
		SensorCell currentCellInfo = sensor.GetSensorDataForCoordinate(navigatedTo.getX(), navigatedTo.getY()); 
		
		//update our stats
		analytics.spacesMoves++;
		
		if (this.navigationController.CurrentNavigationState() == NavigationState.Navigating) {
			analytics.spacesRoamed++;
		}
		
		//we know where we are and can call dirt collection, get data, resolve battery life
		System.out.println("====================================");
		System.out.println("CS has moved to " + currentCellInfo.getXCoordinate() + ", " + currentCellInfo.getYCoordinate());
		System.out.println("Current Battery Life: " + power.GetBatteryLevel());
		System.out.println("Current Dirt Held: " + dirtCollection.getCurrentDirt());
		System.out.println("Current Navigation State: " + navigationController.CurrentNavigationState());
		System.out.println("Current Weighted Cost to home: " + this.GetWeightedCostToOrigin(navigationController.CurrentLocation()));
		System.out.println("Cell Debug Info " + currentCellInfo);
		System.out.println("====================================");
		//ex: dirtCollection.CollectDirt(currentCellInfo)
		// or dirtCollection.CollectDirt(currentCellInfo.getDirtAmount(), currentCellInfo.getFloorType());
		
		//charge the CS if we are at the charging station
		if (currentCellInfo.isChargingStation()) {
			//update our stats
			analytics.timesRecharged++;
			power.Charge();
			
		}
		
		while(currentCellInfo.getDirtAmount() > 0 && navigationController.CurrentNavigationState() != NavigationState.ReturningToOrgin) {
			double costToHome = this.GetWeightedCostToOrigin(navigationController.CurrentLocation());
			double currentBatteryLife = power.GetBatteryLevel();
			double batteryLifeAfterDirtCollection = currentBatteryLife - currentCellInfo.getFloorType().GetValue();
			double batteryLifeAfterNavigatingToHome = batteryLifeAfterDirtCollection - costToHome;
			
			if(batteryLifeAfterNavigatingToHome > 0) {
				
				//proceed with dirt collection
				dirtCollection.collectDirt();
				currentCellInfo.setDirtAmount(currentCellInfo.getDirtAmount() - 1);
				
				//update our stats
				analytics.dirtSwept++;
				
				if(power.RequestEnergy(currentCellInfo.getFloorType().GetValue()) == false) {
					throw new RuntimeException("We've run out of battery life!");
				}
				//update our stats
				analytics.powerUsed+=currentCellInfo.getFloorType().GetValue();
				
				
			} else {
				
				//we don't have power to collect this dirt.
				this.navigationController.returnToOrigin();
				//we definetely need to exit, should we also
				break;
			}
			
		}
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
		
		//System.out.println("About to move to: " + nextCell);
		//System.out.println("From current: " + currentCell);
		if(nextCell == null) {
			return false;
		}
		
		boolean canWeMoveInThisDirection = checkNavigationObstacles(
				currentCell, nextCell);
		
		//now calculate power cost of the move
		double nextMoveNavCost = (currentCell.getFloorType().GetValue()  + nextCell.getFloorType().GetValue()) * .5;
		
		if (navigationController.CurrentNavigationState() != NavigationState.ReturningToOrgin) {
			double costFromNextCell = this.GetWeightedCostToOrigin(new Coordinate(nextCell.getXCoordinate(), nextCell.getYCoordinate()));
			
			//this probably works but depends upon navigation to move back to the current space to be accurate
			double batteryLifeNeededToGetHomeFromNextSquare = costFromNextCell + nextMoveNavCost; 
			if ((power.GetBatteryLevel() - nextMoveNavCost) <= batteryLifeNeededToGetHomeFromNextSquare) {
				navigationController.returnToOrigin();
				return false;
				
			}
		}
		
		if(canWeMoveInThisDirection) {
			
			if(power.RequestEnergy(nextMoveNavCost) == false) {
				throw new RuntimeException("We've run out of battery life!");
			}
			
			//update our stats
			analytics.powerUsed+=nextMoveNavCost;
			
		}

		return canWeMoveInThisDirection;
	}


	private boolean checkNavigationObstacles(SensorCell currentCell,
			SensorCell nextCell) {
		
		if(currentCell == null || nextCell == null) {
			return false;
		}
		
		//determine direction
		int xDifference = currentCell.getXCoordinate() - nextCell.getXCoordinate();
		int yDifference = currentCell.getYCoordinate() - nextCell.getYCoordinate();
		
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


	@Override
	public int GetWeightedCostToOrigin(Coordinate fromCoordinate) {
		
		
		class OriginWeightTracker implements INavigationObserver {
			
			private int weight;
			
			@Override
			public void didNavigate(Coordinate navigatedTo) {
				
				try	{
					//weight++;
					SensorCell sc = sensor.GetSensorDataForCoordinate(navigatedTo.getX(), navigatedTo.getY());
					//System.out.println("Adding " + sc.getXCoordinate() + ", " + sc.getYCoordinate() + " " + sc.getFloorType() + " CW: " + weight);
					weight += sc.getFloorType().GetValue();
					//System.out.println("Current Weight after add: " + weight);
				} catch(Exception e) {
					
				}
				
			}
			
			
			public int getTrackedWeight() {
				//System.out.println("Returning Weight: " + weight);
				return weight;
				
			}
			
		}
		
		class SimpleNavigationChecker implements INavigationChecker {

			private INavigator referencedNavigator;
			
			public SimpleNavigationChecker(INavigator navigator) {
				
				referencedNavigator = navigator;
				
			}
			
			@Override
			public Boolean CheckCoordinate(Coordinate coordinate) {
				try	{
					//weight++;
					SensorCell navigatingFrom = sensor.GetSensorDataForCoordinate(referencedNavigator.CurrentLocation().getX(), referencedNavigator.CurrentLocation().getY());
					SensorCell navigatingTo = sensor.GetSensorDataForCoordinate(coordinate.getX(), coordinate.getY());
					return checkNavigationObstacles(
							navigatingFrom, navigatingTo);
					 
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				return false;
			}

			@Override
			public int GetWeightedCostToOrigin(Coordinate fromCoordinate) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			
			
		}
		
		INavigator childNavigator = new NavigationController();
		
		childNavigator.SetDestinationPoint(fromCoordinate);
		childNavigator.MoveToDestination();
		
		
		OriginWeightTracker tracker = new OriginWeightTracker();
		
		childNavigator.addNavigationObserver(tracker);
		childNavigator.SetNavigationChecker(new SimpleNavigationChecker(childNavigator));
		childNavigator.returnToOrigin();
		
		return tracker.getTrackedWeight();
	}
	
	class CleanSweepAnalytics {
		
		public int dirtSwept;
		public int timesRecharged;
		public int spacesMoves;
		public double powerUsed;
		public int spacesRoamed;
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Dirt Swept = " + this.dirtSwept + "\r\n");
			sb.append("Spaces Moved = " + this.spacesMoves + "\r\n");
			sb.append("Spaces Roamed = " + this.spacesRoamed + "\r\n");
			sb.append("Power Used = " + this.powerUsed + "\r\n");
			sb.append("Times Recharged = " + this.timesRecharged + "\r\n");
			return sb.toString();
			
			
		}
		
	}
	
	
}
