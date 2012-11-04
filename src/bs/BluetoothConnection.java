package bs;

import java.io.InputStream;
import java.io.OutputStream;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

/**
 * A Connection over Bluetooth
 */
public class BluetoothConnection extends StreamConnection {
	public BluetoothConnection() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void connect(String name, String address) {
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
			InputStream ins = nxtComm.getInputStream();
			OutputStream outs = nxtComm.getOutputStream();
			this.start(ins, outs);
		} catch (Exception e) {
			Log.e(getClass().toString(),
					String.format(
							"Error connecting to Bluetooth device with name: %s, address: %s\nError: %s",
							name, address, e.getMessage()));
		}
	}

	@Override
	public void disconnect() {
		stop();
	}

}