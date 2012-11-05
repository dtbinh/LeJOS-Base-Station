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
		final Object lock = new Object();
		final InputStream alwaysBlock = new InputStream() {
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
