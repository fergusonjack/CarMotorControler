package me.jackferg.pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

//this is for client socket to accept data from the php server 

public class MainSocketControl {
	public static void main(String[] args){
		
		ServerSocket serverSocket = null;
		int port = 6729;

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			Report.errorAndGiveUp("Couldn't listen on port " + port);
		}		
	    
		try {
			// We loop for ever, as servers usually do.
			while (true) {
				// Listen to the socket, accepting connections from new clients:
				Socket socket = serverSocket.accept(); 
														

				// This is so that we can use readLine():
				BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// client name is now added to the client table in
				// ServerReceiver

				Report.behaviour("system has connected");

				// We create and start a new thread to read from the client:
				(new CarControlThread(fromClient)).start();

				// server sender now gets opened in the ServerReceiver
			}
		} catch (IOException e) {
			// Lazy approach:
			Report.error("IO error " + e.getMessage());
		}
		
	}
}
