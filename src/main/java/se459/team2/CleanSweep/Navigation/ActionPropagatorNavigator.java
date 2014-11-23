package se459.team2.CleanSweep.Navigation;

import java.util.ArrayList;
import java.util.List;

public class ActionPropagatorNavigator extends NavigationController implements INavigationObserver, INavigator {

	private IActionPerformer actionPerformer;
	
	public ActionPropagatorNavigator() {
		
		this.addNavigationObserver(this);
		
	}

	
	/**
	 * @return the actionPerformer
	 */
	public IActionPerformer getActionPerformer() {
		return actionPerformer;
	}

	/**
	 * @param actionPerformer the actionPerformer to set
	 */
	public void setActionPerformer(
			IActionPerformer actionPerformer) {
		this.actionPerformer = actionPerformer;
	}



	@Override
	public void didNavigate(Coordinate navigatedTo) {
		
		if(this.navState != NavigationState.ReturningToOrgin && actionPerformer != null) {
			if(actionPerformer.performAction(navigatedTo)) {
				//add to some list of done objects
				if (completedCoordinates.contains(navigatedTo) == false)  {
					completedCoordinates.add(navigatedTo);
				}
				if (notCompletedCoordinates.contains(navigatedTo)) {
					notCompletedCoordinates.remove(navigatedTo);
				}
			}
		}
		//get all the possible moves
		NavigationCheckResult res = this.navChecker.CheckCoordinate(currentLocation);
		List<Coordinate> possibleMoves = GetMovesFromNavCheckResult(res);
		
		//compare the possible moves with our ocmpelted coordinates lis
		for(Coordinate c : possibleMoves) {
			if (completedCoordinates.contains(c) == false 
					&& notCompletedCoordinates.contains(c) == false 
					&& this.inaccessibleCoordinates.contains(c) == false) {
				//we havent performed an action of this coordinate, 
				notCompletedCoordinates.add(c);
			}
		}
		
		
	}


	private List<Coordinate> GetMovesFromNavCheckResult(
			NavigationCheckResult res) {
		List<Coordinate> possibleMoves = new ArrayList<Coordinate>();
		if (res.getCanMoveUp()) {
			possibleMoves.add(new Coordinate(this.currentLocation.getX(), this.currentLocation.getY() + 1));
		}
		if (res.getCanMoveDown()) {
			possibleMoves.add(new Coordinate(this.currentLocation.getX(), this.currentLocation.getY() - 1));
		}
		if (res.getCanMoveLeft()) {
			possibleMoves.add(new Coordinate(this.currentLocation.getX() - 1, this.currentLocation.getY()));
		}
		if (res.getCanMoveRight()) {
			possibleMoves.add(new Coordinate(this.currentLocation.getX() + 1, this.currentLocation.getY()));
		}
		return possibleMoves;
	}

	@Override
	public void roam(int count) {
	
		this.BeginCycle(count);
	
	}

	public void BeginCycle(int maxCount) {
		isNavigating = true;
		
		//dont forget about the orgin
		this.didNavigate(currentLocation);
		
		
		int count = 0;
		while(isNavigating == true && count < maxCount) {
			count++;
			//now loop
			if (this.notCompletedCoordinates.size() == 0) {
				this.returnToOrigin();
				isNavigating = false;
			} else {
				Coordinate d = this.notCompletedCoordinates.get(0);
				this.SetDestinationPoint(d);
				System.out.println("Moving to: " + this.GetDestinationPoint());
				if(this.MoveToDestination() == false) {
					this.returnToOrigin();
					this.SetDestinationPoint(d);
					if(this.MoveToDestination() == false) {
						this.inaccessibleCoordinates.add(d);
						this.notCompletedCoordinates.remove(d);
						
					}
					
					
				}
			}
			
			
		}
		
	}

	private boolean isNavigating = false;

	private List<Coordinate> notCompletedCoordinates = new ArrayList<Coordinate>();
	
	private List<Coordinate> inaccessibleCoordinates = new ArrayList<Coordinate>();
	
	private List<Coordinate> completedCoordinates = new ArrayList<Coordinate>();
	
	public List<Coordinate> GetCompletedCoordinates() {
		return completedCoordinates;
	}
	
	public List<Coordinate> GetInacessibleCoordinates() {
		return inaccessibleCoordinates;
	}
	
}
