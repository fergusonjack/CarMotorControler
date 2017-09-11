package me.jackferg.pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class CarControlThread extends Thread {
	private BufferedReader server;
	final GpioController gpio = GpioFactory.getInstance();
	private BackMotorControl backMotorControl = new BackMotorControl(gpio);
	private Direction directionMotorControl = new Direction(gpio);

	public CarControlThread(BufferedReader server) {
		this.server = server;
	}

	public void run() {
		try {
			String s = server.readLine();

			switch (s) {
			case "up":
				backMotorControl.forward(200);
				break;
			case "down":
				backMotorControl.back(200);
				break;
			case "left":
				directionMotorControl.left(200);
				break;
			case "right":
				directionMotorControl.right(200);
				break;
			}
		} catch (SocketException e) {
			Report.errorAndGiveUp("Socket has been closed");
		} catch (IOException e) {
			// this seems to be running even when readLine should be returning
			// null after a closed socket
			Report.errorAndGiveUp("Server seems to have died " + e.getMessage());
		}
	}
}
