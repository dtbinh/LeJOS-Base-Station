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

		int mx = e.getX();
		int my = e.getY();

		// Compute the x, y position of the mouse RELATIVE
		// to the center of the knob.
		int mxp = mx - getRadius();
		int myp = getRadius() - my;

		// Compute the new angle of the knob from the
		// new x and y position of the mouse.
		// Math.atan2(...) computes the angle at which
		// x,y lies from the positive y axis with cw rotations
		// being positive and ccw being negative.
		setAngle(Math.atan2(myp, mxp));

		setDistanceFromCenter(mxp, myp);

		repaint();

		// this.createAndSendMoveMessage();
		speedChanged.set(true);
		// }
	}

	@Override
	protected Point getSpotCenter() {

		// Calculate the center point of the spot RELATIVE to the
		// center of the of the circle.

		int r = Math.max((distFromCenter), 0);

		int ycp = (int) (r * Math.sin(getAngleRadians()));
		int xcp = (int) (r * Math.cos(getAngleRadians()));

		// Adjust the center point of the spot so that it is offset
		// from the center of the circle. This is necessary becasue
		// 0,0 is not actually the center of the circle, it is the
		// upper left corner of the component!
		int xc = getRadius() + xcp;// getRadius
		int yc = getRadius() - ycp;

		// Create a new Point to return since we can't
		// return 2 values!
		return new Point(xc, yc);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		distFromCenter = 0;
		repaint();
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
}
