package net.intelie.challenges;

import java.util.Iterator;
import java.util.List;

/**
 * Class that implements de <code>net.intelie.challenges.EventIterator</code>
 * interface.<br>
 * 
 * @author Vitor Granzinoli Vellozo
 * @version 1.0
 */
public class EventIteratorImpl implements EventIterator {

	/**
	 * The event object.
	 */
	private Event event;

	/**
	 * The iterator object.
	 */
	private final Iterator<Event> iterator;

	/**
	 * The constructor.
	 * 
	 * @param eventList The list of events. A
	 *                  <code>java.util.List<net.intelie.challenges.Event></code>
	 *                  object.
	 */
	public EventIteratorImpl(List<Event> eventList) {
		this.iterator = eventList.iterator();
	}

	/**
	 * Method responsible to close the iteration. <br>
	 * In this implementation has no utility.
	 */
	@Override
	public synchronized void close() throws Exception {
		// nothing to do...
	}

	/**
	 * Method responsible to move the iteration to next element.
	 */
	@Override
	public synchronized boolean moveNext() {
		boolean result = false;

		if (iterator.hasNext()) {
			this.event = iterator.next();
			result = true;
		}

		return result;
	}

	/**
	 * Method responsible to return the current selection.
	 */
	@Override
	public synchronized Event current() {
		return this.event;
	}

	/**
	 * Method responsible to remove the current element.
	 */
	@Override
	public synchronized void remove() {
		iterator.remove();
	}

}