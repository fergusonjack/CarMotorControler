package me.jackferg.pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class WiringTest {
	public static void main(String[] args){
		
		final GpioController gpio = GpioFactory.getInstance();
		
		final GpioPinDigitalOutput Motor2A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "A", PinState.LOW);
		final GpioPinDigitalOutput Motor2B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "B", PinState.LOW);
		final GpioPinDigitalOutput Motor2E = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14, "E", PinState.LOW);
		
		final GpioPinDigitalOutput Motor1A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "A", PinState.LOW);
		final GpioPinDigitalOutput Motor1B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "B", PinState.LOW);
		final GpioPinDigitalOutput Motor1E = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "E", PinState.LOW);		
		
		System.out.println("forwards motor 1");
		Motor1A.high();
		Motor1B.low();
		Motor1E.high();
		
		System.out.println("forwards motor 2");
		Motor2A.high();
		Motor2B.low();
		Motor2E.high();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("12345");
		}
		
		System.out.println("Going backwards 1");
		Motor1A.low();
		Motor1B.high();
		Motor1E.high();
		
		System.out.println("going backwards 2");
		Motor2A.low();
		Motor2B.high();
		Motor2E.high();
		
		Motor1E.low();
		Motor2E.low();
	}
}
