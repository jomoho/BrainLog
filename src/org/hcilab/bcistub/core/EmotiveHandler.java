package org.hcilab.bcistub.core;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.hcilab.bcistub.core.EmoState.EE_ExpressivAlgo_t;
import org.hcilab.bcistub.data.BCIState;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;


public class EmotiveHandler extends Thread{
	
	public enum Target{
		HEADSET,
		COMPOSER
	}
	
	private static EmotiveHandler instance;
	private int target; // composer or headset
	private BCIState bciState = null;
	
	Pointer eEvent			= Edk.INSTANCE.EE_EmoEngineEventCreate();
	Pointer eState			= Edk.INSTANCE.EE_EmoStateCreate();
	IntByReference userID 	= null;
	short composerPort		= 1726;
	int state  				= 0;
	boolean run = false;
	
	private EmotiveHandler() {};
	
	public static EmotiveHandler getInstance(){
		
		if(instance == null){
			instance = new EmotiveHandler();
		}
		
		return instance;
	}
	
	public void setTarget(Target pTarget){
		if(pTarget == Target.HEADSET){
			target = 1;
		}else if(pTarget == Target.COMPOSER){
			target = 2;
		}
	}
	
	public BCIState getBCIState(){
		return bciState;		
	}
	
	public void init(){
		
    	int option 				= target;
    	run = true;
    	
    	
    	userID = new IntByReference(0);
    	
    	switch (option) {
		case 1:
		{
			if (Edk.INSTANCE.EE_EngineConnect("Emotiv Systems-5") != EdkErrorCode.EDK_OK.ToInt()) {
				System.out.println("Emotiv Engine start up failed.");
				return;
			}
			break;
		}
		case 2:
		{
			System.out.println("Target IP of EmoComposer: [127.0.0.1] ");

			if (Edk.INSTANCE.EE_EngineRemoteConnect("127.0.0.1", composerPort, "Emotiv Systems-5") != EdkErrorCode.EDK_OK.ToInt()) {
				System.out.println("Cannot connect to EmoComposer on [127.0.0.1]");
				return;
			}
			System.out.println("Connected to EmoComposer on [127.0.0.1]");
			break;
		}
		default:
			System.out.println("Invalid option...");
			return;
    	}
	}

