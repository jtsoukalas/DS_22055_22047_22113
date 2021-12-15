package gr.hua.dit.ds.circularQueue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.NoSuchElementException;


public class Test3 {
    @Test
    public void testArrayQueue() {
        Queue<Integer> q = new CircularQueue<>(64);
        assertTrue(q.isEmpty());
        int count = 60;
        for (int i = 0; i<count; i++) {
            q.push(i);
            assertTrue(q.size() == i+1);
            assertTrue(q.first() == 0);

            System.out.println("First: " + q.first() + "\tSize: " + q.size());
        }

        System.out.println("\n");

        for (int i = 0; i <= 50; i++) {
            try {
                q.pop();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
            assertTrue(q.size() == count-1-i);
            //assertTrue(q.first() == 0);

            System.out.println("First: " + q.first() + "\tSize: " + q.size());
        }
    }
}


