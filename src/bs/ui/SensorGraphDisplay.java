package bs.ui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.ui.InteractivePanel;

/**
 * A JPanel displaying information for a single sensor as a graph
 */
public class SensorGraphDisplay extends JPanel {
	private long dataCount = 0;

	private DataTable dataTable;
	private XYPlot plot;
	private int maxData;

	public SensorGraphDisplay(String name) {
		this(name, 100);
	}

	/**
	 * Creates a new SensorDisplay
	 * 
	 * @param name
	 *            The name of the sensor to display
	 */
	public SensorGraphDisplay(String name, int maxData) {
		super();
		this.maxData = maxData;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel(name));

		dataTable = new DataTable(Long.class, Double.class);
		dataTable.add(0L, 0.0);
		plot = new XYPlot(dataTable);

		add(new InteractivePanel(plot));
	}

	/**
	 * Sets the value of the sensor to display
	 * 
	 * @param newTelemetryValue
	 *            The value of the sensor encoded as a double
	 */
	public void setValue(double newTelemetryValue) {
		dataCount++;
		dataTable.add(dataCount, newTelemetryValue);
		int indexToRemove = (int) (dataCount - maxData);
		if (indexToRemove > 0) {
			dataTable.remove(indexToRemove);
		}
		plot.dataUpdated(dataTable);
		repaint();
	}
}
