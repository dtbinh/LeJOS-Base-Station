package bs.ui;

import javax.swing.BoxLayout;
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

	private SensorDisplay leftMotor;
	private SensorDisplay rightMotor;
	private SensorDisplay light;
	private SensorDisplay sound;
	private SensorDisplay touch;
	private SensorDisplay ultrasonic;

	/**
	 * Constructor
	 * 
	 * @param controller
	 *            The robot controller from which to retrieve telemetry data
	 */
	public TelemetryDisplayPanel(RobotController controller) {
		super();
		this.controller = controller;

		leftMotor = new SensorDisplay("Left Motor");
		rightMotor = new SensorDisplay("Right Motor");
		light = new SensorDisplay("Light");
		sound = new SensorDisplay("Sound");
		touch = new SensorDisplay("Touch");
		ultrasonic = new SensorDisplay("Ultrasonic");

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
			Log.v(this, "Updating telemetry");
			leftMotor.setValue("" + latest.getSpeedLeft());
			rightMotor.setValue("" + latest.getSpeedRight());
			light.setValue("" + latest.getLight());
			sound.setValue("" + latest.getSound());
			touch.setValue("" + latest.isTouch());
			ultrasonic.setValue("" + latest.getUltrasonic());
		}
	}
}
