package org.hcilab.bcistub.comm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.hcilab.bcistub.core.EmoState.EE_CognitivAction_t;
import org.hcilab.bcistub.data.BCIState;

import de.uni_stuttgart.eitoolkit.DataMessage;
import de.uni_stuttgart.eitoolkit.Description;
import de.uni_stuttgart.eitoolkit.Sender;
import de.uni_stuttgart.eitoolkit.StringMap;


public class TCPCommHandler {
	Socket sock;
	private static TCPCommHandler instance;
	
	private boolean isInitialized = false;
	private TCPCommHandler(){
	};
	
	public static TCPCommHandler getInstance(){
		if(instance == null){
			instance = new TCPCommHandler();
		}
		return instance;
	}
	
	public void init(String host, int port){		
	      try {
	          sock = new Socket(host, port);

				PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
				out.println("Hello");
				out.flush();
				
	       }
	       catch(Exception e) {
	          System.out.print("Whoops! It didn't work!\n");
	          e.printStackTrace();
	       }
				
		isInitialized = true;		
	}
	
	public void sendBCIData(BCIState state){
		if(isInitialized){
			try {
				PrintWriter out = new PrintWriter(sock.getOutputStream(), true);

		         //cognitive values
				int cognitiveAction = state.getCognitiveAction();
				float cognitivePower = state.getCognitivePower();
				if(cognitivePower >0.5){
					if(cognitiveAction == EE_CognitivAction_t.COG_LEFT.ToInt()){
						out.println("C1T2000");
					}else if(cognitiveAction == EE_CognitivAction_t.COG_RIGHT.ToInt()){
						out.println("C2T2000");
					}					
				}
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			//TODO: boom
		}
	}
	
}
