import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Message {
	private int id;
	private String name;
	private List<String> values;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getLongParameter(int index) {
		return Long.parseLong(values.get(index));
	}

	public boolean getBooleanParameter(int index) {
		return Boolean.parseBoolean(values.get(index));
	}

	public String getStringParameter(int index) {
		return values.get(index);
	}

	public float getFloatParameter(int index) {
		return Float.parseFloat(values.get(index));
	}

	public void setLongParameter(int index, long value) {
		values.set(index, Long.toString(value));
	}

	public void setBoolParameter(int index, boolean value) {
		values.set(index, Boolean.toString(value));
	}

	public void setStringParameter(int index, String value) {
		values.set(index, value);
	}

	public void setFloatParameter(int index, float value) {
		values.set(index, Float.toString(value));
	}

	/**
	 * Creates a new message
	 * 
	 * @param id
	 *            The id of the message
	 * @param name
	 *            The name of the message
	 * @param numParameters
	 *            The total number of parameters to send
	 */
	public Message(int id, String name, int numParameters) {
		this.id = id;
		this.name = name;
		this.values = new ArrayList<String>(numParameters);
	}

	private Message(int id, String name, List<String> values) {
		this.id = id;
		this.name = name;
		this.values = values;
	}

	public void serialize(OutputStream out) throws IOException {
		StringBuilder v = new StringBuilder();
		for (String s : values) {
			v.append(s);
			v.append(",");
		}
		String body = String.format("%s:%d:%s", name, id, v.toString());
		int checksum = computeChecksum(body);
		String msg = String.format("{%d|%s}", checksum, body);
		for (int i = 0; i < msg.length(); i++) {
			byte b = (byte) msg.charAt(i);
			out.write(b);
		}
	}

	/**
	 * @param msg
	 *            A string containing message input
	 * @return A message, if one could be successfully read from the given
	 *         string. Null otherwise.
	 */
	public static Message deserialize(String msg) {
		List<String> values = new ArrayList<String>();

		Scanner msgScanner = new Scanner(msg);
		msgScanner.useDelimiter("{|}");
		String inner = msgScanner.next();

		String[] innerSplit = inner.split("|");
		int checksum = Integer.parseInt(innerSplit[0]);
		if (checksum != computeChecksum(innerSplit[1])) {
			return null;
		}

		String[] body = innerSplit[1].split(":");
		String name = body[0];
		int id = Integer.parseInt(body[1]);

		String[] v = body[2].split(",");
		for (int i = 0; i < v.length; i++) {
			values.add(v[i]);
		}

		return new Message(id, name, values);
	}

	private static int computeChecksum(String body) {
		int res = 0;
		for (int i = 0; i < body.length(); i++) {
			res += body.charAt(i);
		}
		return res;
	}
}
