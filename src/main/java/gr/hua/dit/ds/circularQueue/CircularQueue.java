package gr.hua.dit.ds.circularQueue;

import java.util.NoSuchElementException;

/**
 * <h1>Circular Queue</h1>
 * <h3>implements Queue interface developed at HUA.DIT.OS_3rdSem.Lab</h3>
 *
 * @param <E> type of elements inside the queue
 * @since December 2021
 * @version 1.0
 * @author Kostakis Kokkalis (it22047)
 * @author Orestis Kritsotakis (it22055)
 * @author Jason Tsoukalas (it22113)
 */
public class CircularQueue<E> implements Queue<E>, Cloneable {
    private static final int DEFAULT_CAPACITY = 64;

    private E[] array;
    private int capacity;
    private int front, rear;

    /**
     * <h1>Default constructor</h1>
     * Uses: {@link #CircularQueue(int)} with the {@link #DEFAULT_CAPACITY}
     */
    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * <h1>Constructor</h1>
     *
     * @param capacity desired int for initial capacity (must be even)
     * @throws IllegalArgumentException if capacity is not even number
     */
    public CircularQueue(int capacity) throws IllegalArgumentException{
        if (capacity % 2 != 0) {
            throw new IllegalArgumentException("Circular queue size must be even");
        }

        array = (E[]) new Object[capacity];
        front = 0;
        rear = 0;
        this.capacity = capacity;
    }

    /**
     * <h1> Adds an element to the queue</h1>
     * Checks if queue is full (up to next-to-last element) and uses {@link #doubleCapacity()} if true. <br>
     * Updates rear index, points to the next empty queue slot.
     *
     * @param elem the element
     */
    @Override
    public void push(E elem) {
        //Check if there is enough space
        if (size() == capacity - 1) {
            doubleCapacity();
        }

        array[rear] = elem;
        //Updates rear index
        rear = (rear + 1) % capacity;
    }


    /**
     * <h1>Pops the first element of the queue if it exists</h1>
     * Updates front index, points to the next queue slot. <br><br>
     * Checks if: <ol>
     * <li>queue uses the 1/4 of its capacity and</li>
     * <li>capacity/2 is grater than 10</li></ol>
     * in that case calls {@link #halfCapacity()}.<br><br>
     *
     * @return the popped element
     * @throws NoSuchElementException if queue isn't empty (uses {@link #isEmpty()})
     */
    @Override
    public E pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //Saves first element in order to return it
        E temp = first();
        array[front] = null;

        //Updates front index
        front = (front + 1) % capacity;

        //Decreases the queue by half
        if (size() < capacity / 4 && capacity/2>=10) {     //TODO size<capacity/4 or <= ?
            halfCapacity();
        }
        return temp;
    }

    /**
     * <h1> Doubles queue's capacity </h1>
     * Makes new array with double capacity and transfers the elements to it, after ordering them by push series.
     */
    private void doubleCapacity() {
        E[] newArray = (E[]) new Object[capacity * 2];

        //Transfers ald queue elements to the new array starting from first element
        int current = front;
        for (int i = 0; i < size(); i++) {
            newArray[i] = array[current];
            current = (current + 1) % capacity;
        }

        //Connect new array and updates indexes
        rear = size();
        array = newArray;
        front = 0;
        capacity *= 2;
    }

    /**
     * <h1> Subdivides queue's capacity </h1>
     * Makes a new array with half capacity and transfers the elements to it, after ordering them by push series.
     */
    private void halfCapacity() {
        E[] newArray = (E[]) new Object[array.length / 2];

        //Copying the elements from old array and shifting them to the start of new array
        int current = front;
        for (int i = 0; i < size(); i++) {
            newArray[i] = array[current];
            current = (current + 1) % capacity;
        }

        //Connecting newArray and updating the indexes
        rear = size();
        array = newArray;
        front = 0;
        capacity /= 2;
    }

    /**
     * <h1> Returns the first element of the queue</h1>
     * Note: It doesn't delete it
     * @return the first element of the queue
     * @throws NoSuchElementException if queue is empty (uses {@link #isEmpty()})
     */
    @Override
    public E first() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[front];
    }

    /**
     * <h1>Check if queue is empty</h1>
     *
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * <h1>Get the size of the queue</h1>
     *
     * @return the size of the queue
     */
    @Override
    public int size() {
        return (rear - front + capacity) % capacity;
    }

    /**
     * <h1>Clears the queue and updates front index</h1>
     */
    @Override
    public void clear() {
        array = (E[]) new Object[capacity];
        front = rear;
    }

    //Following methods are used at CircularQueueTests
    public int getFront() {
        return front;
    }

    @Override
    public CircularQueue<E> clone() {
        try {
            CircularQueue clone = (CircularQueue) super.clone();
            clone.capacity = capacity;
            clone.front = front;
            clone.rear = rear;
            clone.array = array.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}