package net.intelie.challenges;

/**
 * This is just an event stub, feel free to expand it if needed.
 * 
 * @version 1.0
 */
public class Event {

	/**
	 * type of the event
	 */
	private final String type;

	/**
	 * timestamp of the event
	 */
	private final long timestamp;

	/**
	 * Constructor of the event.<br>
	 * 
	 * @param type      the type of the event
	 * @param timestamp the timestamp of the event
	 */
	public Event(String type, long timestamp) {
		this.type = type;
		this.timestamp = timestamp;
	}

	/**
	 * Return the type of the event
	 * 
	 * @return type of the event
	 */
	public String type() {
		return type;
	}

	/**
	 * Return the timestamp of the event
	 * 
	 * @return timestamp of the event
	 */
	public long timestamp() {
		return timestamp;
	}

}
