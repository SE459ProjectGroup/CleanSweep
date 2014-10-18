package cleansweep;

/**
 *
 * @author Joe
 */
public class PowerManagement {

    /**
     *
     */
    public final static int bareFloorMove = 1;  //bare floor value rating

    /**
     *
     */
    public final static int lowCarpetMove = 2;  //low carpet value rating

    /**
     *
     */
    public final static int highCarpetMove = 3; //high carpet value rating

    private static int maxDirtCap = 50;      //maximum dirt capacity value
    private static int dirtSwept = 0;        //amount of dirt picked up in sweeper
    private static double batteryLvl = 1000;    //full battery level charge value
    private static double btryPowerLeft = 0;   //battery power remaining value

    /**
     *
     */
    public final static int bareFloorMoveClean = 1;   //energy cost to move and clean

    /**
     *
     */
    public final static int lowCarpetMoveClean = 2;   //energy cost to move and clean

    /**
     *
     */
    public final static int highCarpetMoveClean = 4;  //energy cost to move and clean

    /**
     *
     * @param ss
     * @param ds
     * @return
     */
    public static double usePower(int ss, int ds) {
        System.out.println("The usePower method has started\n");
        System.out.println("The ss value is = " + ss);
        System.out.println("The ds value is = " + ds);

        double charge, locA_ss, locB_ss;

        locA_ss = ss;
        locB_ss = ds;

        charge = (locA_ss + locB_ss) / 2;

        System.out.println("The charge value is = " + charge);
        System.out.println("The usePower method has ended\n");

        return charge;
    }

    /**
     *
     * @param charge
     * @return
     */
    public static double powerRemain(double charge) {
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
     * @param charge
     * @param btryPowerLeft
     * @return
     */
    public static double batteryLevel(double charge, int btryPowerLeft) {
        System.out.println("The batteryLevel method has started\n");
        System.out.println("The charge value is = " + charge);
        System.out.println("The btryPowerLeft value is = " + btryPowerLeft);

        double btryLeft, inCharge;

        btryLeft = btryPowerLeft;
        inCharge = charge;

        btryLeft -= inCharge;

        System.out.println("The btryLeft value is = " + btryLeft);
        System.out.println("The batteryLevel method has ended\n");

        return btryLeft;
    }

    /**
     *
     * @param btryPowerLeft
     * @param dirtSwept
     */
    public static void chargerReturn(int btryPowerLeft, int dirtSwept) {
        System.out.println("The chargerReturn method has started\n");

        System.out.println("The btryPowerLeft value is = " + btryPowerLeft);
        System.out.println("The dirtSwept value is = " + dirtSwept);

        if (dirtSwept == 50) {
            backToChargeStation();
        }

        System.out.println("The chargerReturn method has ended\n");
    }

    private static void backToChargeStation() {
        System.out.println("The backToChargeStation method is started\n");

        System.out.println("The backToChargeStation method is ended\n");
    }

    /**
     *
     */
    public static void activityLog() {
        System.out.println("The activityLog method has started\n");

        System.out.println("The activityLog method has ended\n");
    }

    /**
     *
     */
    public static void layoutFiles() {
        System.out.println("The layoutFiles method has started\n");

        System.out.println("The layoutFiles method has ended\n");
    }

    /**
     * @return the maxDirtCap
     */
    public static int getMaxDirtCap() {
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
    public static int getDirtSwept() {
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
        return batteryLvl;
    }

    /**
     * @param aBatteryLvl the batteryLvl to set
     */
    public static void setBatteryLvl(double aBatteryLvl) {
        batteryLvl = aBatteryLvl;
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
