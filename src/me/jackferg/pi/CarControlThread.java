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
	public String pos;
	public int i;


	public CarControlThread(BufferedReader c) {
		myClient = c;
		final GpioController gpio = GpioFactory.getInstance();
		backMotorControl = new BackMotorControl(gpio);
		direction = new Direction(gpio);
		pos = "M";
		i = 1;
	}


	public void run() {
		try {
			// this section is where the names from the file are loaded in
			
			while (i==1) {
				i++;
				String PHPInput = myClient.readLine();
								
				switch (PHPInput){
					case "up":
						//backMotorControl.forward(100);
						System.out.println("forward");
						break;
					case "down":
						//backMotorControl.back(100);
						System.out.println("down");
						break;
					case "left":
						System.out.println("left");
						if (pos == "L"){
							break;
						} else if (pos == "M"){
							direction.left(70);
							pos = "L";
						} else {
							direction.left(70);
							pos = "M";
						}
						break;
					case "right":
						System.out.println("right");
						if (pos == "R"){
							break;
						} else if (pos == "M"){
							direction.right(70);
							pos = "R";
						} else {
							direction.right(70);
							pos = "M";
						}
						break;
					case "fail":
						System.out.println("empty data");
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
