package se459.team2.CleanSweep.Sensor;

import java.util.List;

public interface ISensorDataSource {

	List<SensorCell> LoadAllCellsFromSource();
	
}
