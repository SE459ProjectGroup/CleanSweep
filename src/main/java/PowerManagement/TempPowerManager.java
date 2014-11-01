package main.java.PowerManagement;

public class TempPowerManager implements IPowerManager {

	private final int maxBatteryLife = 50;
	
	private double batteryLife;
	
	
	public TempPowerManager() {
		
		this.batteryLife = maxBatteryLife;
		
	}
	
	
	@Override
	public double GetBatteryLevel() {
		// TODO Auto-generated method stub
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
