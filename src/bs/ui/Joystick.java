package bs.ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * JKnob.java - A knob component. The knob can be rotated by dragging a spot on
 * the knob around in a circle. The knob will report its position in radians
 * when asked.
 * 
 * @author Grant William Braught
 * @author Dickinson College
 * @version 12/4/2000
 */

public class Joystick extends JComponent implements MouseListener,
		MouseMotionListener {

	/** The radius of the entire joystick */
	private static final int KNOB_RADIUS = 50;
	/** The radius of the spot that represents the joystick position */
	private static final int SPOT_RADIUS = 10;
	/** The maximum angle in degrees */
	private static final int MAX_ANGLE = 360;

	/** The angle of the joystick in Radians */
	private double angleTheta;
	/** The color of the joystick */
	private Color knobColor;
	/** The color of the spot that represents the joystick position */
	private Color spotColor;
	/** Represents if the mouse has been clicked on the spot or not */
	private boolean pressedOnSpot;

	/**
	 * No-Arg constructor that initializes the position of the knob to 0 radians
	 * (Up).
	 */
	public Joystick() {
		this(0);
	}

	/**
	 * Constructor that initializes the position of the knob to the specified
	 * angle in radians.
	 * 
	 * @param initTheta
	 *            the initial angle of the knob in Radians.
	 */
	public Joystick(double initTheta) {
		this(initTheta, Color.gray, Color.black);
	}

	/**
	 * Constructor that initializes the position of the knob to the specified
	 * position and also allows the colors of the knob and spot to be specified.
	 * 
	 * @param initTheta
	 *            the initial angle of the knob in Radians.
	 * @param initKnobColor
	 *            the color of the knob.
	 * @param initSpotColor
	 *            the color of the spot.
	 */
	public Joystick(double initTheta, Color initKnobColor, Color initSpotColor) {

		angleTheta = initTheta;
		pressedOnSpot = false;
		knobColor = initKnobColor;
		spotColor = initSpotColor;

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	/**
	 * Paint the JKnob on the graphics context given. The knob is a filled
	 * circle with a small filled circle offset within it to show the current
	 * angular position of the knob.
	 * 
	 * @param g
	 *            The graphics context on which to paint the knob.
	 */
	@Override
	public void paint(Graphics g) {

		// Draw the knob.
		g.setColor(knobColor);
		g.fillOval(0, 0, 2 * KNOB_RADIUS, 2 * KNOB_RADIUS);

		// Find the center of the spot.
		Point pt = getSpotCenter();
		int xc = (int) pt.getX();
		int yc = (int) pt.getY();

		// Draw the spot.
		g.setColor(spotColor);
		g.fillOval(xc - SPOT_RADIUS, yc - SPOT_RADIUS, 2 * SPOT_RADIUS,
				2 * SPOT_RADIUS);
	}

	/**
	 * Return the ideal size that the knob would like to be.
	 * 
	 * @return the preferred size of the JKnob.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(2 * KNOB_RADIUS, 2 * KNOB_RADIUS);
	}

	/**
	 * Return the minimum size that the knob would like to be. This is the same
	 * size as the preferred size so the knob will be of a fixed size.
	 * 
	 * @return the minimum size of the JKnob.
	 */
	public Dimension getMinimumSize() {
		return new Dimension(2 * KNOB_RADIUS, 2 * KNOB_RADIUS);
	}
	
	public int getRadius(){
		return KNOB_RADIUS;
	}
	
	public int getSpotRadius(){
		return SPOT_RADIUS;
	}

	/**
	 * Get the current anglular position of the knob.
	 * 
	 * @return the current anglular position of the knob.
	 */
	public int getAngle() {
		int angle = (int)Math.toDegrees(angleTheta);
		int positiveAngle = (angle + MAX_ANGLE) % MAX_ANGLE;
		return positiveAngle;
	}
	
	public double getAngleRadians() {
		return angleTheta;
	}

	protected void setAngle(double angle) {
		angleTheta = angle;
	}
	
	/**
	 * Calculate the x, y coordinates of the center of the spot.
	 * 
	 * @return a Point containing the x,y position of the center of the spot.
	 */
	protected Point getSpotCenter() {

		// Calculate the center point of the spot RELATIVE to the
		// center of the of the circle.
		int r = KNOB_RADIUS - SPOT_RADIUS;

		int xcp = (int) (r * Math.sin(angleTheta));
		int ycp = (int) (r * Math.cos(angleTheta));

		// Adjust the center point of the spot so that it is offset
		// from the center of the circle. This is necessary becasue
		// 0,0 is not actually the center of the circle, it is the
		// upper left corner of the component!
		int xc = KNOB_RADIUS + xcp;
		int yc = KNOB_RADIUS - ycp;

		// Create a new Point to return since we can't
		// return 2 values!
		return new Point(xc, yc);
	}

	/**
	 * Determine if the mouse click was on the spot or not. If it was return
	 * true, otherwise return false.
	 * 
	 * @return true if x,y is on the spot and false if not.
	 */
	public boolean isOnSpot(Point pt) {
		return (pt.distance(getSpotCenter()) < SPOT_RADIUS);
	}

	public boolean isPressedOnSpot() {
		return pressedOnSpot;
	}
	
	// Methods from the MouseListener interface.

	/**
	 * Empty method because nothing happens on a click.
	 * 
	 * @param e
	 *            reference to a MouseEvent object describing the mouse click.
	 */
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Empty method because nothing happens when the mouse enters the Knob.
	 * 
	 * @param e
	 *            reference to a MouseEvent object describing the mouse entry.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Empty method because nothing happens when the mouse exits the knob.
	 * 
	 * @param e
	 *            reference to a MouseEvent object describing the mouse exit.
	 */
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * When the mouse button is pressed, the dragging of the spot will be
	 * enabled if the button was pressed over the spot.
	 * 
	 * @param e
	 *            reference to a MouseEvent object describing the mouse press.
	 */
	public void mousePressed(MouseEvent e) {

		Point mouseLoc = e.getPoint();
		pressedOnSpot = isOnSpot(mouseLoc);
	}

	/**
	 * When the button is released, the dragging of the spot is disabled.
	 * 
	 * @param e
	 *            reference to a MouseEvent object describing the mouse release.
	 */
	public void mouseReleased(MouseEvent e) {
		pressedOnSpot = false;
	}

	// Methods from the MouseMotionListener interface.

	/**
	 * Empty method because nothing happens when the mouse is moved if it is not
	 * being dragged.
	 * 
	 * @param e
	 *            reference to a MouseEvent object describing the mouse move.
	 */
	public void mouseMoved(MouseEvent e) {
	}

	/**
	 * Compute the new angle for the spot and repaint the knob. The new angle is
	 * computed based on the new mouse position.
	 * 
	 * @param e
	 *            reference to a MouseEvent object describing the mouse drag.
	 */
	public void mouseDragged(MouseEvent e) {
		if (pressedOnSpot) {

			int mx = e.getX();
			int my = e.getY();

			// Compute the x, y position of the mouse RELATIVE
			// to the center of the knob.
			int mxp = mx - KNOB_RADIUS;
			int myp = KNOB_RADIUS - my;

			// Compute the new angle of the knob from the
			// new x and y position of the mouse.
			// Math.atan2(...) computes the angle at which
			// x,y lies from the positive y axis with cw rotations
			// being positive and ccw being negative.
			angleTheta = Math.atan2(mxp, myp);

			repaint();
		}
	}
}
