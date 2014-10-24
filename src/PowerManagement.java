package powermanagement; 
 
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/** 
* 
* @author Joe 
*/ 
public class PowerManagement { 
 
  private static double maxDirtCap = 50;      //maximum dirt capacity value 
  public static double btryLvl = 1000;    //full battery level charge value 
  private static boolean emptyFlag = false;  //set Empty Me indicator to OFF

  private static final double btryPowerUsed = 0;   //battery power used since leaving station 
    
  public static final int bareFloorMoveClean = 1;   //energy cost to move and clean 
  public static final int lowCarpetMoveClean = 2;   //energy cost to move and clean 
  public static final int highCarpetMoveClean = 4;  //energy cost to move and clean 

  public static final int bareFloorMove = 1;  //bare floor value rating 
  public static final int lowCarpetMove = 2;  //low carpet value rating 
  public static final int highCarpetMove = 3; //high carpet value rating   
  
  private static double btryPowerLeft = 0;   //battery power remaining value 

  private static double dirtSwept = 0;        //amount of dirt picked up in sweeper 
 
  public static double ss = 2;
  public static double ds = 3;

  public static double charge;
  
/** 
* 
* @return 
*/ 
  public static double usePower() {
    System.out.println("The usePower method has started\n"); 
    System.out.println("The ss value is = " + ss); 
    System.out.println("The ds value is = " + ds); 
    
    charge = (ss + ds) / 2; 

    System.out.println("The charge value is = " + charge); 
    System.out.println("The usePower method has ended\n"); 

    return charge; 
  } 

/** 
* 

* @return 
*/
  public static double powerRemain() { 
    System.out.println("The powerRemain method has started\n"); 
    System.out.println("The charge value is = " + charge); 
    System.out.println("The IN btryPowerLeft value is = " + getBtryPowerLeft()); 
 
    setBtryPowerLeft(getBtryPowerLeft() - charge); 
 
    System.out.println("The OUT btryPowerLeft value is = " + getBtryPowerLeft()); 
    System.out.println("The powerRemain method has ended\n"); 
 
    return getBtryPowerLeft(); 
  } 

    /** 
    * 
    * @return 
    */
     public static double btryLvl() { 
        System.out.println("The btryLvl method has started\n"); 
        System.out.println("The charge value is = " + charge); 
        System.out.println("The btryPowerLeft value is = " + btryPowerLeft); 
 
          btryPowerLeft -= charge; 
 
         System.out.println("The btryLeft value is = " + btryPowerLeft); 
         System.out.println("The btryLvl method has ended\n"); 
 
         return btryPowerLeft; 
     } 
 
     /** 
     * 
     */ 
     public static void chargerReturn() {
         System.out.println("The chargerReturn method has started\n"); 
 
         System.out.println("The btryPowerLeft value is = " + btryPowerLeft); 
         System.out.println("The dirtSwept value is = " + dirtSwept); 
 
	//checks if dirt contents at max or if Power is HALF used or 
	//if Power Used is greater that Power Remaining will call for
        //returns to charging station
        if (dirtSwept == 50 || btryPowerUsed >= 500 || btryPowerUsed >= btryPowerLeft) { 
             backToChargeStation(); 
         } 
        
        if (dirtSwept < 50) {
         //   if (btryPowerLeft >
        }

        System.out.println("The chargerReturn method has ended\n"); 
    } 
 
    private static void backToChargeStation() { 
        System.out.println("The backToChargeStation method is started\n"); 
		resetSweep();
		
		
        System.out.println("The dirtSwept value is = " + getDirtSwept());
        System.out.println("The btryPowerLeft value is = " + getBtryPowerLeft());	
		
        System.out.println("The backToChargeStation method is ended\n"); 
    } 
 
    /**
     * will reset all values on sweeper to base state
     */
    private static void resetSweep() {
        System.out.println("The resetSweep method is started\n");
		//sets the Empty Me flag/indicator
		emptyFlag = true;		
    	emptyFlag();
        setDirtSwept(0);    //resets dirt swept to 0
        setBtryPowerLeft(1000);   //resets remaining battery power to max

        System.out.println("The dirtSwept value is = " + getDirtSwept());
        System.out.println("The btryPowerLeft value is = " + getBtryPowerLeft());

		//sets the Empty Me flag/indicator
		emptyFlag = false;
		resetFlag();
        System.out.println("The resetSweep method is ended\n");       
    }
	
     /**
     *
     */
    public static void chargestationCheck() {
        System.out.println("The chargestationCheck method has started\n");

        int cs = 0;
        int CScount = 0;

        if (cs == '1') {
            CScount++;      //tracks the number of charging stations
            chargeStation();
        }

        //if SECOND or MORE charging stations are detected, those locations 
        //are stored in a 2-D array and are sorted with the CLOSEST location
        //to the sweeper being in the FIRST position 
        if (CScount > '1') {
            System.out.println("The chargestationCheck method has ended\n");
        }
    }

    /**
     *  Reset Power/Dirt Content Values after Returning to Charging Station
     */
    public static void chargeStation() {
        System.out.println("The chargeStation method has started\n");



        System.out.println("The chargeStation method has ended\n");
    }

    
 private static void emptyFlag(){ 
  JOptionPane opt = new JOptionPane("EMPTY ME", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}); // no buttons
 
  final JDialog dlg = opt.createDialog("ALERT");
  new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Thread.sleep(10000);
              dlg.dispose();
            }
            catch ( Throwable th )
            {
        //    tracea("setValidComboIndex(): alert  " + cThrowable.getStackTrace(th));
            }
          }
        }).start();
		
  dlg.setVisible(true);
}

 private static void resetFlag(){ 
	 JOptionPane opt = new JOptionPane("CONTENTS = 0\n BATTERY POWER = 1000\n POWER USED = 0\n POWER LEFT = 1000 ", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}); // no buttons
 
  final JDialog dlg = opt.createDialog("ALERT");
  new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Thread.sleep(10000);
              dlg.dispose();
            }
            catch ( Throwable th )
            {
        //    tracea("setValidComboIndex(): alert  " + cThrowable.getStackTrace(th));
            }
          }
        }).start();
		
  dlg.setVisible(true);
}

/** 
* @return the maxDirtCap 
*/ 
  public static double getMaxDirtCap() { 
    return maxDirtCap; 
  } 
 
    /** 
    * @param aMaxDirtCap the maxDirtCap to set 
    */ 
    public static void setMaxDirtCap(int aMaxDirtCap) { 
        maxDirtCap = aMaxDirtCap; 
    } 
 
     /** 
      * @return the dirtSwept 
      */ 
     public static double getDirtSwept() { 
         return dirtSwept; 
     } 
 
 
     /** 
      * @param aDirtSwept the dirtSwept to set 
      */ 
     public static void setDirtSwept(int aDirtSwept) { 
         dirtSwept = aDirtSwept; 
     } 
 
 
     /** 
      * @return the batteryLvl 
      */ 
     public static double getBatteryLvl() { 
         return btryLvl; 
     } 
 

     /** 
      * @param aBatteryLvl the batteryLvl to set 
      */ 
     public static void setBatteryLvl(double aBatteryLvl) { 
         btryLvl = aBatteryLvl; 
     } 
 
 
     /** 
      * @return the btryPowerLeft 
      */ 
     public static double getBtryPowerLeft() { 
         return btryPowerLeft; 
     } 
 
 
     /** 
      * @param aBtryPowerLeft the btryPowerLeft to set 
      */ 
     public static void setBtryPowerLeft(double aBtryPowerLeft) { 
         btryPowerLeft = aBtryPowerLeft; 
     } 
 }
