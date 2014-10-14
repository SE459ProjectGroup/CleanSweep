package main.java.Navigation;

public class NavigationController implements INavigator {

	Coordinate currentLocation;

	@Override
	public void NavigateTo(Coordinate c) {
		int difX = c.getX() - this.currentLocation.getX();
		int difY = c.getY() - this.currentLocation.getY();
		if (Math.abs(difX) > 1 || Math.abs(difY) > 1) {
			throw new RuntimeException(
					"You cannot navigate more than one space from our current location.");
		}
		this.currentLocation = c;

	}

	@Override
	public Coordinate CurrentLocation() {
		return this.currentLocation;
	}

	public NavigationController() {
		this.currentLocation = new Coordinate(0, 0);
	}

	@Override
	public void BeginAutoNavigation() {

	}

}
