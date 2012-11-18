package bs.ui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;
import org.math.plot.plots.ScatterPlot;

/**
 * A JPanel displaying information for a single sensor as a graph
 */
public class SensorGraphDisplay extends JPanel {
	private Plot2DPanel plotPanel;
	private ScatterPlot plot;
	private long dataCount = 0;
	private int dataIndex = 0;
	private double[][] data;
	
	public SensorGraphDisplay(String name) {
		this(name, 256);
	}

	/**
	 * Creates a new SensorDisplay
	 * 
	 * @param name
	 *            The name of the sensor to display
	 */
	public SensorGraphDisplay(String name, int maxData) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		data = new double[maxData][2];
		
		plotPanel = new Plot2DPanel();
		plotPanel.setAutoBounds();
		plot = new ScatterPlot(name, Color.blue, data);
		plotPanel.addPlot(plot);
		plot.setVisible(true);
		add(plotPanel);
	}

	/**
	 * Sets the value of the sensor to display
	 * 
	 * @param value
	 *            The value of the sensor encoded as a double
	 */
	public void setValue(double value) {
		if(dataIndex >= data.length) {
			dataIndex = 0;
		}
		data[dataIndex][0] = dataCount++;
		data[dataIndex][1] = value;
		plot.setData(data);
		dataIndex++;
		
		plotPanel.repaint();
	}
}
