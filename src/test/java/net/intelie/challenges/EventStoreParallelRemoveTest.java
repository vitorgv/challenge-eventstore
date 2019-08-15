package net.intelie.challenges;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

/**
 * Class with the responsability to test the method
 * <bold><code>removeAll</code><bold> from the class
 * <code>net.intelie.challenges.EventStore</code>.
 * 
 * @author Vitor Granzinoli Vellozo
 * @version 1.0
 */
@RunWith(ConcurrentTestRunner.class)
public class EventStoreParallelRemoveTest {

	// Number of Threads
	private static final int THREAD_COUNT = 5;

	// Event object
	private Event event;

	// EventStore object
	private EventStoreImpl eventStore;

	// EventIterator object
	private EventIterator eventIterator;

	// The type
	private String type;

	// The timestamp
	private long timestamp;

	/**
	 * Setup the needs...
	 */
	@Before
	public void setUp() {
		type = UUID.randomUUID().toString();
		timestamp = new Date().getTime();
		event = new Event(type, timestamp);
		eventStore = new EventStoreImpl();
	}

	/**
	 * Test of insert functionality.
	 */
	@Test
	@ThreadCount(THREAD_COUNT)
	public void removeEvents() {
		for (int i = 0; i < 5; i++) {
			event = new Event(type, new Date().getTime());
			eventStore.insert(event);
		}

		for (int i = 0; i < 5; i++) {
			event = new Event(UUID.randomUUID().toString(), new Date().getTime());
			eventStore.insert(event);
		}

		eventStore.removeAll(type);
	}

	/**
	 * The final result test after the test.
	 */
	@After
	public void result() {
		eventIterator = eventStore.query(type, timestamp, new Date().getTime());
		eventIterator.moveNext();
		assertTrue(eventIterator.current() == null);
	}

}