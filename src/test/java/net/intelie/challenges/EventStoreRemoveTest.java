package net.intelie.challenges;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Class with the responsability to also test the method
 * <bold><code>query</code><bold> from the class
 * <code>net.intelie.challenges.EventStore</code>.
 * 
 * @author Vitor Granzinoli Vellozo
 * @version 1.0
 */
@RunWith(ConcurrentTestRunner.class)
public class EventStoreRemoveTest {

	// Number of Threads
	private static final int THREAD_COUNT = 5;

	// Event object
	private Event event;

	// EventStoreImpl object
	private EventStoreImpl eventStore;

	/**
	 * Setup the needs...
	 */
	@Before
	public void setUp() {
		eventStore = new EventStoreImpl();
	}

	/**
	 * Test of insert functionality.
	 */
	@Test
	@ThreadCount(THREAD_COUNT)
	public void removeEvents() {
		String type = UUID.randomUUID().toString();
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
		assertEquals("The list should have 5", 5, eventStore.getEventList().size());
	}
	

}
