package net.intelie.challenges;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventStoreQueryTest {

	// Number of Threads
	private static final int THREAD_COUNT = 5;

	// Event object
	private Event event;

	// EventStoreImpl object
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
		eventStore = new EventStoreImpl();

		event = new Event(UUID.randomUUID().toString(), new Date().getTime());
		type = event.type();
		timestamp = event.timestamp();

		eventStore.insert(event);
	}

	/**
	 * Test of insert functionality.
	 */
	@Test
	@ThreadCount(THREAD_COUNT)
	public void ATestQueryWithKnownEvent() {
		event = new Event(UUID.randomUUID().toString(), new Date().getTime());
		eventStore.insert(event);
	}

	/**
	 * The final result test after the test.
	 */
	@After
	public void result() {
		eventIterator = eventStore.query(type, timestamp, (timestamp + 1L));
		eventIterator.moveNext();
		assertEquals("Event Type should be " + type, type, eventIterator.current().type());
	}

}
