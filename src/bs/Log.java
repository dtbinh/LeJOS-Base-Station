package bs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class for logging output
 */
public class Log {
	/**Verbose log message type and **/
	private static String VERBOSE = "Verbose";
	/**Error log message type and **/
	private static String ERROR = "Error";
	/**Debug log message type**/
	private static String DEBUG = "Debug";
	
	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	/**
	 * Writes a message to the log.  It is synchronized to prevent one message to begin writing before the previous one ends.
	 * @param type 
	 * @param o
	 * @param msg
	 */
	private static synchronized void writeLog(String type, Object o, String msg) {
		String time = dateFormat.format(new Date());
		System.out.println(String.format("%s %s: %s: %s", time, type, o
				.getClass().getName(), msg));
	}

	/**
	 * Writes a verbose message to the log
	 * 
	 * @param c
	 *            The object writing the log message
	 * @param msg
	 *            The message to log
	 */
	public static synchronized void v(Object o, String msg) {
		writeLog(VERBOSE, o, msg);
	}

	/**
	 * Writes an error message to the log
	 * 
	 * @param o
	 *            The object writing the log message
	 * @param msg
	 *            The message to log
	 */
	public static synchronized void e(Object o, String msg) {
		writeLog(ERROR, o, msg);
	}

	/**
	 * Writes a debug message to the log
	 * 
	 * @param o
	 *            The object writing the log message
	 * @param msg
	 *            The message to log
	 */
	public static synchronized void d(Object o, String msg) {
		writeLog(DEBUG, o, msg);
	}
}
