package main.java.Navigation;

import java.util.ArrayList;
import java.util.List;

public class NavigationController implements INavigator {
	
	Coordinate currentLocation;
	
	private Coordinate previousLocation;
	
	NavigationState navState = NavigationState.Stopped;

	
	
	@Override
	public boolean MoveTo(Coordinate c) {
		System.out.println("MoveTo: Moving to " + c.toString() + " from " + this.CurrentLocation());
		int difX = Math.abs(c.getX() - this.currentLocation.getX());
		int difY = Math.abs(c.getY() - this.currentLocation.getY());
		if ((difX + difY) > 1 ) {
			throw new RuntimeException("You cannot navigate more than one space from our current location.");	
		}
		
		if (this.navChecker != null) {
			
			boolean res = this.navChecker.CheckCoordinate(c);
			if(res == false) { 
				System.out.println("MoveTo: Cannot Move To Location");
				return false; 
			}
		}
		System.out.println("MoveTo: Moving to accepted");
		this.previousLocation = this.currentLocation;
		this.currentLocation = c;
		
		this.notifyObservers();
		
		return true;
	}



	@Override
	public Coordinate CurrentLocation() {
		return this.currentLocation;
	}

	@Override
	public Coordinate PreviousLocation() {
		return this.previousLocation;
	}
	
	public NavigationController() {
		this.currentLocation = new Coordinate(0,0);
	}

//	@Override
//	public void BeginAutoNavigation() {
//		int maxMoves = 5;
//		int currentMoves = 0;
//		this.navState = NavigationState.Navigating;
//		
//		while(this.navState == NavigationState.Navigating && currentMoves < maxMoves) {
//			
//			if(this.MoveTo(new Coordinate(this.CurrentLocation().getX(), this.CurrentLocation().getY() + 1 )) == false) {
//				
//				this.navState = NavigationState.Stopped;
//			}
//			currentMoves++;
//		}
//		
//	}

	@Override
	public void StopAutoNavigation() {
		
		this.navState = NavigationState.Stopped;
		
	}
	
	Coordinate destinationPoint;

	@Override
	public void SetDestinationPoint(Coordinate c) {
		this.destinationPoint = c;
		
	}

	@Override
	public Coordinate GetDestinationPoint() {
		return this.destinationPoint;
	}

	@Override
	public void MoveToDestination() {
		
		int xDelta, yDelta;
		this.navState = NavigationState.Navigating;
		
		while (this.navState == NavigationState.Navigating && this.CurrentLocation().equals(this.GetDestinationPoint()) == false) {
			xDelta = this.CurrentLocation().getX() - this.GetDestinationPoint().getX();
			yDelta = this.CurrentLocation().getY() - this.GetDestinationPoint().getY();
			if (xDelta != 0 ) {
				
				if(this.MoveTo(new Coordinate(this.CurrentLocation().getX() + ((xDelta > 0)? -1:1) ,this.CurrentLocation().getY())) == false) {
					//we couldnt move horizontally.
					int movedUpCount = 0;
					boolean movedHorizontally = false;
					boolean failedToMoveVertically = false;
					while(movedUpCount < 5 && movedHorizontally == false && failedToMoveVertically == false) {
						if(this.MoveTo(new Coordinate(this.CurrentLocation().getX(), this.CurrentLocation().getY() + 1))) {
							movedUpCount++;
							movedHorizontally = this.MoveTo(new Coordinate(this.CurrentLocation().getX() + ((xDelta > 0)? -1:1) , this.CurrentLocation().getY()));
						} else {
							
							failedToMoveVertically = true;
							
						}
					}
				}
				
			} else {
				
				
				if (yDelta != 0) {
					if(this.MoveTo(new Coordinate(this.CurrentLocation().getX(),this.CurrentLocation().getY()  + ((yDelta > 0)? -1:1) )) == false) {
						//we couldnt move vertically
						//we couldnt move horizontally.
						int movedUpCount = 0;
						boolean movedVertically = false;
						boolean failedToMoveHorizontally = false;
						while(movedUpCount < 5 && movedVertically == false && failedToMoveHorizontally == false) {
							if(this.MoveTo(new Coordinate(this.CurrentLocation().getX() + 1, this.CurrentLocation().getY()))) {
								movedUpCount++;
								movedVertically = this.MoveTo(new Coordinate(this.CurrentLocation().getX(), this.CurrentLocation().getY() + ((yDelta > 0)? -1:1) ));
							} else {
								
								failedToMoveHorizontally = true;
								
							}
						}
					}
				}
			}
			
		}
		
		this.navState = NavigationState.Stopped;
		
	}

	INavigationChecker navChecker;
	
	@Override
	public void SetNavigationChecker(INavigationChecker inc) {
		this.navChecker = inc;
		
	}

	
	List<INavigationObserver> navigationObservers = new ArrayList<INavigationObserver>();
	
	@Override
	public void addNavigationObserver(INavigationObserver observer) {
		if (observer != null ) {
			
			navigationObservers.add(observer);
			
		}
		
	}
	
	private void notifyObservers() {
		
		for(INavigationObserver obs: this.navigationObservers) {
			try	{
				
				obs.didNavigate(currentLocation);
				
			} catch(Exception e) {
				System.out.println("INavigationObserver didNavigate Exception: " + e.getMessage());
				
			}
			
			
		}
		
		
	}



	@Override
	public void returnToOrigin() {
		
		this.SetDestinationPoint(new Coordinate(0,0));
		
		this.MoveToDestination();
		
	}



	@Override
	public int GetWeightedCostToOrigin() {
		
		
		class OriginWeightTracker implements INavigationObserver {
			
			private int weight;
			
			@Override
			public void didNavigate(Coordinate navigatedTo) {
				
				weight++;
				
			}
			
			
			public int getTrackedWeight() {
				
				return weight;
				
			}
			
		}
		
		INavigator childNavigator = new NavigationController();
		
		childNavigator.SetDestinationPoint(this.CurrentLocation());
		childNavigator.MoveToDestination();
		
		
		OriginWeightTracker tracker = new OriginWeightTracker();
		
		childNavigator.addNavigationObserver(tracker);
		
		childNavigator.returnToOrigin();
		
		return tracker.getTrackedWeight();
	}



	@Override
	public void roam(int count) {
		
		
		List<Coordinate> possibleMoves = null;
		boolean didMove;
		
		for(int i = 0; i < count; i++) {
			didMove = false;
			//load the next possible moves
			possibleMoves = new ArrayList<Coordinate>() {{
				add(new Coordinate(currentLocation.getX(),currentLocation.getY() + 1));
				add(new Coordinate(currentLocation.getX() + 1,currentLocation.getY()));
				add(new Coordinate(currentLocation.getX(),currentLocation.getY() - 1));
				add(new Coordinate(currentLocation.getX() - 1,currentLocation.getY()));			
			}};
			
			try {
				
				possibleMoves.remove(this.PreviousLocation());
				
			} catch(Exception e) {
				
				
			}
			
			for(Coordinate test : possibleMoves) {
				if(this.MoveTo(test)) {
					didMove = true;
					break;
				}
			}
			
			if (didMove == false) {
				//we couldnt move to any of the possible moves, back it up!
				this.MoveTo(this.PreviousLocation());
				
			}
		}
		
	}
	
}
