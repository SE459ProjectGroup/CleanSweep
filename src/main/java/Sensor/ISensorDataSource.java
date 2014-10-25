package main.java.Sensor;

import java.util.List;

public interface ISensorDataSource {

	List<SensorCell> LoadAllCellsFromSource();
	
}
