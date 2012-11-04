package bs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import comm.Message;

/**
 * 
 */
public abstract class StreamConnection extends Connection {
	private InputStream in;
	private OutputStream out;

	/**
	 * A thread to continuously read and process messages from the input stream
	 */
	private final Thread readerThread = new Thread() {
		public void run() {
			// repeat until interrupted
			while (!Thread.interrupted()) {
				Message msg = null;
				try {
					msg = Message.deserialize(in);
				} catch (IOException e) {
					// Disconnect and terminate if there is an error in reading
					// the next message from the input stream
					disconnect();
					Log.d(this, "Terminating reader thread");
					return;
				}
				// if we've successfully read a message
				if (msg != null) {
					notifyMessageReceiver(msg);
				}
			}
		}
	};

	/**
	 * Starts listening for messages on a separate thread
	 * 
	 * @param in
	 *            The input stream on which to begin listening for messages
	 * @param out
	 *            The output stream to write messages
	 */
	protected void start(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
		readerThread.interrupt();
		readerThread.start();
	}

	/**
	 * Stops the thread listening for messages and closes the IO streams
	 */
	protected void stop() {
		if (readerThread.isAlive()) {
			readerThread.interrupt();
		}
		try {
			if (in != null) {
				in.close();
			}
		} catch (IOException e) {
			Log.e(this, "Error closing input stream: " + e.getMessage());
		}
		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			Log.e(this, "Error closing output stream: " + e.getMessage());
		}
	}

	@Override
	public synchronized boolean sendMessage(Message m) {
		if (out == null) {
			return false;
		}

		try {
			m.serialize(out);
		} catch (IOException e) {
			// if there was an error writing to the output stream, disconnect
			Log.e(this, "Error writing message to stream");
			disconnect();
			return false;
		}
		return true;
	}

}
