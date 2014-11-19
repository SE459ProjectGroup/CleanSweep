package se459.team2.CleanSweep.Sensor;

public class SensorCell {
	
	
	private int xCoordinate;
	
	public int getXCoordinate() {
		return xCoordinate;
	}

	public void setXCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	private int yCoordinate;
	
	public int getYCoordinate() {
		return yCoordinate;
	}

	public void setYCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	/*
	 * The floor type of the cell. bare floor, low carpet, high carpet
	 */
	private FloorType floorType;

	public FloorType getFloorType() {
		return floorType;
	}

	public void setFloorType(FloorType floorType) {
		this.floorType = floorType;
	}
	
	/*
	 * the amount of dirt present in the cell.
	 * Note: this value is mutable
	 */
	private int dirtAmount;

	protected int getDirtAmount() {
		return dirtAmount;
	}

	public void setDirtAmount(int dirtAmount) {
		this.dirtAmount = dirtAmount;
	}
	
	/**
	 * Check if the cell has any dirt in it
	 * @return
	 */
	public boolean hasDirt() {
		return (dirtAmount > 0);
	}
	
	public void dirtCollected() {
		
		if (dirtAmount > 0) {
			dirtAmount--;
		}
		
	}
	
	/*
	 * Value indicating the navigatability of the cell to our right(x + 1) 
	 */
	private Navigatable rightNavigatableType;
	
	public Navigatable getRightNavigatableType() {
		return rightNavigatableType;
	}

	public void setRightNavigatableType(Navigatable rightNavigatableType) {
		this.rightNavigatableType = rightNavigatableType;
	}
	
	/*
	 * Value indicating the navigatability of the cell to our left(x - 1) 
	 */
	private Navigatable leftNavigatableType;
	
	public Navigatable getLeftNavigatableType() {
		return leftNavigatableType;
	}

	public void setLeftNavigatableType(Navigatable leftNavigatableType) {
		this.leftNavigatableType = leftNavigatableType;
	}

	/*
	 * Value indicating the navigatability of the cell above(y + 1) 
	 */
	private Navigatable topNavigatableType;

	public Navigatable getTopNavigatableType() {
		return topNavigatableType;
	}

	public void setTopNavigatableType(Navigatable topNavigatableType) {
		this.topNavigatableType = topNavigatableType;
	}

	/*
	 * Value indicating the navigatability of the cell above(y - 1) 
	 */
	private Navigatable bottomNavigatableType;

	public Navigatable getBottomNavigatableType() {
		return bottomNavigatableType;
	}

	public void setBottomNavigatableType(Navigatable bottomNavigatableType) {
		this.bottomNavigatableType = bottomNavigatableType;
	}
	
	
	private boolean isChargingStation;
	
	public boolean isChargingStation() {
		return isChargingStation;
	}

	public void setChargingStation(boolean isChargingStation) {
		this.isChargingStation = isChargingStation;
	}

	public void setNavigatableDataFromPathsString(String paths) {
		
		if (paths.length() != 4) {
			throw new RuntimeException("Path string length must be 4.");
		}
		if(paths.matches("[0-9]+") == false) {
			throw new RuntimeException("Path string must only contain numeric characters.");
		}
		//for each direction, iterate once and try to grab the matching index from chars
		for(int i = 0; i < 4; i++) {
			
			Integer intNavigatableValue = Character.getNumericValue(paths.charAt(i));
			Navigatable navType = Navigatable.FromInt(intNavigatableValue);
			switch (i) {
				case 0:
					this.setRightNavigatableType(navType);
					break;
				case 1:
					this.setLeftNavigatableType(navType);
					break;
				case 2:
					this.setTopNavigatableType(navType);
					break;
				case 3:
					this.setBottomNavigatableType(navType);
					break;
				default:
					break;
			
			}
			
		}
		
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Location: X = " + this.getXCoordinate() + ", Y = " + this.getYCoordinate() + "\n");
		
		sb.append("Floor Type = " + this.getFloorType() + "\n");
		sb.append("Is Charging Station? = " + this.isChargingStation() + "\n");
		
		sb.append("Dirt Amount = " + this.getDirtAmount() + "\n");
		sb.append("Navigation Data: \n");
		sb.append("\t" + "Left Navigation Type = " + this.getLeftNavigatableType() + "\n");
		sb.append("\t" + "Top Navigation Type = " + this.getTopNavigatableType() + "\n");
		sb.append("\t" + "Right Navigation Type = " + this.getRightNavigatableType() + "\n");
		sb.append("\t" + "Bottom Navigation Type = " + this.getBottomNavigatableType() + "\n");
		
		
		return sb.toString();
		
	}
	
	
}
