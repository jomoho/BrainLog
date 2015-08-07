package org.hcilab.bcistub.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.hcilab.bcistub.core.BackendControl;

public class Mainframe {

	private final String[] options = { "Push", "Pull", "Lift", "Drop", "Left", "Right", "Rotate Left", "Rotate Right",
			"Rotate Clockwise", "Rotate Counter-Clockwise", "Rotate Forward", "Rotate Reverse", "Disappear" };
	private final String[] videoOptions = { "1", "2", "3", "4", "5", "6", "7", "8" };
	private final BackendControl bc;
	protected Shell shell;

	private Label lblTimestampcarrier;
	private Label labelExcitementCarrier;
	private Label lblEngagementboredomcarrier;
	private Label lblMeditationcarrier;
	private Label lblFrustrationcarrier;
	private Text lblUserText;
	Combo comboVideo;
	private Label lblBlinkcarrier;
	private Label lblWinkleftcarrier;
	private Label lblWinkrightcarrier;
	private Label lblLookleftcarrier;
	private Label lblLookrightcarrier;
	private Label lblNeutralcarrier;
	private ProgressBar progressBarExcitement;
	private ProgressBar progressBarEngagement;
	private ProgressBar progressBarMeditation;
	private ProgressBar progressBarFrustration;
	private ProgressBar progressBarAction1;
	private ProgressBar progressBarAction2;
	private ProgressBar progressBarAction3;
	private ProgressBar progressBarAction4;
	Combo comboAction1;
	Combo comboAction2;
	Combo comboAction3;
	Combo comboAction4;
	String selectedVideo;
	String selectedUser;

