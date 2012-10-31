package bs;

import comm.Message;

public abstract class Connection {
	private MessageReceiver messageReceiver;
	private ConnectionStateListener connectionStateListener;
	
	public Connection() {
		
	}
	
	public Connection(MessageReceiver receiver) {
		
	}
	
	public abstract void start();
	
	public abstract void stop();
	
	public abstract void sendMessage(Message m);
	
	public void setMessageReceiver(MessageReceiver receiver) {
		this.messageReceiver = receiver;
	}
	
	public void setMessageStateListener(ConnectionStateListener listener) {
		this.connectionStateListener = listener;
	}
}
