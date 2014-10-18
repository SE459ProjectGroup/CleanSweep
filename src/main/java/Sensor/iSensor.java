package main.java.Sensor;

import main.java.Navigation.INavigator;

public interface iSensor {
	
	/**
	 * interface  used to expose sensor functionality to a consumer
	 */
	
	public int getSurfaceType(INavigator n);
	
	public int getDirtAmount(cell x);

}
