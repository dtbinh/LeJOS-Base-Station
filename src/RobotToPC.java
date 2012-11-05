import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class RobotToPC {
	// private static final String name = "TROGDOR";
	// private static final String address = "00165313E674";
	private static final String name = "LEAD4";
	private static final String address = "00165313E67B";

	public static void main(String[] args) {
		try {
			NXTComm nxtComm = NXTCommFactory
					.createNXTComm(NXTCommFactory.BLUETOOTH);
			NXTInfo nxtInfo = new NXTInfo(NXTCommFactory.BLUETOOTH, name,
					address);
			System.out.println(String.format(
					"Device has name: %s, address: %s", nxtInfo.name,
					nxtInfo.deviceAddress));
			System.out.println("Attempting to open device...");
			if (nxtComm.open(nxtInfo)) {
				System.out.println("Success");
			}
			InputStream is = nxtComm.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			int value = 0;
			System.out.println("Waiting for input from robot");
			while (true) {
				try {
					value = (int) dis.readByte();
				} catch (EOFException e) {
					System.out.println("EOF Reached.");
					break;
				}
				System.out.println(String.format("Read value %d", value));
			}
			/*
			 * OutputStream os = nxtComm.getOutputStream(); DataOutputStream dos
			 * = new DataOutputStream(os); try { dos.write('h'); dos.write('e');
			 * dos.write('l'); dos.write('l'); dos.write('o'); dos.write('\n');
			 * } catch (IOException e1) { // TODO Auto-generated catch block
			 * e1.printStackTrace(); } // close the output stream when done try
			 * { dos.close(); os.close(); } catch (IOException e) {
			 * e.printStackTrace(); }
			 */
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NXTCommException e) {
			e.printStackTrace();
		}
	}
}
