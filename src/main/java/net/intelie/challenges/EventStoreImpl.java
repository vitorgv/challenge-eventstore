package net.intelie.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that implements de <code>net.intelie.challenges.EventStore</code>
 * interface.<br>
 * 
 * @author Vitor Granzinoli Vellozo
 * @version 1.0
 */
public class EventStoreImpl implements EventStore {

	/**
	 * The list of events.<br>
	 * 
	 */
	private final List<Event> eventList;

	/**
	 * The constructor.
	 */
	public EventStoreImpl() {
		/*
		 * To simplify, I choose a common ArrayList of Event objects, so it's necessary
		 * to synchronize that.
		 */
		eventList = Collections.synchronizedList(new ArrayList<Event>());
	}

	/**
	 * Synchronized method responsible to return the list of events.
	 * 
	 * @return the list of events, an
	 *         <code>java.util.List<net.intelie.challenges.Event></code> object.
	 */
	public synchronized List<Event> getEventList() {
		return eventList;
	}

	/**
	 * Synchronized method responsible to insert an
	 * <code>net.intelie.challenges.Event</code> object into the list.
	 */
	@Override
	public synchronized void insert(Event event) {
		eventList.add(event);
	}

	/**
	 * Synchronized method responsible to remove all
	 * <code>net.intelie.challenges.Event</code> objects from the list, if has the
	 * same type passed as argument.
	 */
	@Override
	public synchronized void removeAll(String type) {
		eventList.removeIf(event -> event.type().equals(type));
	}

	/**
	 * Synchronized method responsible to execute a query in the list, to filter, to
	 * generate and return a <code>net.intelie.challenges.EventIterator</code> as
	 * response.
	 */
	@Override
	public synchronized EventIterator query(String type, long startTime, long endTime) {
		return new EventIteratorImpl(
				Collections.synchronizedList(eventList.stream().filter(eventList -> eventList.type().equals(type))
						.filter(eventList -> eventList.timestamp() >= startTime)
						.filter(eventList -> eventList.timestamp() < endTime).collect(Collectors.toList())));
	}

}
