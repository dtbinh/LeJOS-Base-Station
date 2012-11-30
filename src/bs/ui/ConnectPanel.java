package bs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bs.Connection;

/**
 * A JPanel enabling users to initiate a connection
 */
public class ConnectPanel extends JPanel {
	private Connection connection;

	private JTextField deviceName;
	private JTextField deviceAddress;
	private JButton connectButton;

	/**
	 * Creates a new ConnectPanel to allow connecting with the provided
	 * connection object
	 * 
	 * @param connection
	 *            The connection to control
	 */
	public ConnectPanel(Connection connection) {
		super();

		this.connection = connection;

		this.deviceName = new JTextField("device name");
		this.deviceAddress = new JTextField("device address");
		this.connectButton = new JButton("Connect");
		this.connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConnectPanel.this.connection.connect(deviceName.getText(),
						deviceAddress.getText());
			}
		});
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.add(deviceName);
		this.add(deviceAddress);
		this.add(connectButton);
	}

}
