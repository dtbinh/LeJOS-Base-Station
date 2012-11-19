package bs.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import bs.RobotController;
public class RobotModePanel extends JPanel{

	private RobotController controller;

	/**
	 * Constructor
	 * 
	 * Creates a new RobotModePanel
	 */
	public RobotModePanel(RobotController controller) {
		this.controller = controller;
		add(new RobotModeToggler(controller));
	}
}



