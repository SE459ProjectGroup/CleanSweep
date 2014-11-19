package se459.team2.CleanSweep.PowerManagement;

import se459.team2.CleanSweep.Sensor.FloorType;

public interface IPowerManager {

	public double GetBatteryLevel();
	
	public boolean RequestEnergy(double requestedEnergyAmount);
	
	public void Charge();
	
	//once the above is complete, we should find more graceful ways of determining
	//how much energy is needed
	
	public double GetEnergyCostForDirtCollection(FloorType floorType);
	
	public double GetEnergyCostForNavigation(FloorType floorType);

	public double GetTotalEnergyCost(FloorType floorType, int dirtAmount);	

	public double GetPowerUsedForMove(FloorType floorType);
	
}
