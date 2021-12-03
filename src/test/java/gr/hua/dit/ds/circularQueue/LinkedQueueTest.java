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

		int count = 64;

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

	@Test
	public void testLinkedQueueHardcoded() {
		Queue<Integer> q = new CircularQueue<>();

		assertTrue(q.isEmpty());

		int count = 15;
		//1.I push 15 elements
		for(int i=0;i<count;i++){
			q.push(i);
			assertEquals(q.size(), i + 1);
			assertEquals(0, (int) q.first());
			System.out.println("Element " + i + " pushed.");
		}
		//First and Last print
		System.out.println("Elements in the circular queue: "+q.size()+" from 16.");
		System.out.println("First (f) == "+q.first());
		System.out.println("Last+1 (r)== "+count+"\n\n");

		//2.adding last element to check double
		q.push(15);
		assertEquals(q.size(), 16);
		assertEquals(0, (int) q.first());
		System.out.println("Elements in the circular queue: "+q.size()+" from 16.");
		System.out.println("First (f) == "+q.first());
		System.out.println("Last+1 (r)== "+(count+1)+"\n\n");


		//3.poping first emelent
		q.pop();
		assertEquals(q.size(), 15);
		assertEquals(1, (int) q.first());
		System.out.println("Elements in the circular queue: "+q.size()+" from 16.");
		System.out.println("First (f) == "+q.first());
		System.out.println("Last+1 (r)== "+(count+1)+"\n\n");

		//4.adding 32-16+1 elements,what is the r position?
		for(int i=16;i<32;i++){
			q.push(i);
			assertEquals(q.size(), i);
			assertEquals(1, (int) q.first());
			System.out.println("Element " + i + " pushed.");
		}
		assertEquals(q.size(), 31);
		assertEquals(1, (int) q.first());

		//First and Last print
		System.out.println("First (f) == "+q.first());
		System.out.println("Last+1 (r)== "+0);

		//PUSH WORKS OK!!!




		//continue to pop() and halfCapacity()
		//r=0,f=1

		//poping almost the 15 of 31 elements ....16 elements remain
		int x=q.size();
		for(int i=0;i<15;i++){
			q.pop();
			assertEquals(q.size(),x-i-1);
			assertEquals(i+2, (int) q.first());
			System.out.println("Element " + i + " popped.");
		}
		//First and Last print
		System.out.println("Elements in the circular queue: "+q.size()+" from 32."); //must be 16
		System.out.println("First (f) == "+q.first());
		System.out.println("Last+1 (r)== "+0);
		//r=0,f=16


		///////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//r=0,f=16
		//checking popping in boundaries for halfCapacity()
		//which means I pop a number before halfCapaacity called...    1.pop 7 elements
		x=q.size();
		for(int i=15;i<22;i++){
			q.pop();
			System.out.println("Element " + i + " popped.");
			assertEquals(q.size(),x-1);
			assertEquals(1, (int) q.first());
		}
		//First and Last print
		System.out.println("Elements in the circular queue: "+q.size()+" from 32."); //must be 9
		System.out.println("First (f) == "+q.first());
		System.out.println("Last+1 (r)== "+0);
		/*

		//the exact number that the half capacity is called...         2.popping 8 elements
		x=q.size();
		for(int i=0;i<8;i++){
			q.pop();
			System.out.println("Element " + i + " popped.");
			assertEquals(q.size(),x-1);
			assertEquals(1, (int) q.first());
		}
		//First and Last print
		System.out.println("Elements in the circular queue: "+q.size()+" from 32."); //must be 8
		System.out.println("First (f) == "+q.first());
		System.out.println("Last+1 (r)== "+0);

		//a number after halfCapaacity called...                       3.popping 9 elements
		x=q.size();
		for(int i=0;i<9;i++){
			q.pop();
			System.out.println("Element " + i + " popped.");
			assertEquals(q.size(),x-1);
			assertEquals(1, (int) q.first());
		}
		//First and Last print
		System.out.println("Elements in the circular queue: "+q.size()+" from 32."); //must be 7
		System.out.println("First (f) == "+q.first());
		System.out.println("Last+1 (r)== "+0);

	*/
	}

}
