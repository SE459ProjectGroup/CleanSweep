package main.java.Sensor;

public enum FloorType {
	
	BareFloor(1),
	LowCarpet(2),
	HighCarpe(4);
	
	private final int value;
	
	FloorType(int val) {
		
		value = val;
	}
	
	int GetValue() { return value; }
	
	public static FloorType FromInt(int floorTypeInt) {
		
		switch(floorTypeInt) {
			case 1:
				return BareFloor;
			case 2:
				return LowCarpet;
			case 4:
				return HighCarpe;
			default:
				return BareFloor;
		}
	}
}
