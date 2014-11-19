package se459.team2.CleanSweep.Sensor;





public interface ISensorArray {
	
	/**
	 * interface  used to expose sensor functionality to a consumer.
	 * 
	 * 
	 * 
	 */
	SensorCell GetSensorDataForCoordinate(int xCoordinate, int yCoordinate);
	
	
	
	
	

}
