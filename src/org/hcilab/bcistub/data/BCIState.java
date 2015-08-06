package org.hcilab.bcistub.data;

public class BCIState {
	float timestamp;
	int userID;
	String userName;

	int cognitiveAction = 0;
	float cognitivePower;

	float excitement;
	float excitementLongterm;
	float engagementBoredom;
	float meditation;
	float frustration;

	// float raiseBrow;
	float furrowBrow;
	// float lowerfacePower;

	float smile;
	float clench;
	float eyebrow;
	float laugh;
	float smirkleft;
	float smirkright;

	boolean blink;
	boolean winkLeft;
	boolean winkRight;
	boolean lookLeft;
	boolean lookRight;

	public boolean isBlink() {
		return blink;
	}

	public void setBlink(boolean blink) {
		this.blink = blink;
	}

	public boolean isWinkLeft() {
		return winkLeft;
	}

	public void setWinkLeft(boolean winkLeft) {
		this.winkLeft = winkLeft;
	}

	public boolean isWinkRight() {
		return winkRight;
	}

	public void setWinkRight(boolean winkRight) {
		this.winkRight = winkRight;
	}

	public boolean isLookLeft() {
		return lookLeft;
	}

	public void setLookLeft(boolean lookLeft) {
		this.lookLeft = lookLeft;
	}

	public boolean isLookRight() {
		return lookRight;
	}

	public void setLookRight(boolean lookRight) {
		this.lookRight = lookRight;
	}

	public float getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(float timestamp) {
		this.timestamp = timestamp;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getCognitiveAction() {
		return cognitiveAction;
	}

	public void setCognitiveAction(int cognitiveAction) {
		this.cognitiveAction = cognitiveAction;
	}

	public float getCognitivePower() {
		return cognitivePower;
	}

	public void setCognitivePower(float cognitivePower) {
		this.cognitivePower = cognitivePower;
	}

	public float getExcitement() {
		return excitement;
	}

	public void setExcitement(float excitement) {
		this.excitement = excitement;
	}

	public float getExcitementLongterm() {
		return excitementLongterm;
	}

	public void setExcitementLongterm(float excitementLongterm) {
		this.excitementLongterm = excitementLongterm;
	}

	public float getEngagementBoredom() {
		return engagementBoredom;
	}

	public void setEngagementBoredom(float engagementBoredom) {
		this.engagementBoredom = engagementBoredom;
	}

	public float getMeditation() {
		return meditation;
	}

	public void setMeditation(float meditation) {
		this.meditation = meditation;
	}

	public float getFrustration() {
		return frustration;
	}

	public void setFrustration(float frustration) {
		this.frustration = frustration;
	}

	public float getFurrowBrow() {
		return furrowBrow;
	}

	public void setFurrowBrow(float furrowBrow) {
		this.furrowBrow = furrowBrow;
	}

	public float getSmile() {
		return smile;
	}

	public void setSmile(float smile) {
		this.smile = smile;
	}

	public float getClench() {
		return clench;
	}

	public void setClench(float clench) {
		this.clench = clench;
	}

	public float getEyebrow() {
		return eyebrow;
	}

	public void setEyebrow(float eyebrow) {
		this.eyebrow = eyebrow;
	}

	public float getLaugh() {
		return laugh;
	}

	public void setLaugh(float laugh) {
		this.laugh = laugh;
	}

	public float getSmirkleft() {
		return smirkleft;
	}

	public void setSmirkleft(float smirkleft) {
		this.smirkleft = smirkleft;
	}

	public float getSmirkright() {
		return smirkright;
	}

	public void setSmirkright(float smirkright) {
		this.smirkright = smirkright;
	}

}
