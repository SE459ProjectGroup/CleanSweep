package DiagnosticLogger;

public interface IDiagnosticLogger {
	
	public void createNewFile(String dateFormat);
	
	public String getCurrentDateTime();
		
//	public void saveFile();
	
//	public void readFromFile();
	
	public void writeToFile(String dateFormat);
 
}
