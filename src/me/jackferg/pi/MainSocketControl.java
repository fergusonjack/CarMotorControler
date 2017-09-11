package me.jackferg.pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

//this is for client socket to accept data from the php server 

public class MainSocketControl {
	public static void main(String[] args){
		
		// Open sockets:
	    PrintStream toServer = null;
	    BufferedReader fromServer = null;
	    Socket server = null;
	    int port = 4444;
	    String hostname = "localhost";

	    try {
	      server = new Socket(hostname , port); 
	      fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
	    } 
	    catch (UnknownHostException e) {
	      Report.errorAndGiveUp("Unknown host: " + hostname);
	    } 
	    catch (IOException e) {
	      Report.errorAndGiveUp("The server doesn't seem to be running " + e.getMessage());
	    }
		
	    
	}
}
