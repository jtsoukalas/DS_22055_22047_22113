package gr.hua.dit.ds.circularQueue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test2 {

    @Test
    public void Test2() {

        int capacity = 100;
        int next_push_element = 0;
        int next_pop_element = 0;

        CircularQueue<Integer> q = new CircularQueue<>(capacity);

        assertTrue(q.isEmpty());

        // We fill the queue right before double capacity is used
        for (int i = 0; i < capacity - 1; i++) {
            q.push(next_push_element++);
            assertEquals(q.size(), i + 1);
            assertEquals(next_pop_element, (int) q.first());
            //assertEquals(0, (int) q.first());
        }
        System.out.println("Faze1" + q);
        //assertEquals(capacity, q.getCapacity());

        // We empty the queue up to the point where only 40% capacity -1 element, elements remain
        for (int i = q.getFront(); i < 0.6 * capacity; i++) {
            assertEquals(next_pop_element++, (int) q.pop());
            assertEquals(next_pop_element, (int) q.first());
        }
        System.out.println("Faze2" + q);

        // We fill up the circular queue right before double capacity is used (extreme case rear<front)
        for (int i = 0; i < 0.6 * capacity; i++) {
            q.push(next_push_element++);
            assertEquals(next_pop_element, (int) q.first());
        }
        System.out.println("Faze3" + q);

        // Push element so q will call doubleCapacity
        q.push(next_push_element++);
        System.out.println("Faze3b" + q);

        //Pop elements and check values
        while (!q.isEmpty()) {
            assertEquals(next_pop_element, (int) q.first());
            assertEquals(next_pop_element++, (int) q.pop());
        }

        System.out.println("Faze4" + q);

        assertTrue(q.isEmpty());
    }


}