	@Override
	public void run() {
		
    	
		while (run) 
		{
			state = Edk.INSTANCE.EE_EngineGetNextEvent(eEvent);

			// New event needs to be handled
			if (state == EdkErrorCode.EDK_OK.ToInt()) {
								

				int eventType = Edk.INSTANCE.EE_EmoEngineEventGetType(eEvent);
				Edk.INSTANCE.EE_EmoEngineEventGetUserId(eEvent, userID);
				
				if(eventType == Edk.EE_Event_t.EE_CognitivEvent.ToInt())
				{
					int cogType = Edk.INSTANCE.EE_CognitivEventGetType(eEvent);

					if(cogType ==Edk.EE_CognitivEvent_t.EE_CognitivTrainingStarted.getType())
					{
						System.out.println("Cognitiv Training Start");
					}
					if(cogType == Edk.EE_CognitivEvent_t.EE_CognitivTrainingCompleted.getType())
					{
						System.out.println("Cognitiv Training Complete");
					}
					if(cogType == Edk.EE_CognitivEvent_t.EE_CognitivTrainingSucceeded.getType())
					{
						Edk.INSTANCE.EE_CognitivSetTrainingControl(0,Edk.EE_CognitivTrainingControl_t.COG_ACCEPT.getType());
						System.out.println("Cognitiv Training Succeeded");
					}
					if(cogType == Edk.EE_CognitivEvent_t.EE_CognitivTrainingFailed.getType())
					{
						System.out.println("Cognitiv Training Failed");
					}
					if(cogType == Edk.EE_CognitivEvent_t.EE_CognitivTrainingRejected.getType())
					{
						System.out.println("Cognitiv Training Rejected");
					}
					if(cogType == Edk.EE_CognitivEvent_t.EE_CognitivTrainingDataErased.getType())
					{
						System.out.println("Cognitiv Training Data Erased");
					}
				}

				// Log the EmoState if it has been updated
				if (eventType == Edk.EE_Event_t.EE_EmoStateUpdated.ToInt()) {					

					Edk.INSTANCE.EE_EmoEngineEventGetEmoState(eEvent, eState);

					bciState = new BCIState();
					
					float timestamp = EmoState.INSTANCE.ES_GetTimeFromStart(eState);					
					bciState.setTimestamp(timestamp);
					
					bciState.setUserID(userID.getValue());
					//System.out.println(timestamp + " : New EmoState from user " + userID.getValue());
					
					//System.out.print("WirelessSignalStatus: ");
					//System.out.println(EmoState.INSTANCE.ES_GetWirelessSignalStatus(eState));
					//TODO: maybe later

					//eye
					if (EmoState.INSTANCE.ES_ExpressivIsBlink(eState) == 1){
						bciState.setBlink(true);
						System.out.println("Blink");
					}else{
						bciState.setBlink(false);
					}
					if (EmoState.INSTANCE.ES_ExpressivIsLeftWink(eState) == 1){
						System.out.println("LeftWink");
						bciState.setWinkLeft(true);
					}else{
						bciState.setWinkLeft(false);
					}
					if (EmoState.INSTANCE.ES_ExpressivIsRightWink(eState) == 1){
						System.out.println("RightWink");
						bciState.setWinkRight(true);
					}else{
						bciState.setWinkRight(false);
					}
					if (EmoState.INSTANCE.ES_ExpressivIsLookingLeft(eState) == 1){										
						System.out.println("LookingLeft");
						bciState.setLookLeft(true);
					}else{
						bciState.setLookLeft(false);
					}
					if (EmoState.INSTANCE.ES_ExpressivIsLookingRight(eState) == 1){
						System.out.println("LookingRight");
						bciState.setLookRight(true);
					}else{
						bciState.setLookRight(false);
					}
					
					bciState.setSmile(EmoState.INSTANCE.ES_ExpressivGetSmileExtent(eState));
					bciState.setEyebrow(EmoState.INSTANCE.ES_ExpressivGetEyebrowExtent(eState));
					float upperPower = EmoState.INSTANCE.ES_ExpressivGetUpperFaceActionPower(eState);
					if(EmoState.INSTANCE.ES_ExpressivGetUpperFaceAction(eState) == EE_ExpressivAlgo_t.EXP_FURROW.ToInt()){
						bciState.setFurrowBrow(upperPower);
					}else{
						bciState.setFurrowBrow(0.00f);
					}
					bciState.setClench(EmoState.INSTANCE.ES_ExpressivGetClenchExtent(eState));
					
					float lowerPower = EmoState.INSTANCE.ES_ExpressivGetLowerFaceActionPower(eState);
					if(EmoState.INSTANCE.ES_ExpressivGetLowerFaceAction(eState) == EE_ExpressivAlgo_t.EXP_LAUGH.ToInt()){
						bciState.setLaugh(lowerPower);
					}else{
						bciState.setLaugh(0.00f);
					}
					if(EmoState.INSTANCE.ES_ExpressivGetLowerFaceAction(eState) == EE_ExpressivAlgo_t.EXP_SMIRK_LEFT.ToInt()){
						bciState.setSmirkleft(lowerPower);
					}else{
						bciState.setSmirkleft(0.00f);
					}
					if(EmoState.INSTANCE.ES_ExpressivGetLowerFaceAction(eState) == EE_ExpressivAlgo_t.EXP_SMIRK_RIGHT.ToInt()){
						bciState.setSmirkright(lowerPower);
					}else{
						bciState.setSmirkright(0.00f);
					}
					
					// affective
					bciState.setExcitement(EmoState.INSTANCE.ES_AffectivGetExcitementShortTermScore(eState));					
					bciState.setExcitementLongterm(EmoState.INSTANCE.ES_AffectivGetExcitementLongTermScore(eState));					
					bciState.setEngagementBoredom(EmoState.INSTANCE.ES_AffectivGetEngagementBoredomScore(eState));
					bciState.setMeditation(EmoState.INSTANCE.ES_AffectivGetMeditationScore(eState));
					bciState.setFrustration(EmoState.INSTANCE.ES_AffectivGetFrustrationScore(eState));
										
					// cognitive
					bciState.setCognitiveAction(EmoState.INSTANCE.ES_CognitivGetCurrentAction(eState));
					bciState.setCognitivePower(EmoState.INSTANCE.ES_CognitivGetCurrentActionPower(eState));
				}
			}
			else if (state != EdkErrorCode.EDK_NO_EVENT.ToInt()) {
				System.out.println("Internal error in Emotiv Engine!");
				run = false;
			}
		}
		System.out.println("Not running anymore");
		
		Edk.INSTANCE.EE_EngineDisconnect();
    	System.out.println("Disconnected!");
	}
}
