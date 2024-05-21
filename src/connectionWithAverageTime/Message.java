package connectionWithAverageTime;

import java.io.Serializable;

/**
 * Message class represents a serializable message that will be transfered to
 * the server.
 * 
 * @author Vasilije Mirkovic
 * 
 */

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private int number;
	private String sender;
	private long timestamp;
	private byte[] array;

	public Message(int number, String sender, int kbSize) {
		this.setNumber(number);
		this.setSender(sender);
		this.setTimestamp(System.currentTimeMillis());
		this.setArray(new byte[kbSize * 1000]);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Message [number=" + number + ", sender=" + sender + ", timestamp=" + timestamp + "]";
	}

	public byte[] getArray() {
		return array;
	}

	public void setArray(byte[] array) {
		this.array = array;
	}

}
