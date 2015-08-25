package org.hcilab.bcistub.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;

import org.hcilab.bcistub.data.BCIState;

public class CSVLogHandler {

	
	private PrintWriter out;
	
	private Calendar rightNow = Calendar.getInstance();
	
	public CSVLogHandler(String folder, String filename){
		File theDir = new File(folder);
		 
		// if the directory does not exist, create it 
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + folder);
		    boolean result = false;
		 
		    try{ 
		        theDir.mkdir();
		        result = true;
		    }  
		    catch(SecurityException se){
		        //handle it 
		    }         
		    if(result) {    
		        System.out.println("DIR created");  
		    } 
		}
		
		try {
			out = new PrintWriter(folder + "/log_"+filename+"_"+  rightNow.getTimeInMillis()  +  ".csv");
			String line = "timestamp" + "," +
					"excitement" +"," +
					"frustration" + "," +
					"meditation" + "," +
					"engagementBoredom" + "," +
					"blink" + "," +
					"clench" + "," +
					"laugh" + "," +
					"smile" + "," +
					"eyebrow" + "," +
					"furrowBrow" + "," +
					"lookLeft" + "," +
					"lookRight" + "," +
					"smirkLeft" + "," +
					"smirkRight" + "," +
					"winkLeft" + "," +
					"winkRight";
			for(int i = 0; i < 17; i++){
				line = line + ",eeg"+ i;
			}
			line = line + "\n";
			out.append(line);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private int makeint(boolean b){
		return b?1:0;
	}
	
	public void log(BCIState pState)
	{
		if((out != null) && (pState != null))
		{
			String line = pState.getTimestamp() + "," +
					pState.getExcitement() +"," +
					pState.getFrustration() + "," +
					pState.getMeditation() + "," +
					pState.getEngagementBoredom()+ "," +
					makeint(pState.isBlink()) + "," +
					pState.getClench() + "," +
					pState.getLaugh() + "," +
					pState.getSmile() + "," +
					pState.getEyebrow() + "," +
					pState.getFurrowBrow() + "," +
					makeint(pState.isLookLeft()) + "," +
					makeint(pState.isLookRight()) + "," +
					pState.getSmirkleft() + "," +
					pState.getSmirkright() + "," +
					makeint(pState.isWinkLeft()) + "," +
					makeint(pState.isWinkRight());
			for(int i = 0; i < 17; i++){
				line += "," + pState.eeg[i];
			}
			line += "\n";
			out.append(line);
			out.flush();
			//pState.flush();
		}
		
	}
	public void close(){
		out.close();
	}
	
}
