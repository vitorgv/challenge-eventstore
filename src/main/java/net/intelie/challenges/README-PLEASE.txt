
Hi,

Below my explanation about the test.

My focus on this solution was simplicity and clarity  of  code,  where  a  less 
experienced developer could easily understand.

For simplicity's sake, I choosed  work  with  a  Synchronized  Collection  than
Concurrent Collections, which would make the code a bit bigger and more complex
to understand.

Since the object in this test uses memory to be stored, I  didn't  think  about
effectiveness, efficiency and speed. I just thought of the simplicity itself.

I used a List<Event> that was synchronized with  Collections.synchronizedList()
to ensure thread access.

All methods that needed to be used  by  multiple  threads  have  been  prepared
to be synchronous to ensure thread-safe.

I also created a class to implement the EventIterator interface, which I  named
EventIteratorImpl. This implementation could be done within the method  itself,
but it would be long code and not so readable.

Basic code documentation was provided as requested.

The tests were performed with the vmlens framework to make concurrent tests.


I hope you are pleased.

Grateful,
Vitor