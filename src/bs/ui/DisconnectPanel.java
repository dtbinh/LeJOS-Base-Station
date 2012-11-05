package bs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bs.Connection;
import bs.RobotController;

/**
 * A JPanel enabling the user to disconnect a RobotController's connection
 */
public class DisconnectPanel extends JPanel {
	private JButton disconnect;
	private Connection connection;

	/**
	 * Constructor
	 * 
	 * Create a new panel with controls to allow disconnecting the given
	 * connection
	 * 
	 * @param connection
	 *            The connection to control
	 */
	public DisconnectPanel(Connection connection) {
		this.connection = connection;
		disconnect = new JButton("Disconnect");
		disconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DisconnectPanel.this.connection.disconnect();
			}
		});
		this.add(disconnect);
	}
}
