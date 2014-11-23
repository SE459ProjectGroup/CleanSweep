package se459.team2.CleanSweep.PowerManagement;

public class PowerManagement implements IPowerManager {

	private final int maxBatteryLife = 50;
	
	private double batteryLife;
		double returnPower;	
	
	public PowerManagement() {
		
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
}

