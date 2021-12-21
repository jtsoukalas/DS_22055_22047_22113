package gr.hua.dit.ds.circularQueue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * <h1>Tests for {@link CircularQueue} class </h1>
 *
 * @version 1.0
 * @since December 2021
 *
 * @author Kostakis Kokkalis (it22047)
 * @author Orestis Kritsotakis (it22055)
 * @author Iasonas Tsoukalas (it22113)
 */
public class CircularQueueTests {

    /**
     * This code is part of the lab exercises for the Data Structures course at Harokopio
     * University of Athens, Dept. of Informatics and Telematics.
     */
    @Test
    public void test1() {
        Queue<Integer> q = new CircularQueue<>();

        assertTrue(q.isEmpty());

        int count = 100000;

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

    /**
     * <h1>Limits and circularity</h1>
     * The goal of this test is to push the structure to the limits.
     * Further than that, we want to test the circular nature of the structure
     * and make sure of the quality on managing the elements.
     */
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

    /**
     * <h1>Multiple elements and clear method test</h1>
     * The goal of this test, is to check the structure with multiple objects and also to run the clear() method.
     */
    @Test
    public void test3() {
        Queue<Character> q = new CircularQueue<>();

        assertTrue(q.isEmpty());

        StringBuilder input = new StringBuilder("Murphy's law is an adage or epigram that is typically stated as: " +
                "Anything that can go wrong will go wrong." +
                "History\n" +
                "The perceived perversity of the universe has long been a subject of comment, and precursors to the modern version of Murphy's law are not hard to find. Recent significant research in this area has been conducted by members of the American Dialect Society.\n" +
                "\n" +
                "Mathematician Augustus De Morgan wrote on June 23, 1866:[1] \"The first experiment already illustrates a truth of the theory, well confirmed by practice, what-ever can happen will happen if we make trials enough.\" In later publications \"whatever can happen will happen\" occasionally is termed \"Murphy's law\", which raises the possibility—if something went wrong—that \"Murphy\" is \"De Morgan\" misremembered (an option, among others, raised by Goranson on the American Dialect Society list).[2]\n" +
                "\n" +
                "Society member Stephen Goranson has found a version of the law, not yet generalized or bearing that name, in a report by Alfred Holt at an 1877 meeting of an engineering society.\n" +
                "\n" +
                "It is found that anything that can go wrong at sea generally does go wrong sooner or later, so it is not to be wondered that owners prefer the safe to the scientific … Sufficient stress can hardly be laid on the advantages of simplicity. The human factor cannot be safely neglected in planning machinery. If attention is to be obtained, the engine must be such that the engineer will be disposed to attend to it.[3]\n" +
                "American Dialect Society member Bill Mullins has found a slightly broader version of the aphorism in reference to stage magic. The British stage magician Nevil Maskelyne wrote in 1908:\n" +
                "\n" +
                "It is an experience common to all men to find that, on any special occasion, such as the production of a magical effect for the first time in public, everything that can go wrong will go wrong. Whether we must attribute this to the malignity of matter or to the total depravity of inanimate things, whether the exciting cause is hurry, worry, or what not, the fact remains.[4]\n" +
                "In 1948, humorist Paul Jennings coined the term resistentialism, a jocular play on resistance and existentialism, to describe \"seemingly spiteful behavior manifested by inanimate objects\",[5] where objects that cause problems (like lost keys or a runaway bouncy ball) are said to exhibit a high degree of malice toward humans.[6][7]\n" +
                "\n" +
                "The contemporary form of Murphy's law goes back as far as 1952, as an epigraph to a mountaineering book by John Sack, who described it as an \"ancient mountaineering adage\":\n" +
                "\n" +
                "Anything that can possibly go wrong, does.[8] [from Wikipedia]");

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
