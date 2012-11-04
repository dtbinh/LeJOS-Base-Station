package simulators.robot;

import java.io.IOException;
import java.util.Scanner;

import comm.Message;

public class Connection {
	
	Scanner scanner;
	
	public Connection() {
		scanner = new Scanner(System.in);
	}
	
	public void writeMessage(Message message){
		try {
			message.serialize(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] readMessage(){
		try {
			String line = scanner.nextLine();
			return line.getBytes();
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
}
