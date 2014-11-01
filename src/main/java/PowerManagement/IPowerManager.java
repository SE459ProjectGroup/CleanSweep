package main.java.PowerManagement;

public interface IPowerManager {

	public double GetBatteryLevel();
	
	public boolean RequestEnergy(double requestedEnergyAmount);
	
	public void Charge();
	
	//once the above is complete, we should find more graceful ways of determining
	//home much energy is needed
	
	//public int GetEnergyCostForDirtCollection(FloorType floorType);
	
	//public int GetEnergyCostForNavigation(FloorType floorType);
	
}
