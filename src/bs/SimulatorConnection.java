package bs;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * A one-time-use connection ideal for testing purposes.
 */
public class SimulatorConnection extends StreamConnection {
	private InputStream in;
	private OutputStream out;

	public SimulatorConnection(InputStream in, OutputStream out) {
		super();
		this.in = in;
		this.out = out;
	}

	@Override
	public void connect(String name, String address) {
		this.connect(in, out);
	}
}