	public Mainframe(BackendControl b) {
		this.bc = b;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void setTimeStamp(float pTimestamp) {
		lblTimestampcarrier.setText(pTimestamp + "");
	}

	public void setUserID(int pUserID) {
		lblUserText.setText(pUserID + "");
	}

	public void setExcitement(float pExcitement) {
		labelExcitementCarrier.setText(pExcitement + "");
		progressBarExcitement.setSelection((int) (pExcitement * 100));
	}

	public void setEngagementBoredom(float pEngangement) {
		lblEngagementboredomcarrier.setText(pEngangement + "");
		progressBarEngagement.setSelection((int) (pEngangement * 100));
	}

	public void setMeditation(float pMeditation) {
		lblMeditationcarrier.setText(pMeditation + "");
		progressBarMeditation.setSelection((int) (pMeditation * 100));
	}

	public void setFrustration(float pFrustration) {
		lblFrustrationcarrier.setText(pFrustration + "");
		progressBarFrustration.setSelection((int) (pFrustration * 100));
	}

	public void setBlink(boolean blink) {
		if (blink) {
			lblBlinkcarrier.setText("TRUE");
		} else {
			lblBlinkcarrier.setText("FALSE");
		}
	}

	public void setWinkLeft(boolean winkleft) {
		if (winkleft) {
			lblWinkleftcarrier.setText("TRUE");
		} else {
			lblWinkleftcarrier.setText("FALSE");
		}
	}

	public void setWinkRight(boolean winkright) {
		if (winkright) {
			lblWinkrightcarrier.setText("TRUE");
		} else {
			lblWinkrightcarrier.setText("FALSE");
		}
	}

	public void setLookLeft(boolean lookleft) {
		if (lookleft) {
			lblLookleftcarrier.setText("TRUE");
		} else {
			lblLookleftcarrier.setText("FALSE");
		}
	}

	public void setLookRight(boolean lookright) {
		if (lookright) {
			lblLookrightcarrier.setText("TRUE");
		} else {
			lblLookrightcarrier.setText("FALSE");
		}
	}

	public void setCognitiveAction(int pActionId, float pPower) {

		if (comboAction1.getSelectionIndex() != -1) {
			if (BackendControl.cognitivActionList[comboAction1.getSelectionIndex()] == pActionId) {
				progressBarAction1.setSelection((int) (pPower * 100));
				progressBarAction2.setSelection(0);
				progressBarAction3.setSelection(0);
				progressBarAction4.setSelection(0);
			}
		}

		if (comboAction2.getSelectionIndex() != -1) {
			if (BackendControl.cognitivActionList[comboAction2.getSelectionIndex()] == pActionId) {
				progressBarAction2.setSelection((int) (pPower * 100));
				progressBarAction1.setSelection(0);
				progressBarAction3.setSelection(0);
				progressBarAction4.setSelection(0);
			}
		}

		if (comboAction3.getSelectionIndex() != -1) {
			if (BackendControl.cognitivActionList[comboAction3.getSelectionIndex()] == pActionId) {
				progressBarAction3.setSelection((int) (pPower * 100));
				progressBarAction2.setSelection(0);
				progressBarAction1.setSelection(0);
				progressBarAction4.setSelection(0);
			}
		}

		if (comboAction4.getSelectionIndex() != -1) {
			if (BackendControl.cognitivActionList[comboAction4.getSelectionIndex()] == pActionId) {
				progressBarAction4.setSelection((int) (pPower * 100));
				progressBarAction2.setSelection(0);
				progressBarAction3.setSelection(0);
				progressBarAction1.setSelection(0);
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	/**
	 * 
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setMinimumSize(new Point(132, 43));
		shell.setSize(600, 450);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		Group grpGeneral = new Group(composite, SWT.NONE);
		grpGeneral.setText("General");
		grpGeneral.setLayout(new GridLayout(2, false));

		Label lblTimestamp = new Label(grpGeneral, SWT.NONE);
		lblTimestamp.setText("Timestamp:");

		lblTimestampcarrier = new Label(grpGeneral, SWT.NONE);
		GridData gd_lblTimestampcarrier = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblTimestampcarrier.widthHint = 156;
		lblTimestampcarrier.setLayoutData(gd_lblTimestampcarrier);

		Label lblUser = new Label(grpGeneral, SWT.NONE);
		lblUser.setText("User:");

		lblUserText = new Text(grpGeneral, SWT.NONE);
		lblUserText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblVideo = new Label(grpGeneral, SWT.NONE);
		lblVideo.setText("Video:");

		comboVideo = new Combo(grpGeneral, SWT.NONE);
		comboVideo.setItems(videoOptions);
		GridData gd_comboVideo1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_comboVideo1.widthHint = 120;
		comboVideo.setLayoutData(gd_comboVideo1);

		Button btnStart = new Button(grpGeneral, SWT.NONE);
		btnStart.setText("Start");
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				selectedUser = lblUserText.getText();
				selectedVideo = comboVideo.getText();
			}
		});

		Button btnStop = new Button(grpGeneral, SWT.NONE);
		btnStop.setText("Stop");

		Group grpSettings = new Group(composite, SWT.NONE);
		grpSettings.setText("Settings");
		grpSettings.setLayout(new GridLayout(1, false));

		final Button btnStreamToUdp = new Button(grpSettings, SWT.CHECK);
		btnStreamToUdp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				boolean state = btnStreamToUdp.getSelection();
				BackendControl.getInstance().setUDPStreaming(state);
			}
		});
		btnStreamToUdp.setText("Stream to UDP");

		final Button btnLog = new Button(grpSettings, SWT.CHECK);
		btnLog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				boolean state = btnLog.getSelection();
				BackendControl.getInstance().setLogValues(state);
			}
		});
		btnLog.setText("Log to file");

		Group grpAffective = new Group(shell, SWT.NONE);
		GridData gd_grpAffective = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_grpAffective.widthHint = 475;
		grpAffective.setLayoutData(gd_grpAffective);
		grpAffective.setText("Affective");
		grpAffective.setLayout(new GridLayout(3, false));

		Label lblExcitement = new Label(grpAffective, SWT.NONE);
		lblExcitement.setText("Excitement:");

		labelExcitementCarrier = new Label(grpAffective, SWT.NONE);
		GridData gd_labelExcitementCarrier = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_labelExcitementCarrier.widthHint = 76;
		labelExcitementCarrier.setLayoutData(gd_labelExcitementCarrier);

		progressBarExcitement = new ProgressBar(grpAffective, SWT.NONE);

		Label lblEngagementboredom = new Label(grpAffective, SWT.NONE);
		lblEngagementboredom.setText("Engagement/Boredom:");

		lblEngagementboredomcarrier = new Label(grpAffective, SWT.NONE);
		lblEngagementboredomcarrier.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		progressBarEngagement = new ProgressBar(grpAffective, SWT.NONE);

		Label lblMeditation = new Label(grpAffective, SWT.NONE);
		lblMeditation.setText("Meditation:");

		lblMeditationcarrier = new Label(grpAffective, SWT.NONE);
		lblMeditationcarrier.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		progressBarMeditation = new ProgressBar(grpAffective, SWT.NONE);

		Label lblFrustration = new Label(grpAffective, SWT.NONE);
		lblFrustration.setText("Frustration:");

		lblFrustrationcarrier = new Label(grpAffective, SWT.NONE);
		lblFrustrationcarrier.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		progressBarFrustration = new ProgressBar(grpAffective, SWT.NONE);

		Group grpExpressive = new Group(shell, SWT.NONE);
		grpExpressive.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpExpressive.setText("Expressive");
		grpExpressive.setLayout(new GridLayout(2, false));

		Label lblEyeBlink = new Label(grpExpressive, SWT.NONE);
		lblEyeBlink.setText("Eye Blink:");

		lblBlinkcarrier = new Label(grpExpressive, SWT.NONE);
		GridData gd_lblBlinkcarrier = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblBlinkcarrier.widthHint = 164;
		lblBlinkcarrier.setLayoutData(gd_lblBlinkcarrier);

		Label lblEyeWinkLeft = new Label(grpExpressive, SWT.NONE);
		lblEyeWinkLeft.setText("Eye Wink Left:");

		lblWinkleftcarrier = new Label(grpExpressive, SWT.NONE);
		lblWinkleftcarrier.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblEyeWinkRight = new Label(grpExpressive, SWT.NONE);
		lblEyeWinkRight.setText("Eye Wink Right:");

		lblWinkrightcarrier = new Label(grpExpressive, SWT.NONE);
		lblWinkrightcarrier.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblEyeLookLeft = new Label(grpExpressive, SWT.NONE);
		lblEyeLookLeft.setText("Eye Look Left:");

		lblLookleftcarrier = new Label(grpExpressive, SWT.NONE);
		lblLookleftcarrier.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblEyeLookRight = new Label(grpExpressive, SWT.NONE);
		lblEyeLookRight.setText("Eye Look Right:");

		lblLookrightcarrier = new Label(grpExpressive, SWT.NONE);
		lblLookrightcarrier.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

//      Group grpCognitive = new Group(shell, SWT.NONE);
//		grpCognitive.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
//		grpCognitive.setText("Cognitive");
//		grpCognitive.setLayout(new GridLayout(5, false));
//
//		Label lblStart = new Label(grpCognitive, SWT.NONE);
//		lblStart.setText("Neutral");
//
//		lblNeutralcarrier = new Label(grpCognitive, SWT.NONE);
//
//		Button btnNeutralTrain = new Button(grpCognitive, SWT.NONE);
//		btnNeutralTrain.setText("Train");
//
//		Button btnNeutralDelete = new Button(grpCognitive, SWT.NONE);
//		btnNeutralDelete.setText("Delete");
//		new Label(grpCognitive, SWT.NONE);
//
//		Label lblAction1Carrier = new Label(grpCognitive, SWT.NONE);
//		lblAction1Carrier.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		lblAction1Carrier.setText("Action 1:");
//
//		comboAction1 = new Combo(grpCognitive, SWT.READ_ONLY);
//		comboAction1.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				progressBarAction1.setSelection(0);
//			}
//		});
//		comboAction1.setEnabled(false);
//		comboAction1.setItems(options);
//		GridData gd_comboAction1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
//		gd_comboAction1.widthHint = 120;
//		comboAction1.setLayoutData(gd_comboAction1);
//
//		final Button btnTrainAction1 = new Button(grpCognitive, SWT.NONE);
//		btnTrainAction1.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// Strings in ComboBox haben selbe Reihenfolge wie StringArray
//				// in startTraining
//				bc.startTraining(comboAction1.getSelectionIndex());
//
//			}
//		});
//		btnTrainAction1.setText("Train");
//		btnTrainAction1.setEnabled(false);
//
//		final Button btnDeleteAction1 = new Button(grpCognitive, SWT.NONE);
//		btnDeleteAction1.setText("Delete");
//		btnDeleteAction1.setEnabled(false);
//
//		progressBarAction1 = new ProgressBar(grpCognitive, SWT.NONE);
//
//		Label lblAction2Carrier = new Label(grpCognitive, SWT.NONE);
//		lblAction2Carrier.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		lblAction2Carrier.setText("Action 2:");
//
//		comboAction2 = new Combo(grpCognitive, SWT.READ_ONLY);
//		comboAction2.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				progressBarAction2.setSelection(0);
//			}
//		});
//		comboAction2.setEnabled(false);
//		comboAction2.setItems(options);
//		GridData gd_comboAction2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
//		gd_comboAction2.widthHint = 145;
//		comboAction2.setLayoutData(gd_comboAction2);
//
//		final Button btnTrainAction2 = new Button(grpCognitive, SWT.NONE);
//		btnTrainAction2.setEnabled(false);
//		btnTrainAction2.setText("Train");
//
//		final Button btnDeleteAction2 = new Button(grpCognitive, SWT.NONE);
//		btnDeleteAction2.setEnabled(false);
//		btnDeleteAction2.setText("Delete");
//
//		progressBarAction2 = new ProgressBar(grpCognitive, SWT.NONE);
//
//		Label lblAction3Carrier = new Label(grpCognitive, SWT.NONE);
//		lblAction3Carrier.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		lblAction3Carrier.setText("Action 3:");
//
//		comboAction3 = new Combo(grpCognitive, SWT.READ_ONLY);
//		comboAction3.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				progressBarAction3.setSelection(0);
//			}
//		});
//		comboAction3.setEnabled(false);
//		comboAction3.setItems(options);
//		GridData gd_comboAction3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
//		gd_comboAction3.widthHint = 165;
//		comboAction3.setLayoutData(gd_comboAction3);
//
//		final Button btnTrainAction3 = new Button(grpCognitive, SWT.NONE);
//		btnTrainAction3.setEnabled(false);
//		btnTrainAction3.setText("Train");
//
//		final Button btnDeleteAction3 = new Button(grpCognitive, SWT.NONE);
//		btnDeleteAction3.setEnabled(false);
//		btnDeleteAction3.setText("Delete");
//
//		progressBarAction3 = new ProgressBar(grpCognitive, SWT.NONE);
//
//		Label lblAction4Carrier = new Label(grpCognitive, SWT.NONE);
//		lblAction4Carrier.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		lblAction4Carrier.setText("Action 4:");
//
//		comboAction4 = new Combo(grpCognitive, SWT.READ_ONLY);
//		comboAction4.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				progressBarAction4.setSelection(0);
//			}
//		});
//		comboAction4.setEnabled(false);
//		comboAction4.setItems(options);
//		GridData gd_comboAction4 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
//		gd_comboAction4.widthHint = 135;
//		comboAction4.setLayoutData(gd_comboAction4);
//
//		final Button btnTrainAction4 = new Button(grpCognitive, SWT.NONE);
//		btnTrainAction4.setEnabled(false);
//		btnTrainAction4.setText("Train");
//
//		final Button btnDeleteAction4 = new Button(grpCognitive, SWT.NONE);
//		btnDeleteAction4.setEnabled(false);
//		btnDeleteAction4.setText("Delete");
//
//		progressBarAction4 = new ProgressBar(grpCognitive, SWT.NONE);
//		
//
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmFile = new MenuItem(menu, SWT.NONE);
		mntmFile.setText("File");

		MenuItem mntmConnect_1 = new MenuItem(menu, SWT.CASCADE);
		mntmConnect_1.setText("Connect");

		Menu menu_1 = new Menu(mntmConnect_1);
		mntmConnect_1.setMenu(menu_1);

		MenuItem mntmToHeadset = new MenuItem(menu_1, SWT.NONE);
		mntmToHeadset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				BackendControl.getInstance().connectToHeadset();
			}
		});
		mntmToHeadset.setText("To Headset");

		MenuItem mntmToComposer = new MenuItem(menu_1, SWT.NONE);
		mntmToComposer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				BackendControl.getInstance().connectToComposer();
			}
		});
		mntmToComposer.setText("To Composer");

//		btnNeutralTrain.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				bc.startNeutralTraining();
//				comboAction1.setEnabled(true);
//				btnTrainAction1.setEnabled(true);
//				btnDeleteAction1.setEnabled(true);
//
//				comboAction2.setEnabled(true);
//				btnTrainAction2.setEnabled(true);
//				btnDeleteAction2.setEnabled(true);
//
//				comboAction3.setEnabled(true);
//				btnTrainAction3.setEnabled(true);
//				btnDeleteAction3.setEnabled(true);
//
//				comboAction4.setEnabled(true);
//				btnTrainAction4.setEnabled(true);
//				btnDeleteAction4.setEnabled(true);
//			}
//		});
//
//		btnNeutralDelete.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				bc.deleteNeutralTraining();
//			}
//		});
//
//		btnTrainAction1.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// Strings in ComboBox haben selbe Reihenfolge wie StringArray
//				// in startTraining
//				bc.startTraining(comboAction1.getSelectionIndex());
//				comboAction1.setEnabled(false);
//			}
//		});
//
//		btnTrainAction2.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// Strings in ComboBox haben selbe Reihenfolge wie StringArray
//				// in startTraining
//				bc.startTraining(comboAction2.getSelectionIndex());
//				comboAction2.setEnabled(false);
//			}
//		});
//
//		btnTrainAction3.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// Strings in ComboBox haben selbe Reihenfolge wie StringArray
//				// in startTraining
//				bc.startTraining(comboAction3.getSelectionIndex());
//				comboAction3.setEnabled(false);
//			}
//		});
//
//		btnTrainAction4.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// Strings in ComboBox haben selbe Reihenfolge wie StringArray
//				// in startTraining
//				bc.startTraining(comboAction4.getSelectionIndex());
//				comboAction4.setEnabled(false);
//			}
//		});
//
//		btnDeleteAction1.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// Strings in ComboBox haben selbe Reihenfolge wie StringArray
//				// in startTraining
//				bc.deleteTraining(comboAction1.getSelectionIndex());
//				comboAction1.deselectAll();
//				comboAction1.setEnabled(true);
//				progressBarAction1.setSelection(0);
//			}
//		});
//
//		btnDeleteAction2.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// Strings in ComboBox haben selbe Reihenfolge wie StringArray
//				// in startTraining
//				bc.deleteTraining(comboAction2.getSelectionIndex());
//				comboAction2.deselectAll();
//				comboAction2.setEnabled(true);
//				progressBarAction2.setSelection(0);
//			}
//		});
//
//		btnDeleteAction3.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// Strings in ComboBox haben selbe Reihenfolge wie StringArray
//				// in startTraining
//				bc.deleteTraining(comboAction3.getSelectionIndex());
//				comboAction3.deselectAll();
//				comboAction3.setEnabled(true);
//				progressBarAction3.setSelection(0);
//			}
//		});
//
//		btnDeleteAction4.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// Strings in ComboBox haben selbe Reihenfolge wie StringArray
//				// in startTraining
//				bc.deleteTraining(comboAction4.getSelectionIndex());
//				comboAction4.deselectAll();
//				comboAction4.setEnabled(true);
//				progressBarAction4.setSelection(0);
//			}
//		});
	}
}
