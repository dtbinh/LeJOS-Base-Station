package bs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class for logging output
 */
public class Log {
	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

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
		writeLog("Verbose", o, msg);
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
		writeLog("Error", o, msg);
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
		writeLog("Debug", o, msg);
	}
}
