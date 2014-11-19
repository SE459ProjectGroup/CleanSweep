package main.java.CleanSweep;



public class CSLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CleanSweep cs = new CleanSweep();
		
		try{
		//roam around the area for 10 spaces
		for (int i = 0; i < 10; i++) {
			cs.getNavigationController().roam(10);
		}
		}catch(Exception e) {
			
			System.out.println(e);
		}
		System.out.println("**********************************");
		System.out.println("Roaming Complete! Statistics Below:");
		System.out.print(cs.getAnalytics());
	}

}
