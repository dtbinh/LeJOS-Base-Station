package bs;

import java.util.List;

import comm.Message;

public abstract class Connection {
	private MessageReceiver messageReceiver;
	private String name;
	private String address;
	private List<ConnectionStateListener> connectionStateListener;
	
	public Connection() {
		
	}
	
	public Connection(MessageReceiver receiver) {
		
	}
	
	public abstract void start(String name, String address);
	
	public abstract void stop();
	
	public abstract void sendMessage(Message m);
	
	public void setMessageReceiver(MessageReceiver receiver) {
		this.messageReceiver = receiver;
	}
	
	public void addMessageStateListener(ConnectionStateListener listener) {
		this.connectionStateListener.add(listener);
	}
}
