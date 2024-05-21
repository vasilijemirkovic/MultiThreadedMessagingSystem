package basicTCPConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * When the run method is executed, it sets up a server socket on port 8000.
 * It prints a message indicating the server has started on port 8000 using TCP connection.
 * 
 * @author Vasilije Mirkovic
 */

public class Server {

	public static void run() {
		try {
			@SuppressWarnings("resource")
			ServerSocket tcpServerSocket = new ServerSocket(8000);

			System.out.println("TCP Server started on port " + 8000);

			while (true) {
				Socket socket = tcpServerSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

				System.out.println("New client connected on TCP Server!");

				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String message = in.readLine();

				System.out.println(message);
				int returnValue = Integer.valueOf(message) + 1;
				out.println(returnValue);
			}

		} catch (IOException e) {
			System.out.println("TCP Server exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		run();
	}
}