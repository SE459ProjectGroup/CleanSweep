package DiagnosticLoggerTests;

import DiagnosticLogger.DiagnosticLogger;
import DiagnosticLogger.DiagnosticLogger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiagnosticLoggerTest {
	
	public DiagnosticLoggerTest() {
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

	@Test
	public void testGetCurrentDateTime() {
		System.out.println("getCurrentDateTime");
		DiagnosticLogger instance = new DiagnosticLogger();
		String expResult = instance.getCurrentDateTime();
		String result = instance.getCurrentDateTime();
		assertEquals(expResult, result);
	}

	@Test
	public void testCreateNewFile() {
		System.out.println("createNewFile");
		DiagnosticLogger instance = new DiagnosticLogger();
		String dateFormat = instance.getCurrentDateTime();;
		instance.createNewFile(dateFormat);
	}

	@Test
	public void testWriteToFile() {
		System.out.println("writeToFile");
		DiagnosticLogger instance = new DiagnosticLogger();
		String dateFormat = instance.getCurrentDateTime();;		
		instance.writeToFile(dateFormat);
	}

	@Test
	public void testMain() {
		System.out.println("main");
		String[] args = null;
		DiagnosticLogger.main(args);
	}
	
}
