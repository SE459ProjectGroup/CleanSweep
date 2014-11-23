package se459.team2.CleanSweep.PowerManagementTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se459.team2.CleanSweep.PowerManagement.IPowerManager;

import static org.junit.Assert.*;

/**
 *
 * @author Joe
 */


public class IPowerManagerTest {
	
	public IPowerManagerTest() {
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
	 * Test of GetBatteryLevel method, of class IPowerManager.
	 */
	@Test
	public void testGetBatteryLevel() {
		System.out.println("GetBatteryLevel");
		IPowerManager instance = new IPowerManagerImpl();
		double expResult = 0.0;
		double result = instance.GetBatteryLevel();
		assertEquals(expResult, result, 0.0);
	}

	/**
	 * Test of RequestEnergy method, of class IPowerManager.
	 */
	@Test
	public void testRequestEnergy() {
		System.out.println("RequestEnergy");
		double requestedEnergyAmount = 0.0;
		IPowerManager instance = new IPowerManagerImpl();
		boolean expResult = false;
		boolean result = instance.RequestEnergy(requestedEnergyAmount);
		assertEquals(expResult, result);
	}

	/**
	 * Test of Charge method, of class IPowerManager.
	 */
	@Test
	public void testCharge() {
		System.out.println("Charge");
		IPowerManager instance = new IPowerManagerImpl();
		instance.Charge();
	}

	public class IPowerManagerImpl implements IPowerManager {

		@Override
		public double GetBatteryLevel() {
			return 0.0;
		}

		@Override
		public boolean RequestEnergy(double requestedEnergyAmount) {
			return false;
		}

		@Override
		public void Charge() {
		}
	}
}
