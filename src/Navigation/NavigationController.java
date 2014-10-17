package Navigation;

public class NavigationController implements INavigator {
	
	Coordinate currentLocation;
	
	NavigationState navState = NavigationState.Stopped;
	
	@Override
	public void MoveTo(Coordinate c) {
		int difX = c.getX() - this.currentLocation.getX();
		int difY = c.getY() - this.currentLocation.getY();
		if (Math.abs(difX) > 1 || Math.abs(difY) > 1) {
			throw new RuntimeException("You cannot navigate more than one space from our current location.");	
		}
		this.currentLocation = c;
		
	}

	@Override
	public Coordinate CurrentLocation() {
		return this.currentLocation;
	}

	public NavigationController() {
		this.currentLocation = new Coordinate(0,0);
	}

	@Override
	public void BeginAutoNavigation() {
		int maxMoves = 5;
		int currentMoves = 0;
		this.navState = NavigationState.Navigating;
		
		while(this.navState == NavigationState.Navigating && currentMoves < maxMoves) {
			
			this.MoveTo(new Coordinate(this.CurrentLocation().getX(), this.CurrentLocation().getY() + 1 ));
			currentMoves++;
		}
		
	}

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
		while (this.CurrentLocation().equals(this.GetDestinationPoint()) == false) {
			xDelta = this.CurrentLocation().getX() - this.GetDestinationPoint().getX();
			yDelta = this.CurrentLocation().getY() - this.GetDestinationPoint().getY();
			if (xDelta != 0 ) {
				
				this.MoveTo(new Coordinate(this.CurrentLocation().getX() + ((xDelta > 0)? -1:1) ,this.CurrentLocation().getY()));
				
			} else {
				
				
				if (yDelta != 0) {
					this.MoveTo(new Coordinate(this.CurrentLocation().getX(),this.CurrentLocation().getY()  + ((yDelta > 0)? -1:1) ));
				}
			}
			
		}
		
	}
	
	
	
}
