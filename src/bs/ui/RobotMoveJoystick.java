package bs.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;

import bs.RobotController;

public class RobotMoveJoystick extends Joystick {
	private static final int MAX_MOVEMENT_SPEED = 900;

	private RobotController controller;
	
	private int distFromCenter;
	
	public RobotMoveJoystick(RobotController controller) {
		super();
		this.controller = controller;
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (isPressedOnSpot()) {

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

			repaint();
			
			setDistanceFromCenter(mxp, myp);
			
			this.createAndSendMoveMessage();
		}
		
	}
	
	@Override
	protected Point getSpotCenter() {

		// Calculate the center point of the spot RELATIVE to the
		// center of the of the circle.

		int r = Math.max((distFromCenter - getSpotRadius()), 0);

		int ycp = (int) (r * Math.sin(getAngleRadians()));
		int xcp = (int) (r * Math.cos(getAngleRadians()));

		// Adjust the center point of the spot so that it is offset
		// from the center of the circle. This is necessary becasue
		// 0,0 is not actually the center of the circle, it is the
		// upper left corner of the component!
		int xc = getRadius() + xcp;//getRadius
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
	
	private void setDistanceFromCenter(int x, int y) {
		double distSquared = (Math.pow(x, 2) + Math.pow(y, 2));
		distFromCenter = (int)Math.sqrt(distSquared);
		if(distFromCenter > getRadius()) {
			distFromCenter = getRadius();
		}
	}
	
	private void createAndSendMoveMessage() {
		double speedProportion = (double)distFromCenter / (double)getRadius();
		double totalSpeed = RobotController.MOTOR_SPEED_MAX_FWD * speedProportion;
		
		double posX = speedProportion * Math.cos(getAngleRadians());
		double posY = speedProportion * Math.sin(getAngleRadians());
		
		posX = posX * -1;
		
		double rPlusL = (MAX_MOVEMENT_SPEED - Math.abs(posX)) * (posY / 100) + posY;
		double rMinusL = (MAX_MOVEMENT_SPEED - Math.abs(posY)) * (posX / 100) + posX;
		
		double rightMotor = (rPlusL + rMinusL) / 2;
		double leftMotor = (rPlusL - rMinusL) / 2;
		rightMotor *= totalSpeed;
		leftMotor *= totalSpeed;
		
		controller.sendMove((int)leftMotor, (int)rightMotor);
		
	}
}
