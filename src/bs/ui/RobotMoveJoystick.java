package bs.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicBoolean;

import bs.RobotController;

public class RobotMoveJoystick extends Joystick {
	private static final int MAX_UPDATE_PERIOD_MS = 100;

	private final AtomicBoolean speedChanged = new AtomicBoolean(false);

	private RobotController controller;

	private int distFromCenter;

	public RobotMoveJoystick(RobotController controller) {
		super();
		this.controller = controller;
		this.distFromCenter = 0;
		// HACK to periodically send update messages
		new Thread() {
			public void run() {
				while (true) {
					if (speedChanged.get()) {
						speedChanged.set(false);
						createAndSendMoveMessage();
						try {
							Thread.sleep(MAX_UPDATE_PERIOD_MS);
						} catch (Exception e) {
						}
					}
				}
			}
		}.start();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// if (isPressedOnSpot()) {

		int xCoordinate = e.getX();
		int yCoordinate = e.getY();

		// Compute the x, y position of the mouse RELATIVE
		// to the center of the knob.
		int xPosition = xCoordinate - getRadius();
		int yPosition = getRadius() - yCoordinate;

		// Compute the new angle of the knob from the
		// new x and y position of the mouse.
		// Math.atan2(...) computes the angle at which
		// x,y lies from the positive y axis with cw rotations
		// being positive and ccw being negative.
		setAngle(Math.atan2(yPosition, xPosition));

		setDistanceFromCenter(xPosition, yPosition);

		repaint();

		// this.createAndSendMoveMessage();
		speedChanged.set(true);
		// }
	}



	@Override
	public void mouseReleased(MouseEvent event) {
		super.mouseReleased(event);
		distFromCenter = 0;
		speedChanged.set(true);
		repaint();
	}

	private void createAndSendMoveMessage() {
		double speedProportion = (double) distFromCenter
				/ (double) getMaxRadius();
		double posX = speedProportion * Math.cos(getAngleRadians());
		double posY = speedProportion * Math.sin(getAngleRadians());

		posX = posX * -1;

		double rPlusL = (1.0 - Math.abs(posX))
				* (posY / 1.0) + posY;
		double rMinusL = (1.0 - Math.abs(posY))
				* (posX / 1.0) + posX;

		double rightMotor = (rPlusL + rMinusL) / 2;
		double leftMotor = (rPlusL - rMinusL) / 2;
		
		double totalSpeed = RobotController.MOTOR_SPEED_MAX_FWD
				* speedProportion;

		rightMotor *= totalSpeed;
		leftMotor *= totalSpeed;

		controller.sendMove((int) leftMotor, (int) rightMotor);

	}

	private int getMaxRadius() {
		return getRadius() - getSpotRadius();
	}

	private void setDistanceFromCenter(int x, int y) {
		double distSquared = (x * x + y * y);
		distFromCenter = (int) Math.sqrt(distSquared);
		if (distFromCenter > getMaxRadius()) {
			distFromCenter = getMaxRadius();
		}
	}

	
	@Override
	protected Point getSpotCenter() {

		// Calculate the center point of the spot RELATIVE to the
		// center of the of the circle.

		int radius = Math.max((distFromCenter), 0);

		int centerPointY = (int) (radius * Math.sin(getAngleRadians()));
		int centerPointX = (int) (radius * Math.cos(getAngleRadians()));

		// Adjust the center point of the spot so that it is offset
		// from the center of the circle. This is necessary becasue
		// 0,0 is not actually the center of the circle, it is the
		// upper left corner of the component!
		int centerX = getRadius() + centerPointX;// getRadius
		int centerY = getRadius() - centerPointY;

		// Create a new Point to return since we can't
		// return 2 values!
		return new Point(centerX, centerY);
	}
}
