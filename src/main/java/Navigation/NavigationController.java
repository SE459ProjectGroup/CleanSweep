package main.java.Navigation;

import java.util.ArrayList;
import java.util.List;

public class NavigationController implements INavigator {
	
	Coordinate currentLocation;
	
	private Coordinate previousLocation;
	
	NavigationState navState = NavigationState.Stopped;

	
	
	@Override
	/*
	 * Move the Object to an adjacent cell.
	 * (non-Javadoc)
	 * @see main.java.Navigation.INavigator#MoveTo(main.java.Navigation.Coordinate)
	 */
	public boolean MoveTo(Coordinate c) {
		//System.out.println("MoveTo: Moving to " + c.toString() + " from " + this.CurrentLocation());
		int difX = Math.abs(c.getX() - this.currentLocation.getX());
		int difY = Math.abs(c.getY() - this.currentLocation.getY());
		if ((difX + difY) > 1 ) {
			throw new RuntimeException("You cannot navigate more than one space from our current location.");	
		}
		
		if (this.navChecker != null) {
			
			boolean res = this.navChecker.CheckCoordinate(c);
			if(res == false) { 
				//System.out.println("MoveTo: Cannot Move To Location");
				return false; 
			}
		}
		//System.out.println("MoveTo: Moving to accepted");
		this.previousLocation = this.currentLocation;
		this.currentLocation = c;
		
		this.notifyObservers();
		
		return true;
	}



	@Override
	/*
	 * The current coordinate the navigator is on
	 * (non-Javadoc)
	 * @see main.java.Navigation.INavigator#CurrentLocation()
	 */
	public Coordinate CurrentLocation() {
		return this.currentLocation;
	}

	/*
	 * the previous location of the Clean Sweep
	 * (non-Javadoc)
	 * @see main.java.Navigation.INavigator#PreviousLocation()
	 */
	@Override
	public Coordinate PreviousLocation() {
		return this.previousLocation;
	}
	
	/*
	 * Public default constructor
	 */
	public NavigationController() {
		this.currentLocation = new Coordinate(0,0);
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
		
		if (this.navState == NavigationState.Stopped) {
			
			this.navState = NavigationState.Navigating;
			
		}
		
		
		while ((this.navState == NavigationState.Navigating || this.navState == NavigationState.ReturningToOrgin)&& this.CurrentLocation().equals(this.GetDestinationPoint()) == false) {
			xDelta = this.CurrentLocation().getX() - this.GetDestinationPoint().getX();
			yDelta = this.CurrentLocation().getY() - this.GetDestinationPoint().getY();
			if (xDelta != 0 ) {
				
				if(this.MoveTo(new Coordinate(this.CurrentLocation().getX() + ((xDelta > 0)? -1:1) ,this.CurrentLocation().getY())) == false) {
					
					if (Math.abs(yDelta) > 0 && this.MoveTo(new Coordinate(this.CurrentLocation().getX(),this.CurrentLocation().getY()  + ((yDelta > 0)? -1:1) )) == true) {
						//we cant move horizontally, so let's try to get correct vertically and we can try again

					} else {
						
						//we couldnt move horizontally.
						int movedUpCount = 0;
						boolean movedHorizontally = false;
						boolean failedToMoveVertically = false;
						while(movedUpCount < 5 && movedHorizontally == false && failedToMoveVertically == false) {
							
							//if our destination point is lower, 
							
							if(this.MoveTo(new Coordinate(this.CurrentLocation().getX(), this.CurrentLocation().getY() + 1))) {
								movedUpCount++;
								movedHorizontally = this.MoveTo(new Coordinate(this.CurrentLocation().getX() + ((xDelta > 0)? -1:1) , this.CurrentLocation().getY()));
							} else {
								
								failedToMoveVertically = true;
								
							}
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
		if (this.navState == NavigationState.Navigating) {
			
			this.navState = NavigationState.Stopped;
			
		}
		
		
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
		
		this.navState = NavigationState.ReturningToOrgin;
		
		this.SetDestinationPoint(new Coordinate(0,0));
		
		this.MoveToDestination();
		
		
	}

	@Override
	public void roam(int count) {
		
		
		List<Coordinate> possibleMoves = null;
		boolean didMove;
		
		this.navState = NavigationState.Navigating;
		
		for(int i = 0; (i < count && this.navState != NavigationState.ReturningToOrgin ); i++) {
			didMove = false;
			//load the next possible moves
			possibleMoves = new ArrayList<Coordinate>();
			
			possibleMoves.add(new Coordinate(currentLocation.getX(),currentLocation.getY() + 1));
			possibleMoves.add(new Coordinate(currentLocation.getX() + 1,currentLocation.getY()));
			possibleMoves.add(new Coordinate(currentLocation.getX(),currentLocation.getY() - 1));
			possibleMoves.add(new Coordinate(currentLocation.getX() - 1,currentLocation.getY()));			
			
			try {
				
				possibleMoves.remove(this.PreviousLocation());
				
			} catch(Exception e) {
				
				
			}
				
				for(Coordinate test : possibleMoves) {
					if(this.MoveTo(test)) {
						didMove = true;
						break;
					} 
					
					if (this.navState == NavigationState.ReturningToOrgin || this.navState == NavigationState.Stopped) {
						break;
					}
				}
				
			
			
			if (didMove == false && this.navState != NavigationState.ReturningToOrgin) {
				//we couldnt move to any of the possible moves, back it up!
				this.MoveTo(this.PreviousLocation());
				
			}
		}
		
		this.navState = NavigationState.Stopped;
		
	}
	
	
	@Override
	public NavigationState CurrentNavigationState() {
		return this.navState;
	}
}
