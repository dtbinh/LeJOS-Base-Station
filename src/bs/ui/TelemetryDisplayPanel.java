package bs.ui;

import javax.swing.JPanel;

import bs.RobotController;
import bs.RobotStateListener;
import bs.Telemetry;

public class TelemetryDisplayPanel extends JPanel {
	private RobotController controller;

	private SensorDisplay leftMotor;
	private SensorDisplay rightMotor;
	private SensorDisplay light;
	private SensorDisplay sound;
	private SensorDisplay touch;
	private SensorDisplay ultrasonic;

	public TelemetryDisplayPanel(RobotController controller) {
		super();
		this.controller = controller;

		leftMotor = new SensorDisplay("Left Motor");
		rightMotor = new SensorDisplay("Right Motor");
		light = new SensorDisplay("Light");
		sound = new SensorDisplay("Sound");
		touch = new SensorDisplay("Touch");
		ultrasonic = new SensorDisplay("Ultrasonic");

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
			leftMotor.setValue("" + latest.getSpeedLeft());
			rightMotor.setValue("" + latest.getSpeedRight());
			light.setValue("" + latest.getLight());
			touch.setValue("" + latest.isTouch());
			ultrasonic.setValue("" + latest.getUltrasonic());
		}
	}
}
