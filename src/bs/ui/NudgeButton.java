package bs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import bs.Log;
import bs.RobotController;

public class NudgeButton extends JButton {

	private RobotController controller;
	private static final int STOP_SPEED = 0;

	public NudgeButton(final RobotController controller, final int speedL, final int speedR) {
		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Log.verbose(this, "nudge");
				new Thread() {
					public void run() {
						controller.sendMove(speedL, speedR);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						controller.sendMove(STOP_SPEED, STOP_SPEED);
					}
				}.start();
			}
		});
	}

}
