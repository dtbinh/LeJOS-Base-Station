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
		// TODO make this method asynchronous so we don't block the entire GUI
		notifyConnecting();
		NXTComm nxtComm;
		try {
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			//NXTInfo[] found = nxtComm.search(null);
			//for(int i = 0; i < found.length; i++) {
				//Log.d(this, "Found device: " + found[i].toString() + " - " + found[i].deviceAddress);
			//}
			
			NXTInfo nxtInfo = new NXTInfo(NXTCommFactory.BLUETOOTH, name,
					address);
			System.out.println(String.format(
					"Device has name: %s, address: %s", nxtInfo.name,
					nxtInfo.deviceAddress));
			Log.v(this, "Attempting to open device...");
			if (nxtComm.open(nxtInfo)) {
				Log.v(this, "Bluetooth connection opened successfully");
			}
		} catch (Exception e) {
			Log.e(this,
					String.format(
							"Error connecting to Bluetooth device with name: %s, address: %s\nError: %s",
							name, address, e.getMessage()));
			notifyConnectionAttemptFailed();
			return;
		}
		InputStream ins = nxtComm.getInputStream();
		OutputStream outs = nxtComm.getOutputStream();
		this.connect(ins, outs);
	}
}