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
	public GpioPinDigitalOutput Motor1A;
	public GpioPinDigitalOutput Motor1B;
	public GpioPinDigitalOutput Motor1E;
	
	public Direction(GpioController gpio){
		Motor1A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "A", PinState.LOW);
		Motor1B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "B", PinState.LOW);
		Motor1E = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "E", PinState.LOW);		
	}
	
	public void left(float time){
		Motor1A.high();
		Motor1B.low();
		Motor1E.high();
		try {
			Thread.sleep((long) time);
		} catch (InterruptedException e) {
			System.out.println("moving forward, back motor error");
		}
		Motor1A.low();
		Motor1E.low();
	}
	
	public void right(float time){
		Motor1A.low();
		Motor1B.high();
		Motor1E.high();
		try {
			Thread.sleep((long) time);
		} catch (InterruptedException e) {
			System.out.println("moving forward, back motor error");
		}
		Motor1B.low();
		Motor1E.low();
	}
}
