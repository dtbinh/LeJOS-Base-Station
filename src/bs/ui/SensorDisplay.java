package bs.ui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A JPanel displaying information for a single sensor
 */
public class SensorDisplay extends JPanel {
	private JLabel label;
	private JLabel value;

	/**
	 * Creates a new SensorDisplay
	 * 
	 * @param name
	 *            The name of the sensor to display
	 */
	public SensorDisplay(String name) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		label = new JLabel(name);
		value = new JLabel("Unknown");
		this.add(label);
		this.add(value);
	}

	/**
	 * Sets the value of the sensor to display
	 * 
	 * @param value
	 *            The value of the sensor encoded in a human-readable string
	 */
	public void setValue(String value) {
		this.value.setText(value);
	}
}
