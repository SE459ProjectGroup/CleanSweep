package se459.team2.CleanSweep.Sensor;

import java.util.ArrayList;
import java.util.List;

import se459.team2.CleanSweep.XMLParser.ParseXML;


public class LocalSensorSource implements ISensorArray {

	
	private List<SensorCell> dataFromSensor = new ArrayList<SensorCell>();
	
	public LocalSensorSource() {
		
		ISensorDataSource ds = new ParseXML();
		dataFromSensor = ds.LoadAllCellsFromSource();
	}
	
	public LocalSensorSource(ISensorDataSource sensorSource) {
		
		dataFromSensor = sensorSource.LoadAllCellsFromSource();
	}
	
	
	@Override
	public SensorCell GetSensorDataForCoordinate(int xCoordinate,
			int yCoordinate) {
		for(SensorCell sc : dataFromSensor) {
			if (sc.getXCoordinate() == xCoordinate && sc.getYCoordinate() == yCoordinate) {
				return sc;
			}
		}
		return null;
	}
	
}
