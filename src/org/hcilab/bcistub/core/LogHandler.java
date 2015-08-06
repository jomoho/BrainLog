package org.hcilab.bcistub.core;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;

import org.hcilab.bcistub.data.BCIState;

public class LogHandler {

	private static LogHandler instance;
	
	private PrintWriter out;
	
	private Calendar rightNow = Calendar.getInstance();
	
	private LogHandler(){
		try {
			out = new PrintWriter("log" +  rightNow.getTimeInMillis()  +  ".csv");
			out.append("timestamp" + "," +
					"excitement" +"," +
					"frustration" + "," +
					"meditation" + "," +
					"engagementBoredom"+ "\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static LogHandler getInstance(){
		if(instance == null){
			instance = new LogHandler();
		}
		return instance;
	}
	
	public void log(BCIState pState)
	{
		if((out != null) && (pState != null))
		{
			out.append(pState.getTimestamp() + "," +
					pState.getExcitement() +"," +
					pState.getFrustration() + "," +
					pState.getMeditation() + "," +
					pState.getEngagementBoredom()+"\n");
			out.flush();
			//pState.flush();
		}
		
	}
	
}
