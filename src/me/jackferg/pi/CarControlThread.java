package me.jackferg.pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class CarControlThread extends Thread {
	

	private PrintStream toClient;
	private BufferedReader myClient;
	private BackMotorControl backMotorControl;
	private Direction direction;


	public CarControlThread(BufferedReader c) {
		myClient = c;
		final GpioController gpio = GpioFactory.getInstance();
		backMotorControl = new BackMotorControl(gpio);
		direction = new Direction(gpio);
	}


	public void run() {
		try {
			// this section is where the names from the file are loaded in
			
			while (true) {
				String PHPInput = myClient.readLine();
				
				switch (PHPInput){
					case "up":
						backMotorControl.forward(200);
						break;
					case "down":
						backMotorControl.back(200);
						break;
					case "left":
						direction.left(200);
						break;
					case "right":
						direction.right(200);
						break;
				}
			}
		} catch (IOException e) {
			Report.error("Something went wrong with the client "+  " " + e.getMessage());
			// No point in trying to close sockets. Just give up.
			// We end this thread (we don't do System.exit(1)).
		}
	}
}
