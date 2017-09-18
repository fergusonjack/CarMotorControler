package me.jackferg.pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/*
http://docs-europe.electrocomponents.com/webdocs/135f/0900766b8135fae0.pdf
GPIO used
GPIOpins , wiringpi 
23 		 , 4
24		 , 5
10		 , 12
9		 , 13
11		 , 14
25		 , 6
l293D chip
*/

public class Direction {
	public GpioPinDigitalOutput Motor2A;
	public GpioPinDigitalOutput Motor2B;
	public GpioPinDigitalOutput Motor2E;
	
	public Direction(GpioController gpio){
		Motor2A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "A", PinState.LOW);
		Motor2B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "B", PinState.LOW);
		Motor2E = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "E", PinState.LOW);		
	}
	
	public void left(float time){
		Motor2A.high();
		Motor2B.low();
		Motor2E.high();
		try {
			Thread.sleep((long) time);
		} catch (InterruptedException e) {
			System.out.println("moving left, front motor error");
		}
		Motor2A.low();
		Motor2E.low();
	}
	
	public void right(float time){
		Motor2A.low();
		Motor2B.high();
		Motor2E.high();
		try {
			Thread.sleep((long) time);
		} catch (InterruptedException e) {
			System.out.println("moving right, front motor error");
		}
		Motor2B.low();
		Motor2E.low();
	}
}
