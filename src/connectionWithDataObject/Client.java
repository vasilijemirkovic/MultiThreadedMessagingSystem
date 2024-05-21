package connectionWithDataObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client connects to the server on the port 8000, sends a Message object, and
 * waits for a response from the server.
 * 
 * @author Vasilije Mirkovic
 *
 */

public class Client {

	public static void run() throws UnknownHostException, IOException, ClassNotFoundException {
		Socket clientSocket;
		clientSocket = new Socket("localhost", 8000);
		OutputStream out = clientSocket.getOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
		InputStream in = clientSocket.getInputStream();
		objectOutputStream.writeObject(new Message(10, "client1"));
		ObjectInputStream objectInputStream = new ObjectInputStream(in);
		Message message = (Message) objectInputStream.readObject();
		System.out.println(message.toString());
		in.close();
		out.close();
		clientSocket.close();
	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		run();
	}
}
