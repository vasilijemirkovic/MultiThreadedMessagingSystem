package connectionWithDataObject;

import java.io.Serializable;

/**
 * 
 * Message class represents a data object that implements Serializable for
 * object serialization.
 * 
 * @author Vasilije Mirkovic
 */

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private int number;
	private String sender;
	private long timestamp;

	public Message(int number, String sender) {
		this.setNumber(number);
		this.setSender(sender);
		this.setTimestamp(System.currentTimeMillis());
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

}
