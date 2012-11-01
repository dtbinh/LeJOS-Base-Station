package comm;

import java.io.ByteArrayOutputStream;
import java.io.StringBufferInputStream;

/**
 * A Test for class Message.
 */
public class MessageTest {
	public static void main(String[] args) {
		Message m = new Message(7, "test_message", 4);
		m.setBoolParameter(0, true);
		m.setFloatParameter(1, 9.3382348f);
		m.setLongParameter(2, 239429);
		m.setStringParameter(3, "teststring");
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			m.serialize(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String serialized = new String(os.toByteArray());
		System.out.println("Serialized message: " + serialized);

		try {
			Message m2 = Message.deserialize(new StringBufferInputStream(
					serialized));
			System.out.println("Deserialized message: " + m2.toString());
			boolean a = m2.getBooleanParameter(0);
			float b = m2.getFloatParameter(1);
			long c = m2.getLongParameter(2);
			String d = m2.getStringParameter(3);
			System.out.println("Deserialized message contains: " + a + ", " + b
					+ ", " + c + ", " + d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
