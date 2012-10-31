package bs;

/**
 * A callback for listening for changes to a connection status
 */
public interface ConnectionStateListener {

	/**
	 * Called when a connection is successfully established
	 */
	public void connectionEstablished();

	/**
	 * Called when a connection is lost
	 */
	public void connectionLost();
}