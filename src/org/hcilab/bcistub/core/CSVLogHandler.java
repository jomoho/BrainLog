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
			out.append("timestamp" + "," +
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
					"winkRight" + 
					"\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void log(BCIState pState)
	{
		if((out != null) && (pState != null))
		{
			out.append(pState.getTimestamp() + "," +
					pState.getExcitement() +"," +
					pState.getFrustration() + "," +
					pState.getMeditation() + "," +
					pState.getEngagementBoredom()+ 
					pState.isBlink() + "," +
					pState.getClench() + "," +
					pState.getLaugh() + "," +
					pState.getSmile() + "," +
					pState.getEyebrow() + "," +
					pState.getFurrowBrow() + "," +
					pState.isLookLeft() + "," +
					pState.isLookRight() + "," +
					pState.getSmirkleft() + "," +
					pState.getSmirkright() + "," +
					pState.isWinkLeft() + "," +
					pState.isWinkRight() + "," +"\n");
			out.flush();
			//pState.flush();
		}
		
	}
	
}
