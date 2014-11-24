package se459.team2.CleanSweep.DiagnosticLogger;

import org.junit.Test;
import static org.junit.Assert.*;

public class DiagnosticLoggerTest {
	
	public DiagnosticLoggerTest() {
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
		String dateFormat = "";
		DiagnosticLogger instance = new DiagnosticLogger();
		instance.createNewFile(dateFormat);
	}

	@Test
	public void testWriteToFile() {
		System.out.println("writeToFile");
		String dateFormat = "";
		DiagnosticLogger instance = new DiagnosticLogger();
	}

	@Test
	public void testReadFromFile() {
		System.out.println("readFromFile");
		String dateFormat = "";
		DiagnosticLogger instance = new DiagnosticLogger();
		instance.readFromFile(dateFormat);
	}

	@Test
	public void testListAllFiles() {
		System.out.println("listAllFiles");
		DiagnosticLogger instance = new DiagnosticLogger();
		instance.listAllFiles();
	}

}
