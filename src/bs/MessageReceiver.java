package bs;

import comm.Message;

public interface MessageReceiver {
	
	/**
	 * Process a message received and act accordingly
	 * @param message The message that has been received
	 */
	public void receiveMessage(Message message);
}
