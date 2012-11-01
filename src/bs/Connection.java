package bs;

import java.util.LinkedList;
import java.util.List;

import comm.Message;

/**
 * Manages a particular type of connection to a remote device.
 */
public abstract class Connection {
	private MessageReceiver messageReceiver;
	private List<ConnectionStateListener> connectionStateListeners;

	protected void notifyMessageReceiver(Message m) {
		if (messageReceiver != null) {
			messageReceiver.receiveMessage(m);
		}
	}

	protected void notifyConnectionEstablished() {
		for (ConnectionStateListener listener : connectionStateListeners) {
			listener.connectionEstablished();
		}
	}

	protected void notifyConnectionLost() {
		for (ConnectionStateListener listener : connectionStateListeners) {
			listener.connectionLost();
		}
	}

	/**
	 * Constructor
	 * 
	 * Creates a new connection
	 */
	public Connection() {
		connectionStateListeners = new LinkedList<ConnectionStateListener>();
	}

	/**
	 * Constructor
	 * 
	 * Creates a new connection using the provided MessageReciever
	 * 
	 * @param receiver
	 *            The MessageReceiver to invoke upon receipt of messages
	 */
	public Connection(MessageReceiver receiver) {
		this();
		setMessageReceiver(receiver);
	}

	/**
	 * Tries to initiate a connection to a device with the given name and
	 * address.
	 * 
	 * Note that this is an asynchronous operation. Use a
	 * ConnectionStateListener to detect when a connection is established.
	 * 
	 * If the system is currently connected or trying to connect when this
	 * method is called, the existing connection or connection attempt will be
	 * terminated.
	 * 
	 * @param name
	 *            The name of the device to connect to
	 * @param address
	 *            The address of the device to connect to
	 */
	public abstract void connect(String name, String address);

	/**
	 * Terminates any existing connection and halts any current attempt to
	 * establish a connection.
	 */
	public abstract void disconnect();

	/**
	 * If connected, sends a message over the connection. Otherwise, does
	 * nothing.
	 * 
	 * @param m
	 *            The message to send
	 * @return True if able to successfully send the message, false otherwise
	 */
	public abstract boolean sendMessage(Message m);

	/**
	 * Sets the message receiver callback
	 * 
	 * @param receiver
	 *            The callback
	 */
	public void setMessageReceiver(MessageReceiver receiver) {
		this.messageReceiver = receiver;
	}

	/**
	 * Adds the given ConnectionStateListener callback
	 * 
	 * @param listener
	 *            The callback to notify of changes in connection state
	 */
	public void addConnectionStateListener(ConnectionStateListener listener) {
		this.connectionStateListeners.add(listener);
	}
}
