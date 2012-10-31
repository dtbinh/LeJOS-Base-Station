import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class LatencyTest {
	private static final String name = "TROGDOR";
	private static final String address = "00165313E674";

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
			OutputStream os = nxtComm.getOutputStream();

			DataOutputStream dos = new DataOutputStream(os);
			DataInputStream dis = new DataInputStream(nxtComm.getInputStream());
			for (int i = 0; i < 100; i++) {
				try {
					long time = System.nanoTime();
					dos.writeByte(i);
					dos.flush();
					dis.readByte();
					long finalTime = System.nanoTime();
					System.out
							.println(String
									.format("Round-trip time for a 1-byte bluetooth echo = %d ns",
											finalTime - time));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// ignore
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			dos.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NXTCommException e) {
			e.printStackTrace();
		}
	}

}
