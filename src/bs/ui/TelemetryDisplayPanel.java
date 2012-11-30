package bs.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import bs.Log;
import bs.RobotController;
import bs.RobotStateListener;
import bs.Telemetry;

/**
 * A Panel to display a vertical list of up-to-date robot telemetry data
 */
public class TelemetryDisplayPanel extends JPanel {
	private RobotController controller;

	private SensorGraphDisplay leftMotor;
	private SensorGraphDisplay rightMotor;
	private SensorGraphDisplay light;
	private SensorGraphDisplay sound;
	private SensorGraphDisplay touch;
	private SensorGraphDisplay ultrasonic;

	/**
	 * Constructor
	 * 
	 * @param controller
	 *            The robot controller from which to retrieve telemetry data
	 */
	public TelemetryDisplayPanel(RobotController controller) {
		super();
		this.controller = controller;

		leftMotor = new SensorGraphDisplay("Left Motor");
		rightMotor = new SensorGraphDisplay("Right Motor");
		light = new SensorGraphDisplay("Light");
		sound = new SensorGraphDisplay("Sound");
		touch = new SensorGraphDisplay("Touch");
		ultrasonic = new SensorGraphDisplay("Ultrasonic");

		setLayout(new GridLayout(3, 2));
		add(leftMotor);
		add(rightMotor);
		add(light);
		add(sound);
		add(touch);
		add(ultrasonic);

		controller.addRobotStateListener(new RobotStateListener() {
			@Override
			public void stateChanged() {
				updateTelemetry();
			}
		});
	}

	private void updateTelemetry() {
		Telemetry latest = controller.getLatestTelemetry();
		if (latest != null) {
			Log.verbose(this, "Updating telemetry");
			leftMotor.setValue(latest.getSpeedLeft());
			rightMotor.setValue(latest.getSpeedRight());
			light.setValue(latest.getLight());
			sound.setValue(latest.getSound());
			touch.setValue(latest.isTouch()?0:1);
			ultrasonic.setValue(latest.getUltrasonic());
		}
	}
}
