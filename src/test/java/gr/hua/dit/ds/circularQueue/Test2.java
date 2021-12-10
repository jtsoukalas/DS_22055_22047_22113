package gr.hua.dit.ds.circularQueue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test2 {

    @Test
    public void Test2() {

        int count = 10;
        int current = 0;
        CircularQueue<Integer> q = new CircularQueue<>(count);

        assertTrue(q.isEmpty());

        // We fill the queue right before double capacity is used
        for (int i = 0; i < count - 1; i++) {
            q.push(current++);
            assertEquals(q.size(), i + 1);
            assertEquals(0, (int) q.first());
        }
        System.out.println("Faze1" + q.toString());
        assertEquals(count, q.getCapacity());


        // We empty the queue up to the point where only 3 elements remain
        for (int i=q.getFront(); i < count - 0.4*count; i++) {
            assertEquals(i, (int) q.pop());
        }
        System.out.println("Faze2" + q.toString());

        // We fill up the circular queue right before double capacity is used (extreme case rear<front)
        for (int i = 0; i < 6; i++) {
            q.push(current++);
        }

        System.out.println("Faze3" + q.toString());
    }


}
