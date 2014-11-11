package main.java.PowerManagement;

import main.java.Sensor.FloorType;

public class TempPowerManager implements IPowerManager {

	private final int maxBatteryLife = 50;
	
	private double batteryLife;
		double returnPower;	
	
	public TempPowerManager() {
		
		this.batteryLife = maxBatteryLife;
	
	}
	
	
	@Override
	public double GetBatteryLevel() {

		return batteryLife;
	}

	@Override
	public boolean RequestEnergy(double requestedEnergyAmount) {
		
		if (requestedEnergyAmount <= batteryLife) {
			batteryLife -= requestedEnergyAmount;
			return true;
			
		}
		return false;
	}


	@Override
	public void Charge() {
		batteryLife = maxBatteryLife;
		
	}

	//computes the cost of CLEANING the CURRENT location based on floor type
	@Override
	public double GetEnergyCostForDirtCollection(FloorType floorType) {
		double dirtCost;
		
		switch(floorType){
			case BareFloor:
				dirtCost = 1;
				break;
			case LowCarpet:
				dirtCost = 2;
				break;				
			case HighCarpet:
				dirtCost = 4;
				break;
			default:
				dirtCost = 1;
				break;		
		}
		return dirtCost;	
	}

	//computes the cost of MOVING to the NEXT location based on floor type
	@Override
	public double GetEnergyCostForNavigation(FloorType floorType) {
		double moveCost;
		
		switch(floorType){
			case BareFloor:
				moveCost = 1;
				break;
			case LowCarpet:
				moveCost = 2;
				break;				
			case HighCarpet:
				moveCost = 3;
				break;
			default:
				moveCost = 1;
				break;		
		}
		return moveCost;		
	}

	//computes the energy cost to MOVE TO & CLEAN CURRENT location
	@Override
	public double GetTotalEnergyCost(FloorType floorType, int dirtAmount) 
	{
		double totalCost;
		
		totalCost = this.GetEnergyCostForNavigation(floorType) + (this.GetEnergyCostForDirtCollection(floorType) * dirtAmount);

		return totalCost;
	}

	//keeps running count of power that was used for MOVEMENT ONLY
	//will be used to signal when it is time to return to charger
	@Override
	public double GetPowerUsedForMove(FloorType floorType) {
		
		returnPower += this.GetEnergyCostForNavigation(floorType);

		return returnPower;
	}
}
