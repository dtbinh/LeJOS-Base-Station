package bs.ui;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import bs.RobotController;

public class RobotMovementKeyDispatcher implements KeyEventDispatcher {

	private RobotController controller;

	public RobotMovementKeyDispatcher(final RobotController controller) {
		this.controller = controller;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				controller.sendMove(RobotController.MOTOR_SPEED_MAX_FWD,
						RobotController.MOTOR_SPEED_MAX_FWD);
				break;
			case KeyEvent.VK_DOWN:
				controller.sendMove(RobotController.MOTOR_SPEED_MAX_REV,
						RobotController.MOTOR_SPEED_MAX_REV);
				break;
			case KeyEvent.VK_LEFT:
				controller.sendMove(RobotController.MOTOR_SPEED_MAX_REV,
						RobotController.MOTOR_SPEED_MAX_FWD);
				break;
			case KeyEvent.VK_RIGHT:
				controller.sendMove(RobotController.MOTOR_SPEED_MAX_FWD,
						RobotController.MOTOR_SPEED_MAX_REV);
				break;
			}
		} else if (e.getID() == KeyEvent.KEY_RELEASED) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_RIGHT:
				controller.sendMove(RobotController.MOTOR_SPEED_STOP,
						RobotController.MOTOR_SPEED_STOP);
				break;
			}
		} else if (e.getID() == KeyEvent.KEY_TYPED) {
		}
		return false;
	}

}
