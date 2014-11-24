package se459.team2.CleanSweep.DiagnosticLogger;

public interface IDiagnosticLogger {
	
	public void createNewFile(String dateFormat);
	
	public String getCurrentDateTime();
		
	public void readFromFile(String dateFormat);
	
	public void writeToFile(String dateFormat, String input);
 
	public void listAllFiles();
}
