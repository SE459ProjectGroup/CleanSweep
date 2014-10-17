package Sensor;

import java.util.ArrayList;

import Navigation.Coordinate;
import Navigation.INavigator;
import Surface.SurfaceType;

public class Sensor implements iSensor{
	
	/*
	 * to do:
	 *  xml interface to get each cell and floor 
	 *  read xml file
	 * 	
	 */

	
	public int getSurfaceType(INavigator n) {
			
		while (n.CurrentLocation() != null)  //clean sweep is moving
		{
		return SurfaceType.getSurfaceType();
		}
		return 0;
	}

	public int getDirtAmount(cell x) {
		int totalDirt;
		
		for(int i =0; i<x ; i++)  //for each cell in the xml file 
		{
			int dirtAmount= x.getDirtSenor();
			totalDirt += dirtAmount;
		
		}
		
		return totalDirt; 
	}



	

}
