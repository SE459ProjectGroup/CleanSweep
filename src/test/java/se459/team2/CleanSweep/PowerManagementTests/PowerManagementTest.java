package se459.team2.CleanSweep.PowerManagementTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se459.team2.CleanSweep.PowerManagement.PowerManagement;
import static org.junit.Assert.*;

public class PowerManagementTest {
	
	public PowerManagementTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of GetBatteryLevel method, of class PowerManagement.
	 */
	@Test
	public void testGetBatteryLevel() {
		System.out.println("GetBatteryLevel");
		PowerManagement instance = new PowerManagement();
		double expResult = 50.0;
		double result = instance.GetBatteryLevel();
		assertEquals(expResult, result, 50.0);
	}

	/**
	 * Test of RequestEnergy method, of class PowerManagement.
	 */
	@Test
	public void testRequestEnergy() {
		System.out.println("RequestEnergy");
		double requestedEnergyAmount = 0.0;
		PowerManagement instance = new PowerManagement();
		boolean expResult = true;
		boolean result = instance.RequestEnergy(requestedEnergyAmount);
		assertEquals(expResult, result);
	}

	/**
	 * Test of Charge method, of class PowerManagement.
	 */
	@Test
	public void testCharge() {
		System.out.println("Charge");
		PowerManagement instance = new PowerManagement();
		instance.Charge();
	}
}
