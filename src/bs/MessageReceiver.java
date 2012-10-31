package bs;

import comm.Message;

/** Implementors of this interface can receive and process messages from a Connection */
public interface MessageReceiver {
	
	/**
	 * Process a message received and act accordingly
	 * @param message The message that has been received
	 */
	public void receiveMessage(Message message);
}
