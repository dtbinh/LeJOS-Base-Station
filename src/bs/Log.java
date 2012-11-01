package bs;

/**
 * A class for logging output
 */
public class Log {
	/**
	 * Writes a verbose message to the log
	 * 
	 * @param msg
	 *            The message to log
	 */
	public static synchronized void v(String tag, String msg) {
		System.out.println("Verbose: " + tag + ": " + msg);
	}

	/**
	 * Writes an error message to the log
	 * 
	 * @param msg
	 *            The message to log
	 */
	public static synchronized void e(String tag, String msg) {
		System.out.println("Error: " + tag + ": " + msg);
	}

	/**
	 * Writes a debug message to the log
	 * 
	 * @param msg
	 *            The message to log
	 */
	public static synchronized void d(String tag, String msg) {
		System.out.println("Debug: " + tag + ": " + msg);
	}
}
