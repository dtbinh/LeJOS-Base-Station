package bs.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import bs.RobotController;

public class NudgePanel extends JPanel {

	private RobotController controller;

	public NudgePanel(RobotController robotController) {
		this.controller = robotController;
		setLayout(new GridLayout(3, 2));
		add(new JLabel());
		add(new NudgeButton(controller, RobotController.MOTOR_SPEED_MAX_FWD,
				RobotController.MOTOR_SPEED_MAX_FWD));
		add(new JLabel());
		add(new NudgeButton(controller, RobotController.MOTOR_SPEED_MAX_REV,
				RobotController.MOTOR_SPEED_MAX_FWD));
		add(new NudgeButton(controller, RobotController.MOTOR_SPEED_MAX_REV,
				RobotController.MOTOR_SPEED_MAX_REV));
		add(new NudgeButton(controller, RobotController.MOTOR_SPEED_MAX_FWD,
				RobotController.MOTOR_SPEED_MAX_REV));
	}

}
