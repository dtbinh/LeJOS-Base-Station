package bs.ui;

import javax.swing.JPanel;

import bs.RobotController;

public class NudgePanel extends JPanel {

	private RobotController controller;
	public NudgePanel(RobotController robotController) {
		this.controller = robotController;
		add(new NudgeButton(controller));
	}


}
