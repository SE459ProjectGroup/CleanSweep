package main.java.Sensor;

import java.util.ArrayList;
import java.util.List;

import main.java.Sensor.XMLParser.ParseXML;

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
