package DiagnosticLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiagnosticLogger implements IDiagnosticLogger {


	@Override
	public String getCurrentDateTime() {
		String date_to_string;
		
		//Creating Date in java with today's date.
	       Date logDate = new Date();

		//date to dd-MMM-yy format e.g. "14-Sep-11"
		SimpleDateFormat ddMMMyyFormat = new SimpleDateFormat("dd-MMM-yy_HH-mm-ss");
		date_to_string = ddMMMyyFormat.format(logDate);
		
	   return date_to_string;
	}
	
	@Override
	public void createNewFile(String dateFormat) {
 
	//creates file with time stamp title	
        File logFile = new File(dateFormat + ".txt");
	
	boolean fileCreated = false;
        try {
            fileCreated = logFile.createNewFile();
        } catch (IOException ioe) {
            System.out.println("Error while creating empty file: " + ioe);
        }
 
        if (fileCreated) {
            System.out.println("Created empty file: " + logFile.getPath());
        } else {
            System.out.println("Failed to create empty file: " + logFile.getPath());
        }
     }

	@Override
	public void writeToFile(String dateFormat) {
		
		try {
 			String content = toString();
// 			String content = "This is the content to write into file";
			
			File file = new File(dateFormat + ".txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				bw.write(content);
			}
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		DiagnosticLogger dl = new DiagnosticLogger();
		
		String currentDateTime = dl.getCurrentDateTime();
		dl.createNewFile(currentDateTime);
		dl.writeToFile(currentDateTime);
		
	}
*/
}

