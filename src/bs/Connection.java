package bs;

import java.util.List;

import comm.Message;

/**
 * Manages a particular type of connection to a remote device.
 */
public abstract class Connection {
	private MessageReceiver messageReceiver;

	// connection parameters
	private String name;
	private String address;

	private List<ConnectionStateListener> connectionStateListener;

	/**
	 * Constructor
	 * 
	 * Creates a new connection
	 */
	public Connection() {

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
	 */
	public abstract void sendMessage(Message m);

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
		this.connectionStateListener.add(listener);
	}
}
