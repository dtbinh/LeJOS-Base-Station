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
	}

	@Override
	public void connect(String name, String address) {
		notifyConnecting();
		NXTComm nxtComm;
		try {
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);

			NXTInfo nxtInfo = new NXTInfo(NXTCommFactory.BLUETOOTH, name, address);
			System.out.println(String.format("Device has name: %s, address: %s", nxtInfo.name, nxtInfo.deviceAddress));
			Log.verbose(this, "Attempting to open device...");
			if (nxtComm.open(nxtInfo)) {
				Log.verbose(this, "Bluetooth connection opened successfully");
			}
		} catch (Exception e) {
			Log.error(
					this,
					String.format(
							"Error connecting to Bluetooth device with name: %s, address: %s\nError: %s",
							name, address, e.getMessage()));
			notifyConnectionAttemptFailed();
			return;
		}
		InputStream inputStream = nxtComm.getInputStream();
		OutputStream outputStream = nxtComm.getOutputStream();
		this.connect(inputStream, outputStream);
	}

	public void searchConnections(NXTComm nxtComm) {
		try {
			NXTInfo[] found = nxtComm.search(null);
			for (int i = 0; i < found.length; i++) {
				Log.debug(this, "Found device: " + found[i].toString() + " - "
						+ found[i].deviceAddress);
			}
		} catch (Exception e) {
			Log.error(this, "Error searching for devices: " + e.getMessage());
		}
	}

}