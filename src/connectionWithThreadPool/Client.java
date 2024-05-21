package connectionWithThreadPool;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 * Client connects to the server, sends a message, and measures the time taken
 * for the round-trip communication and prints the average time taken for the
 * communication at the end.
 * 
 * @author Vasilije Mirkovic
 *
 */

public class Client {

	private static int total = 0;

	public static void run() throws UnknownHostException, IOException, ClassNotFoundException {
		int threadCount = 4;
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
		executor.submit(() -> {
			OutputStream out;
			try {
				long sum = 0;
				for (int i = 0; i < 1000 / threadCount; i++) {
					long startMillis = System.currentTimeMillis();
					Socket clientSocket;
					clientSocket = new Socket("localhost", 8000);
					out = clientSocket.getOutputStream();
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
					InputStream in = clientSocket.getInputStream();
					objectOutputStream.writeObject(new Message(10, "client1", 128));
					ObjectInputStream objectInputStream = new ObjectInputStream(in);
					Message message = (Message) objectInputStream.readObject();
					System.out.println(message.toString());
					in.close();
					out.close();
					clientSocket.close();
					sum += System.currentTimeMillis() - startMillis;
				}
				total += sum;

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		executor.shutdown();
		while (!executor.isTerminated()) {

		}
		System.out.println("Average time: " + ((double) total / (double) 1000));
	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		run();
	}
}
