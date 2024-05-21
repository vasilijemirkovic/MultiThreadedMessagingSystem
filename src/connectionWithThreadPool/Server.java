package connectionWithThreadPool;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 * Server starts on the port 8000 using TCP and waiting for the incoming
 * connections and receivers a Message object, processes it, and informing about
 * the message. This time, it uses a thread pool with a fixed thread pool size
 * of 4, to handle client connections concurrently.
 * 
 * @author Vasilije Mirkovic
 *
 */

public class Server {

	private static class ClientHandler implements Runnable {

		private Socket socket;

		public ClientHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			System.out.println("New client connected on TCP Server!");

			InputStream inputStream;
			try {
				inputStream = this.socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				Message message = (Message) objectInputStream.readObject();
				System.out.println("RECEIVED MESSAGE:");
				System.out.println(message.toString());
				OutputStream out = socket.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
				objectOutputStream
						.writeObject(new Message(message.getNumber() + 1, "server", message.getArray().length / 1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void run() throws ClassNotFoundException {
		try {
			@SuppressWarnings("resource")
			ServerSocket tcpServerSocket = new ServerSocket(8000);

			System.out.println("TCP Server started on port " + 8000);
			ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
			while (true) {
				Socket socket = tcpServerSocket.accept();
				executor.submit(new ClientHandler(socket));
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