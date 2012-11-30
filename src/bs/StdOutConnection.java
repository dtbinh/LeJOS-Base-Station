package bs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A connection which simply writes to standard output
 */
public class StdOutConnection extends StreamConnection {

	@Override
	public void connect(String name, String address) {
		/**
		 * Used to synchronize the reading of messages to prevent threading
		 * issues
		 */
		final Object lock = new Object();
		/**
		 * The input stream that this connection will read from to simulate
		 * messages from the robot. Always blocks because it's the test software
		 */
		final InputStream alwaysBlock = new InputStream() {
			/**
			 * causes the stream to block, so the test software can send it's
			 * messages.
			 */
			public int read() throws IOException {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						throw new IOException();
					}
				}
				return 0;
			}
		};
		this.connect(alwaysBlock, System.out);
	}
}
