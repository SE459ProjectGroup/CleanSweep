package se459.team2.CleanSweep.Sensor;

public enum Navigatable {
	Unknown(false),
	Open(true),
	Obstacle(false),
	Stairs(false);
	
	
	Navigatable(boolean canNavigateTo) {
		this.canMoveTo = canNavigateTo;
	}
	
	private final boolean canMoveTo;
	
	public boolean CanMoveTo() {
		return canMoveTo;
	}
	
	
	public static Navigatable FromInt(int i) {
		switch (i) {
		case 1:
			return Open;
		case 2:
			return Obstacle;
		case 3:
			return Stairs;
		default:
			return Navigatable.Unknown;
		}
	}
}
