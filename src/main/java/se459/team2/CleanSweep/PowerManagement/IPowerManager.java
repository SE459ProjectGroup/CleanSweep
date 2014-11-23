package se459.team2.CleanSweep.PowerManagement;

public interface IPowerManager {

	public double GetBatteryLevel();
	
	public boolean RequestEnergy(double requestedEnergyAmount);
	
	public void Charge();
	
}
