package org.hcilab.bcistub.comm;

import org.hcilab.bcistub.core.EmoState.EE_CognitivAction_t;
import org.hcilab.bcistub.data.BCIState;

import de.uni_stuttgart.eitoolkit.DataMessage;
import de.uni_stuttgart.eitoolkit.Description;
import de.uni_stuttgart.eitoolkit.Sender;
import de.uni_stuttgart.eitoolkit.StringMap;


public class CommunitactionHandler {

	private static CommunitactionHandler instance;
	
	private boolean isInitialized = false;
	private Description desc;
	private Sender sender;
	
	private CommunitactionHandler(){
		init();
	};
	
	public static CommunitactionHandler getInstance(){
		if(instance == null){
			instance = new CommunitactionHandler();
		}
		return instance;
	}
	
	public void init(){
		
		 desc = new Description("EPOC_GUI_STUB", "Emotive_Epoc");
		 sender = new Sender(desc, new StringMap());
		
		
		isInitialized = true;		
	}
	
	public void sendBCIData(BCIState state){
		if(isInitialized){
			DataMessage msg = sender.createDataMessage();
			
			//meta values
			msg.setString(EPOCConstants.META_TIME_FROM_START, state.getTimestamp()+"");
			msg.setString(EPOCConstants.META_USER_ID, state.getUserID()+"");
			
			
			//affective values
			msg.setString(EPOCConstants.AFFECTIVE_ENGAGEMENT_BOREDOM, state.getEngagementBoredom()+"");
			msg.setString(EPOCConstants.AFFECTIVE_EXCITEMENT_SHORT, state.getExcitement()+"");
			msg.setString(EPOCConstants.AFFECTIVE_EXCITEMENT_LONG, state.getExcitementLongterm()+"");
			msg.setString(EPOCConstants.AFFECTIVE_FRUSTRATION, state.getFrustration()+"");
			msg.setString(EPOCConstants.AFFECTIVE_MEDITATION, state.getMeditation()+"");
			
			//cognitive values
			int cognitiveAction = state.getCognitiveAction();
			float cognitivePower = state.getCognitivePower();
			if(cognitiveAction == EE_CognitivAction_t.COG_DISAPPEAR.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_DISAPPEAR, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_DROP.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_DROP, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_LEFT.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_LEFT, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_LIFT.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_LIFT, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_NEUTRAL.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_NEUTRAL, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_PULL.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_PULL, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_PUSH.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_PUSH, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_RIGHT.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_RIGHT, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_ROTATE_CLOCKWISE.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_ROTATE_CLOCKWISE, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_ROTATE_COUNTER_CLOCKWISE.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_ROTATE_COUNTER_CLOCKWISE, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_ROTATE_FORWARDS.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_ROTATE_FORWARDS, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_ROTATE_LEFT.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_ROTATE_LEFT, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_ROTATE_REVERSE.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_ROTATE_REVERSE, cognitivePower+"");
			}else if(cognitiveAction == EE_CognitivAction_t.COG_ROTATE_RIGHT.ToInt()){
				msg.setString(EPOCConstants.COGNITIVE_ROTATE_RIGHT, cognitivePower+"");
			}
			
			
			// expressive values
			msg.setString(EPOCConstants.EXPRESSIVE_CLENCH, state.getClench()+"");
			msg.setString(EPOCConstants.EXPRESSIVE_EYEBROW, state.getEyebrow()+"");
			msg.setString(EPOCConstants.EXPRESSIVE_FURROW, state.getFurrowBrow()+"");
			msg.setString(EPOCConstants.EXPRESSIVE_LAUGH, state.getLaugh()+"");
			msg.setString(EPOCConstants.EXPRESSIVE_SMILE, state.getSmile()+"");
			msg.setString(EPOCConstants.EXPRESSIVE_SMIRK_LEFT, state.getSmirkleft()+"");
			msg.setString(EPOCConstants.EXPRESSIVE_SMIRK_RIGHT, state.getSmirkright()+"");
			
			
			if(state.isBlink()){
				msg.setString(EPOCConstants.EXPRESSIVE_EYE_BLINK,"TRUE");
			}
			if(state.isLookLeft()){
				msg.setString(EPOCConstants.EXPRESSIVE_EYE_LOOK_LEFT, "TRUE");
			}
			if(state.isLookRight()){
				msg.setString(EPOCConstants.EXPRESSIVE_EYE_LOOK_RIGHT, "TRUE");
			}
			if(state.isWinkLeft()){
				msg.setString(EPOCConstants.EXPRESSIVE_EYE_WINK_LEFT, "TRUE");
			}
			if(state.isWinkRight()){
				msg.setString(EPOCConstants.EXPRESSIVE_EYE_WINK_RIGHT, "TRUE");
			}
			
		
			
			sender.sendMessage(msg);
			//System.out.println("Message sent to UDP");
		}else{
			//TODO: boom
		}
	}
	
}
