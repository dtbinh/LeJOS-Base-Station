package bs.ui;

import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bs.Log;
import bs.RobotController;

/**
 * A button for controlling robot movement. On click, the button will send a
 * movement request with prespecified parameters
 */
public class RobotArmSlider extends Joystick {

	private RobotController controller;
	/**
	 * Constructor
	 * 
	 * Creates a RobotMovementButton which will stop the robot
	 * 
	 * @param controller
	 *            The RobotController representing the robot to move
	 */
	public RobotArmSlider(final RobotController controller) {
		super(Math.PI);
		this.controller  = controller;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		controller.sendSetArmAngle(this.getAngle());
	}


}
