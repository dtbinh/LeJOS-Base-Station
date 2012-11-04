package simulators.robot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.annotation.processing.Messager;

import bs.Connection;
import bs.MessageReceiver;

import comm.Message;

public class TestRobot implements MessageReceiver {

	static int ultrasonic, light, sound, speedLeft, speedRight, angleArm,
			msgCount;
	static boolean touch, safeMode;

	HashMap<Integer, Message> messageStore = new HashMap<Integer, Message>();

	Connection connection;

	public TestRobot(Connection connection) {
		this.connection = connection;
		msgCount = 0;
	}

	@Override
	public void receiveMessage(Message message) {
		try {

			Message ack = new Message(msgCount++, "ack", 1);
			ack.setLongParameter(0, message.getId());
			sendMessage(ack);

			switch (message.getName()) {
			case "motor_speed":
				speedLeft = (int) message.getLongParameter(0);
				speedRight = (int) message.getLongParameter(1);
				break;
			case "arm_angle":
				angleArm = (int) message.getLongParameter(0);
				break;
			case "safety":
				safeMode = message.getBooleanParameter(0);
				break;
			case "resend":
				int robotMsgId = (int) message.getLongParameter(0);
				sendMessage(messageStore.get(robotMsgId));
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendMessage(Message message) {
		connection.sendMessage(message);
	}

	public void generateHeartbeat() {
		Message heartBeat = new Message(msgCount++, "heartbeat", 7);
		heartBeat.setLongParameter(0, ultrasonic);
		heartBeat.setLongParameter(1, light);
		heartBeat.setBoolParameter(2, touch);
		heartBeat.setLongParameter(3, sound);
		heartBeat.setLongParameter(4, speedLeft);
		heartBeat.setLongParameter(5, speedRight);
		heartBeat.setLongParameter(6, angleArm);
		sendMessage(heartBeat);
	}

	public byte[] ruinMessage(Message data) {
		String message = String.valueOf(data);
		Random random = new Random();
		byte[] badData = new byte[random.nextInt(message.length())];
		random.nextBytes(badData);

		int start = random.nextInt(message.length() - badData.length);
		int end = start + badData.length;

		String toReplace = message.substring(start, end);
		message.replace(toReplace, String.valueOf(badData));

		return message.getBytes();
	}

	public void generateEncounter() {
		Message toAbort;
		for (Message message : messageStore.values()) {
			if (message.getName().equals("motor_speed")) {
				toAbort = message;

				Message abortNotification = new Message(msgCount++, "abort", 2);
				abortNotification.setStringParameter(0, toAbort.getName());
				abortNotification.setLongParameter(1, 500);

				if (safeMode) {
					try {
						abortNotification.serialize(System.out);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				break;
			}
		}
	}

}
