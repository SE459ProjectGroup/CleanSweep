////package powermanagement; 
//package main.java.PowerManagement;
//
///** 
//* 
//* @author Joe 
//*/ 
//public class PowerManagement { 
//
//	private static double maxDirtCap = 50;      //maximum dirt capacity value 
//	public static double btryLvl = 1000;    //full battery level charge value 
//
//	private static final double btryPowerUsed = 0;   //battery power used since leaving station 
//
//	//public static final int bareFloorMoveClean = 1;   //energy cost to move and clean 
//	//public static final int lowCarpetMoveClean = 2;   //energy cost to move and clean 
//	//public static final int highCarpetMoveClean = 4;  //energy cost to move and clean 
//
//	//public static final int bareFloorMove = 1;  //bare floor value rating 
//	//public static final int lowCarpetMove = 2;  //low carpet value rating 
//	//public static final int highCarpetMove = 3; //high carpet value rating   
//
//	private static double btryPowerLeft = 0;   //battery power remaining value 
//
//	private static double dirtSwept = 0;        //amount of dirt picked up in sweeper 
//
//	//temporary TEST Values
//	public static double currentCell = 2;
//	public static double nextCell =  3;
//
//	public static double charge;  //computed cost to move and clean an area
//
//	/** 
//	* Computes the amount of power that the moving and cleaning will require for
//	* the location
//	* @return 
//	*/ 
//	public static double moveCost() {
//		System.out.println("The moveCost method has started\n"); 
//		System.out.println("The currentCell value is = " + currentCell); 
//		System.out.println("The nextCell value is = " + nextCell); 
//		System.out.println("The cleanCost value is = " + dirtSensor); 		
//
//		//locA_ss is the MOVE-FROM coordinate
//		//locB_ss is the MOVE-TO coordinate
//        	double locA_ss, locB_ss, cleanCost;
//
//		locA_ss = currentCell;
//		locB_ss = nextCell;
//		cleanCost = dirtSensor;
//
//		charge = ((locA_ss + locB_ss) / 2) + cleanCost;
//		System.out.println("The charge value is = " + charge); 
//		System.out.println("The moveCost method has ended\n"); 
//
//		return charge; 
//	} 
//
//	public static void chargeStation() {
//		setDirtSwept(0);   	 //Sets dirt swept to 0
//		setBtryPowerLeft(1000);   //Sets battery power to max
//	}
//	
//	public static double powerRemain() { 
//		btryPowerLeft -= charge; 
//		return btryPowerLeft; 
//	} 
//
//	private static void atChargeStation() { 
//		resetSweep();
//	} 
//
//	public static double moveCostHome() {
//		System.out.println("The moveCostHome method has started\n"); 
//		System.out.println("The currentCell value is = " + currentCell); 
//		System.out.println("The nextCell value is = " + nextCell); 
//
//		//locA_ss is the MOVE-FROM coordinate
//		//locB_ss is the MOVE-TO coordinate
//        	double locA_ss, locB_ss;
//
//		locA_ss = currentCell;
//		locB_ss = nextCell;
//
//		charge = (locA_ss + locB_ss) / 2;
//		System.out.println("The charge value is = " + charge); 
//		System.out.println("The moveCostHome method has ended\n"); 
//
//		return charge; 
//	} 
//	
//	/** 
//	* 
//	*/ 
//	public static void return2Charger() {
//		System.out.println("The return2Charger method has started\n"); 
//
//		System.out.println("WILL NEED TO HAVE NAVIGATION PATH TO GET BACK TO CHARGING STATION"); 
//
//		System.out.println("The return2Charger method has ended\n"); 
//	} 
//
//	public static void powerCheck() {
//		if (dirtSwept == maxDirtCap || (btryPowerUsed >= btryPowerLeft)) { 
//			return2Charger(); 
//		}
//
//		if (dirtSwept < maxDirtCap || (btryPowerUsed < btryPowerLeft)) {
//				
//		}	
//	}
//
//	private static void resetSweep() {
//		emptyFlag();
//
//		setDirtSwept(0);    //resets dirt swept to 0
//		setBtryPowerLeft(1000);   //resets remaining battery power to max
//
//		resetFlag();
//	}
//
//	 /**
//	 *
//	 */
//	public static void chargeStationCheck() {
//		System.out.println("The chargestationCheck method has started\n");
//
//		int cs = 0;
//		int CScount = 0;
//
//		if (cs == '1') {
//			CScount++;      //tracks the number of charging stations
//			chargeStation();
//		}
//
//		//if SECOND or MORE charging stations are detected, those locations 
//		//are stored in a 2-D array and are sorted with the CLOSEST location
//		//to the sweeper being in the FIRST position 
//		if (CScount > '1') {
//			System.out.println("The chargestationCheck method has ended\n");
//		}
//	}
//
//	private static void emptyFlag(){ 
//		System.out.println("\nEMPTY ME!!\n");
//	}
//	
//	private static void resetFlag(){
//		System.out.println("\nThe Dirt Contents -->" + getDirtSwept());
//		System.out.println("The Battery Power Left --> " + getBtryPowerLeft());
//	}
//
//	/** 
//	* @return the maxDirtCap 
//	*/ 
//	public static double getMaxDirtCap() { 
//		return maxDirtCap; 
//	} 
//
//	/** 
//	* @param aMaxDirtCap the maxDirtCap to set 
//	*/ 
//	public static void setMaxDirtCap(int aMaxDirtCap) { 
//		maxDirtCap = aMaxDirtCap; 
//	} 
//
//	 /** 
//	  * @return the dirtSwept 
//	  */ 
//	 public static double getDirtSwept() { 
//		 return dirtSwept; 
//	 } 
//
//	 /** 
//	  * @param aDirtSwept the dirtSwept to set 
//	  */ 
//	 public static void setDirtSwept(int aDirtSwept) { 
//		 dirtSwept = aDirtSwept; 
//	 } 
//
//	 /** 
//	  * @return the batteryLvl 
//	  */ 
//	 public static double getBatteryLvl() { 
//		 return btryLvl; 
//	 } 
//
//	 /** 
//	  * @param aBatteryLvl the batteryLvl to set 
//	  */ 
//	 public static void setBatteryLvl(double aBatteryLvl) { 
//		 btryLvl = aBatteryLvl; 
//	 } 
//
//	 /** 
//	  * @return the btryPowerLeft 
//	  */ 
//	 public static double getBtryPowerLeft() { 
//		 return btryPowerLeft; 
//	 } 
//
//	 /** 
//	  * @param aBtryPowerLeft the btryPowerLeft to set 
//	  */ 
//	 public static void setBtryPowerLeft(double aBtryPowerLeft) { 
//		 btryPowerLeft = aBtryPowerLeft; 
//	 } 
//
//	/**
//	 * @param args the command line arguments
//	 */
///*	public static void main(String[] args) {
//		// TODO code application logic here
//	chargeStation();
//	return2Charger();
//	moveCost();
//	powerRemain();
//	atChargeStation();
//
//	}*/
//}
