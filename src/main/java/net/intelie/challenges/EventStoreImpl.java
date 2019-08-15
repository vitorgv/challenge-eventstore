package net.intelie.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventStoreImpl implements EventStore {

	private final List<Event> eventList;

	public EventStoreImpl() {
		eventList = Collections.synchronizedList(new ArrayList<Event>());
	}
	
	public synchronized List<Event> getEventList() {
		return eventList;
	}

	@Override
	public synchronized void insert(Event event) {
		eventList.add(event);
	}

	@Override
	public synchronized void removeAll(String type) {
		eventList.removeIf(event -> event.type().equals(type));
	}

	@Override
	public synchronized EventIterator query(String type, long startTime, long endTime) {
		return new EventIteratorImpl(
				Collections.synchronizedList(eventList.stream().filter(eventList -> eventList.type().equals(type))
						.filter(eventList -> eventList.timestamp() >= startTime)
						.filter(eventList -> eventList.timestamp() < endTime).collect(Collectors.toList())));
	}

}
