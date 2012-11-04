package bs.ui;

import javax.swing.JPanel;

import bs.RobotController;
import bs.RobotStateListener;
import bs.Telemetry;

public class TelemetryDisplayPanel extends JPanel {
	private RobotController controller;

	public TelemetryDisplayPanel(RobotController controller) {
		super();
		this.controller = controller;
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

		}
	}
}
