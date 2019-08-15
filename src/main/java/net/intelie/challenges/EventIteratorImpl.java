package net.intelie.challenges;

import java.util.Iterator;
import java.util.List;

public class EventIteratorImpl implements EventIterator {

	private Event event;
	private final Iterator<Event> iterator;

	public EventIteratorImpl(List<Event> eventList) {
		this.iterator = eventList.iterator();
	}

	@Override
	public synchronized void close() throws Exception {
		// nothing to do...
	}

	@Override
	public synchronized boolean moveNext() {
		boolean result = false;
		
		if (iterator.hasNext()) {
			this.event = iterator.next();
			result = true;
		}
		
		return result;
	}

	@Override
	public synchronized Event current() {
		return this.event;
	}

	@Override
	public synchronized void remove() {
		iterator.remove();
	}

}