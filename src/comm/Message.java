package comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Message {
	private static int computeChecksum(String body) {
		int res = 0;
		for (int i = 0; i < body.length(); i++) {
			res += body.charAt(i);
		}
		return res;
	}

	/**
	 * @param msg
	 *            A string containing message input
	 * @return A message, if one could be successfully read from the given
	 *         string. Null otherwise.
	 */
	public static Message deserialize(String msg) throws Exception {
		List<String> values = new ArrayList<String>();

		Scanner msgScanner = new Scanner(msg);
		msgScanner.useDelimiter("\\{|\\}");
		String inner = msgScanner.next();

		String[] innerSplit = inner.split("\\|");
		int checksum = Integer.parseInt(innerSplit[0]);
		assert (checksum == computeChecksum(innerSplit[1]));

		String[] body = innerSplit[1].split(":");
		String name = body[0];
		int id = Integer.parseInt(body[1]);

		String[] v = new String[0];
		if (body.length >= 3) {
			v = body[2].split(",");
			for (int i = 0; i < v.length; i++) {
				values.add(v[i]);
			}
		}

		return new Message(id, name, values);
	}

	private int id;

	private String name;

	private List<String> values;

	public Message(int id, String name, int numParameters) {
		this.id = id;
		this.name = name;
		this.values = new ArrayList<String>(numParameters);
		for (int i = 0; i < numParameters; i++) {
			this.values.add("");
		}
	}

	private Message(int id, String name, List<String> values) {
		this.id = id;
		this.name = name;
		this.values = values;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	public boolean getBooleanParameter(int index) {
		return Boolean.parseBoolean(values.get(index));
	}

	public float getFloatParameter(int index) {
		return Float.parseFloat(values.get(index));
	}

	public int getId() {
		return id;
	}

	public long getLongParameter(int index) {
		return Long.parseLong(values.get(index));
	}

	public String getName() {
		return name;
	}

	public String getStringParameter(int index) {
		return values.get(index);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
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

	public void setBoolParameter(int index, boolean value) {
		values.set(index, Boolean.toString(value));
	}

	public void setFloatParameter(int index, float value) {
		values.set(index, Float.toString(value));
	}

	public void setLongParameter(int index, long value) {
		values.set(index, Long.toString(value));
	}

	public void setStringParameter(int index, String value) {
		values.set(index, value);
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", name=" + name + ", values=" + values
				+ "]";
	}
}
