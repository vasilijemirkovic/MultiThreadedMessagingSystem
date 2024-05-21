package basicTCPConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * Client sets up PrintWriter and BufferedReader objects to send and receive
 * messages from the server, sends the integer 10 to the server and then waits
 * for the server's response.
 * 
 * After communication, the client closes the input, output, and socket
 * connections.
 * 
 * @author Vasilije Mirkovic
 *
 */

public class Client {

	public static void run() throws UnknownHostException, IOException {
		Socket clientSocket;
		PrintWriter out;
		BufferedReader in;
		clientSocket = new Socket("localhost", 8000);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out.println(10);
		String resp = in.readLine();
		System.out.println(resp);
		in.close();
		out.close();
		clientSocket.close();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		run();
	}
}
