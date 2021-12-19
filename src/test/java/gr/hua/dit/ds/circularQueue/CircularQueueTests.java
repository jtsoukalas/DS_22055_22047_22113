import gr.hua.dit.ds.circularQueue.CircularQueue;
import gr.hua.dit.ds.circularQueue.Queue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CircularQueueTests {

    /**
     * This code is part of the lab exercises for the Data Structures course at Harokopio
     * University of Athens, Dept. of Informatics and Telematics.
     */
    @Test
    public void test1() {
        Queue<Integer> q = new CircularQueue<>();

        assertTrue(q.isEmpty());

        int count = 500000;

        for (int i = 0; i < count; i++) {
            q.push(i);
            assertEquals(q.size(), i + 1);
            assertEquals(0, (int) q.first());
        }

        int current = 0;
        while (!q.isEmpty()) {
            assertEquals((int) q.first(), current);
            assertEquals((int) q.pop(), current);
            current++;
        }
        assertTrue(q.isEmpty());
    }

    @Test
    public void test2() {

        int capacity = 100000;
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

        // We empty the queue up to the point where only 40% capacity -1 element, elements remain
        for (int i = q.getFront(); i < 0.6 * capacity; i++) {
            assertEquals(next_pop_element++, (int) q.pop());
            assertEquals(next_pop_element, (int) q.first());
        }

        // We fill up the circular queue right before double capacity is used (extreme case rear<front)
        for (int i = 0; i < 0.6 * capacity; i++) {
            q.push(next_push_element++);
            assertEquals(next_pop_element, (int) q.first());
        }

        // Push element so q will call doubleCapacity
        q.push(next_push_element++);

        //Pop elements and check values
        while (!q.isEmpty()) {
            assertEquals(next_pop_element, (int) q.first());
            assertEquals(next_pop_element++, (int) q.pop());
        }

        assertTrue(q.isEmpty());
    }

    @Test
    public void test3() {
        Queue<Character> q = new CircularQueue<>();

        assertTrue(q.isEmpty());

        StringBuilder input = new StringBuilder("Murphy's law is an adage or epigram that is typically stated as: Anything that can go wrong will go wrong." +
                "History\n" +
                "The perceived perversity of the universe has long been a subject of comment, and precursors to the modern version of Murphy's law are not hard to find. Recent significant research in this area has been conducted by members of the American Dialect Society.\n" +
                "\n" +
                "Mathematician Augustus De Morgan wrote on June 23, 1866:[1] \"The first experiment already illustrates a truth of the theory, well confirmed by practice, what-ever can happen will happen if we make trials enough.\" In later publications \"whatever can happen will happen\" occasionally is termed \"Murphy's law\", which raises the possibility—if something went wrong—that \"Murphy\" is \"De Morgan\" misremembered (an option, among others, raised by Goranson on the American Dialect Society list).[2]\n" +
                "\n" +
                "Society member Stephen Goranson has found a version of the law, not yet generalized or bearing that name, in a report by Alfred Holt at an 1877 meeting of an engineering society.\n" +
                "\n" +
                "It is found that anything that can go wrong at sea generally does go wrong sooner or later, so it is not to be wondered that owners prefer the safe to the scientific … Sufficient stress can hardly be laid on the advantages of simplicity. The human factor cannot be safely neglected in planning machinery. If attention is to be obtained, the engine must be such that the engineer will be disposed to attend to it.[3]\n" +
                "American Dialect Society member Bill Mullins has found a slightly broader version of the aphorism in reference to stage magic."
        );

        //Fill the queue with every character from the above StringBuilder
        for (int i = 0; i < input.length(); i++) {
            q.push(input.charAt(i));
            assertEquals(q.size(), i + 1);
        }

        //Getting a deep copy of q in order to check clear()
        Queue<Character> q2 = ((CircularQueue<Character>) q).clone();

        //Empty the queue and append each element to a new StringBuilder
        StringBuilder output = new StringBuilder();
        while (!q.isEmpty()) {
            output.append(q.pop());
        }

        //Check if the two StringBuilder are equal
        assertEquals(input.toString(), output.toString());
        assertTrue(q.isEmpty());

        //Check clear() method
        q2.clear();
        assertTrue(q2.isEmpty());
    }
}
