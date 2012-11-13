package bs.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import bs.RobotController;

/**
 * A button enabling control of a robot's motors by toggling the speeds of the
 * left and right motors
 */
public class RobotArmPanel extends JPanel {
	private RobotController controller;

	/**
	 * Constructor
	 * 
	 * Creates a new RobotMovementPanel
	 */
	public RobotArmPanel(RobotController controller) {
		this.controller = controller;
		add(new RobotArmSlider(controller));
	}
}
