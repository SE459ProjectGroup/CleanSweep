package main.java.detection;

import main.java.Navigation.Coordinate;
import main.java.parser.ParseXML;

public class Surface {
	
	int surf_type;
	String[] surface_types = { null, "bare_floor", "low_carpet", "high_carpet" };
	//static ParseXML parse;

	public int getSurf_type() {
		return surf_type;
	}

	public void setSurf_type(int surf_type) {
		this.surf_type = surf_type;
	}
	
	public Surface(int surface){
		this.surf_type = surface;
	}
	
	
	
	public String getSurfaceFromCoordinate(Coordinate coord){
		String surface = null;
		
		surf_type = ParseXML.getSurfaceTypeFromCoordinates(coord.getX(), coord.getY());
		surface = surface_types[surf_type];
		
		return surface;
	}
	

	
}
