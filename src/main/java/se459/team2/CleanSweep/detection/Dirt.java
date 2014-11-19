package se459.team2.CleanSweep.detection;

import se459.team2.CleanSweep.Navigation.Coordinate;
import se459.team2.CleanSweep.XMLParser.ParseXML;


public class Dirt {
	int dirt;
	
	public Dirt() {
		
	}

	public int getDirt() {
		return dirt;
	}

	public void setDirt(int dirt) {
		this.dirt = dirt;
	}
	
	
	
	public Dirt(int dirt_amount) {
		
		this.dirt = dirt_amount; 
	}

	public int  getDirtFromCoordinate(Coordinate coord){
		
		int d=0;
		
		d = ParseXML.getDirtFromCoordinates(coord.getX(), coord.getY());
		
		return d;		
	}
		
	
}
