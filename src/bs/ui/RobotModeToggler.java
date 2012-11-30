package bs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import bs.Log;
import bs.RobotController;

public class RobotModeToggler extends JCheckBox {

	private RobotController controller;
	
	public RobotModeToggler(final RobotController controller) {
		this.controller  = controller;
		
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if(isSelected()) {
					controller.sendSetSafeMode(true);
				} else {
					controller.sendSetSafeMode(false);
				}
				
			}
			
		});
	}
	
	
}
