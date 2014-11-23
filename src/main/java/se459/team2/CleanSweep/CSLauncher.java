package se459.team2.CleanSweep;

import se459.team2.CleanSweep.CleanSweep.CleanSweep;
import se459.team2.CleanSweep.DirtCollection.DirtCollection;


public class CSLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CleanSweep cs = new CleanSweep();
		
		try{
		//roam around the area for 10 spaces
			cs.getNavigationController().roam(1000);
		
		} catch(Exception e) {
			
			System.out.println(e);
		}
		System.out.println("**********************************");
		System.out.println("Roaming Complete! Statistics Below:");
		System.out.print(cs.getAnalytics());
	}

}
