package se459.team2.CleanSweep.DiagnosticLogger;

import java.io.File;
import java.io.FileInputStream;
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
		SimpleDateFormat ddMMMyyFormat = new SimpleDateFormat("dd-MMM-yy_HH-mm");
		date_to_string = ddMMMyyFormat.format(logDate);
		
	   return date_to_string;
	}
	
	@Override
	public void createNewFile(String dateFormat) {
 
		//creates file with time stamp title	
		File logFile = new File(dateFormat + ".log");

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
 //			String content = toString();
			String content = "This is the content to write into file\n";
			
			File file = new File(dateFormat + ".log");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file,true);  //the true will append the new data
			
			//appends the string to the file
			fw.write(toString());
			
//			fw.write("add a line\n");//appends the string to the file
			fw.close();
			
			System.out.println("Done");
 
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}

	@Override
	public void readFromFile(String dateFormat) {

		File logFile = new File(dateFormat + ".log");
		FileInputStream logFileIn = null;
 
		try {
			logFileIn = new FileInputStream(logFile);
 
			System.out.println("Total file size to read (in bytes) : "
					+ logFileIn.available());
 
			int content;
			while ((content = logFileIn.read()) != -1) {
				// convert to char and display it
				System.out.print((char) content);
			}
 
		} catch (IOException e) {
				System.err.println("IOException: " + e.getMessage());
		} finally {
			try {
				if (logFileIn != null)
					logFileIn.close();
			} catch (IOException ex) {
				System.err.println("IOException: " + ex.getMessage());
			}
		}
	}

	@Override
	public void listAllFiles() {

	        File curDir = new File(".");
		
		File[] filesList = curDir.listFiles();
		
		for(File f : filesList){

//			list FOLDERS, IF WANTED
//			if(f.isDirectory())
//              		System.out.println(f.getName());

			// list FILES
			if(f.isFile()){
				System.out.println(f.getName());
				}
		}
	}
	
	
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DiagnosticLogger dl = new DiagnosticLogger();

		dl.listAllFiles();
				
		String currentDateTime = dl.getCurrentDateTime();
		dl.createNewFile(currentDateTime);
		dl.writeToFile(currentDateTime);
		dl.readFromFile(currentDateTime);
		dl.listAllFiles();		
	}
*/
}
