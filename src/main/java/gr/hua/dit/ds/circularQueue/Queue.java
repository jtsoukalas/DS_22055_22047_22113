
package gr.hua.dit.ds.circularQueue;

/**
 * <h1> A queue </h1>
 *
 *  This code is part of the lab exercises for the Data Structures course at Harokopio
 *  University of Athens, Dept. of Informatics and Telematics.
 * @param <E> the element type
 */
public interface Queue<E> {

	/**
	 * Push a new element into the queue
	 * 
	 * @param elem the element
	 */
	void push(E elem);
	
	/**
	 * Pop an element from the queue
	 */
	E pop();
	
	/**
	 * Return the first element of the queue
	 *  
	 * @return the first element of the queue
	 */
	E first();
	
	/**
	 * Check if a queue is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * Get the size of the queue
	 * 
	 * @return the size of the queue
	 */
	int size();
	
	/**
	 * Clear the queue
	 */
	void clear();
	
}
