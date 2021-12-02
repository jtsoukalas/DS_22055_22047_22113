/**
 * This code is part of the lab exercises for the Data Structures course at Harokopio
 * University of Athens, Dept. of Informatics and Telematics.
 */
package gr.hua.dit.ds.circularQueue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinkedQueueTest {

	@Test
	public void testLinkedQueue() {
		Queue<Integer> q = new CircularQueue<>();
		
		assertTrue(q.isEmpty());

		int count = 10;

		for (int i = 0; i < count; i++) {
			q.push(i);
			assertEquals(q.size(), i + 1);
			assertEquals(0, (int) q.first());
			System.out.println(q.first());
		}
		System.out.println("first:"+q.first());

		int current = 0;
		while (!q.isEmpty()) {
			assertEquals((int) q.first(), current);
			assertEquals((int) q.pop(), current);
			System.out.println(current);
			current++;
			System.out.println("first:"+q.first());
		}
		assertTrue(q.isEmpty());
	}

}
