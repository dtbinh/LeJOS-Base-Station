package bs.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bs.Connection;

/**
 * A JPanel enabling users to initiate a connection
 */
public class ConnectPanel extends JPanel {
	private Connection connection;

	private JTextField name;
	private JTextField address;
	private JButton connect;

	public ConnectPanel(Connection connection) {
		super();
		
		this.connection = connection;

		this.name = new JTextField("device name");
		this.address = new JTextField("device address");
		this.connect = new JButton("Connect");
		this.connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConnectPanel.this.connection.connect(name.getText(),
						address.getText());
			}
		});
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.add(name);
		this.add(address);
		this.add(connect);
	}

}
