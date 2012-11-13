package bs.ui;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bs.Log;
import bs.RobotController;

/**
 * A button for controlling robot movement. On click, the button will send a
 * movement request with prespecified parameters
 */
public class RobotArmSlider extends JSlider {

	/**
	 * Constructor
	 * 
	 * Creates a RobotMovementButton which will stop the robot
	 * 
	 * @param controller
	 *            The RobotController representing the robot to move
	 */
	public RobotArmSlider(final RobotController controller) {
		super(RobotController.ARM_MIN_ANGLE, RobotController.ARM_MAX_ANGLE);
		this.setMajorTickSpacing(60);
		this.setMinorTickSpacing(15);
		this.setPaintTicks(true);
		this.setPaintLabels(true);
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider)arg0.getSource();
			    if (!source.getValueIsAdjusting()) {
			    	Log.v(this, "arm slider moved");
					controller.setArmAngle(source.getValue());
			        }
			    }
			
		});
	}


}
