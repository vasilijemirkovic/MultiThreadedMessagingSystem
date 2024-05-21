package connectionWithDataObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server starts on the port 8000 waiting for the incoming connections and
 * receivers a Message object, processes it, and informing about the message.
 * 
 * @author Vasilije Mirkovic
 *
 */
public class Server {

	public static void run() throws ClassNotFoundException {
		try {
			@SuppressWarnings("resource")
			ServerSocket tcpServerSocket = new ServerSocket(8000);

			System.out.println("TCP Server started on port " + 8000);

			while (true) {
				Socket socket = tcpServerSocket.accept();

				System.out.println("New client connected on TCP Server!");

				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				Message message = (Message) objectInputStream.readObject();
				System.out.println("RECEIVED MESSAGE:");
				System.out.println(message.toString());

				OutputStream out = socket.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
				objectOutputStream.writeObject(new Message(message.getNumber() + 1, "server"));

			}

		} catch (IOException e) {
			System.out.println("TCP Server exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		run();
	}
}