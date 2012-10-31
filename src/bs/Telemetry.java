package bs;

public class Telemetry {
	
	private int ultrasonic;
	private int light;
	private boolean touch;
	private int sound;
	private int speedLeft;
	private int speedRight;
	private int angleArm;
	private long receiveTime;
	
	public Telemetry(int ultrasonic, int light, boolean touch, int sound,
			int speedLeft, int speedRight, int angleArm, long receiveTime) {
		super();
		this.ultrasonic = ultrasonic;
		this.light = light;
		this.touch = touch;
		this.sound = sound;
		this.speedLeft = speedLeft;
		this.speedRight = speedRight;
		this.angleArm = angleArm;
		this.receiveTime = receiveTime;
	}
	
	public int getUltrasonic() {
		return ultrasonic;
	}
	public void setUltrasonic(int ultrasonic) {
		this.ultrasonic = ultrasonic;
	}
	public int getLight() {
		return light;
	}
	public void setLight(int light) {
		this.light = light;
	}
	public boolean isTouch() {
		return touch;
	}
	public void setTouch(boolean touch) {
		this.touch = touch;
	}
	public int getSound() {
		return sound;
	}
	public void setSound(int sound) {
		this.sound = sound;
	}
	public int getSpeedLeft() {
		return speedLeft;
	}
	public void setSpeedLeft(int speedLeft) {
		this.speedLeft = speedLeft;
	}
	public int getSpeedRight() {
		return speedRight;
	}
	public void setSpeedRight(int speedRight) {
		this.speedRight = speedRight;
	}
	public int getAngleArm() {
		return angleArm;
	}
	public void setAngleArm(int angleArm) {
		this.angleArm = angleArm;
	}
	public long getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}
	
}
