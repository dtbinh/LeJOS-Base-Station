package bs.ui;

import javax.swing.JPanel;

import bs.Connection;
import bs.ConnectionStateListener;

/**
 * A JPanel enabling the user to manage a Connection
 */
public class ConnectionManagerPanel extends JPanel implements
		ConnectionStateListener {
	private ConnectPanel connPanel;
	private ConnectPanel disconnectPanel;

	public ConnectionManagerPanel(Connection connection) {
		connPanel = new ConnectPanel(connection);
		disconnectPanel = new ConnectPanel(connection);
		this.add(connPanel);
		this.add(disconnectPanel);
		connection.addConnectionStateListener(this);
	}

	private void onConnecting() {

	}

	private void onConnected() {

	}

	private void onDisconnected() {

	}

	@Override
	public void connectionEstablished() {

	}

	@Override
	public void connectionLost() {

	}

	@Override
	public void connectionAttemptFailed() {

	}
}
