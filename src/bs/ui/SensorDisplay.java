package bs.ui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SensorDisplay extends JPanel {
	private JLabel label;
	private JLabel value;

	public SensorDisplay(String name) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		label = new JLabel(name);
		value = new JLabel("Unknown");
		this.add(label);
		this.add(value);
	}

	public void setValue(String value) {
		this.value.setText(value);
	}
}
