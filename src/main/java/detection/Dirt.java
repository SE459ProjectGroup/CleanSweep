package main.java.detection;

import main.java.Navigation.Coordinate;
import main.java.Sensor.XMLParser.ParseXML;

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
