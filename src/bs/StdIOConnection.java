package bs;

/**
 * A connection which writes to standard output and reads from standard input
 */
public class StdIOConnection extends StreamConnection {

	public StdIOConnection() {
		super();
	}

	@Override
	public void connect(String name, String address) {
		this.connect(System.in, System.out);
	}
}
