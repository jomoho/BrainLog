package org.hcilab.bcistub.core;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.widgets.Display;
import org.hcilab.bcistub.comm.CommunitactionHandler;
import org.hcilab.bcistub.core.EmotiveHandler.Target;
import org.hcilab.bcistub.data.BCIState;
import org.hcilab.bcistub.gui.Mainframe;

public class BackendControl {

	private static BackendControl instance;
	private Mainframe window;

	private boolean sendValues = false;
	private boolean logValues = false;
	
	private CSVLogHandler logHandler;

	public static int[] cognitivActionList = { EmoState.EE_CognitivAction_t.COG_PUSH.ToInt(),
			EmoState.EE_CognitivAction_t.COG_PULL.ToInt(), EmoState.EE_CognitivAction_t.COG_LIFT.ToInt(),
			EmoState.EE_CognitivAction_t.COG_DROP.ToInt(), EmoState.EE_CognitivAction_t.COG_LEFT.ToInt(),
			EmoState.EE_CognitivAction_t.COG_RIGHT.ToInt(), EmoState.EE_CognitivAction_t.COG_ROTATE_LEFT.ToInt(),
			EmoState.EE_CognitivAction_t.COG_ROTATE_RIGHT.ToInt(),
			EmoState.EE_CognitivAction_t.COG_ROTATE_CLOCKWISE.ToInt(),
			EmoState.EE_CognitivAction_t.COG_ROTATE_COUNTER_CLOCKWISE.ToInt(),
			EmoState.EE_CognitivAction_t.COG_ROTATE_FORWARDS.ToInt(),
			EmoState.EE_CognitivAction_t.COG_ROTATE_REVERSE.ToInt(),
			EmoState.EE_CognitivAction_t.COG_DISAPPEAR.ToInt() };

	private BackendControl() {
		this.logHandler = new CSVLogHandler("testfolder", "testfilename");
	};

	public static BackendControl getInstance() {
		if (instance == null) {
			instance = new BackendControl();
		}
		return instance;
	}

	public void startGUI() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				BCIState state = EmotiveHandler.getInstance().getBCIState();
				if (state != null) {
					Display display = Display.getDefault();
					display.asyncExec(new Runnable() {

						@Override
						public void run() {
							updateEmoState();
						}
					});
				}

			}
		}, 0, 250);

		window = new Mainframe(this);
		window.open();
	}

	public void setUDPStreaming(boolean pSend) {
		sendValues = pSend;
	}

	public void connectToComposer() {
		EmotiveHandler emo = EmotiveHandler.getInstance();
		emo.setTarget(Target.COMPOSER);
		emo.init();
		emo.start();
	}

	public void connectToHeadset() {
		EmotiveHandler emo = EmotiveHandler.getInstance();
		emo.setTarget(Target.HEADSET);
		emo.init();
		emo.start();
	}
	public void startLog(String username, String video){
		logValues = true;
		logHandler = new CSVLogHandler(username, video);
	}

	public void stopLog(){
		logValues = false;
		logHandler.close();
	}


	public void updateEmoState() {

		if (window != null) {

			BCIState state = EmotiveHandler.getInstance().getBCIState();
			if (state != null) {
				window.setTimeStamp(state.getTimestamp());
				// window.setUserID(state.getUserID());
				window.setExcitement(state.getExcitement());
				window.setEngagementBoredom(state.getEngagementBoredom());
				window.setMeditation(state.getMeditation());
				window.setFrustration(state.getFrustration());
				window.setBlink(state.isBlink());
				window.setLookLeft(state.isLookLeft());
				window.setLookRight(state.isLookRight());
				window.setWinkLeft(state.isWinkLeft());
				window.setWinkRight(state.isWinkRight());
				int cog_action = state.getCognitiveAction();
				if (cog_action != 1) {// 1 is neutral
					window.setCognitiveAction(cog_action, state.getCognitivePower());
					System.out.println("Cognitive Action: " + cog_action);
					System.out.println("CognitivePower: " + state.getCognitivePower());
				}

				// send out values?
				if (sendValues) {
					CommunitactionHandler comm = CommunitactionHandler.getInstance();
					comm.sendBCIData(state);
				}
				//System.out.println("logValues: " + logValues);
				if (logValues) {
					//LogHandler.getInstance().log(state);
					this.logHandler.log(state);
				}

			}
		}
	}

	public void startNeutralTraining() {
		Edk.INSTANCE.EE_CognitivSetTrainingAction(0, EmoState.EE_CognitivAction_t.COG_NEUTRAL.ToInt());
		Edk.INSTANCE.EE_CognitivSetTrainingControl(0, Edk.EE_CognitivTrainingControl_t.COG_START.getType());
	}

	public void deleteNeutralTraining() {
		Edk.INSTANCE.EE_CognitivSetTrainingAction(0, EmoState.EE_CognitivAction_t.COG_NEUTRAL.ToInt());
		Edk.INSTANCE.EE_CognitivSetTrainingControl(0, Edk.EE_CognitivTrainingControl_t.COG_ERASE.getType());
	}

	public void startTraining(int action) {
		// Strings in diesem Array haben selbe Reihenfolge wie die Strings der
		// ComboBox der GUI

		if (action != -1) {
			System.out.println("Start Training:" + cognitivActionList[action]);
			Edk.INSTANCE.EE_CognitivSetActiveActions(0, cognitivActionList[action]);
			Edk.INSTANCE.EE_CognitivSetTrainingAction(0, cognitivActionList[action]);
			Edk.INSTANCE.EE_CognitivSetTrainingControl(0, Edk.EE_CognitivTrainingControl_t.COG_START.getType());
		}

	}

	public void deleteTraining(int action) {
		if (action != -1) {
			Edk.INSTANCE.EE_CognitivSetTrainingAction(0, cognitivActionList[action]);
			Edk.INSTANCE.EE_CognitivSetTrainingControl(0, Edk.EE_CognitivTrainingControl_t.COG_ERASE.getType());
		}
	}
}
