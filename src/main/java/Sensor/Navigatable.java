package main.java.Sensor;

public enum Navigatable {
	Unknown,
	Open,
	Obstacle,
	Stairs;
	
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
