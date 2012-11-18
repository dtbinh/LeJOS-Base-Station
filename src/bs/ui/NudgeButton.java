package bs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import bs.Log;
import bs.RobotController;

public class NudgeButton extends JButton{

	private RobotController controller;
	private final int speed = 100;
	private final int stop = 0;
	
	
	public NudgeButton(final RobotController controller) {
		this.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
			
				Log.v(this, "nudge");
				controller.sendMove(speed, speed);
				controller.sendMove(stop, stop);	
			}
		});
	}

}
