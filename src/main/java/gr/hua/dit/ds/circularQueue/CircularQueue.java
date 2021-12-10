package gr.hua.dit.ds.circularQueue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <h1>Circular Queue</h1>
 * <h3>implements Queue interface developed at HUA.DIT.OS_3rdSem.Lab</h3>
 *
 * @param <E> type of elements inside the queue
 * @author Kostakis Kokkalis (it22047)
 * @author Orestis Kritsotakis (it22055)
 * @author Jason Tsoukalas (it22113)
 */
public class CircularQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 64;

    private E[] array;
    private int capacity;
    private int front, rear;

    /**
     * <h1>Default constructor</h1>
     */
    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * <h1>Constructor</h1>
     *
     * @param capacity desired int for initial capacity (must be even)
     */
    public CircularQueue(int capacity) {
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
     * Updates front index, points to the next queue slot. <br>
     * Checks if queue uses the 1/4 of its capacity, if true calls {@link #halfCapacity()}.
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
        if (size() <= capacity / 4) {
            halfCapacity();
        }
        return temp;
    }

    /**
     * <h1> Doubles queue's capacity </h1>
     * Makes new array with double capacity and transfers the old elements to the new accordingly.
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
     * Makes new array with half capacity and transfers the old elements to the new accordingly.
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
     * <h1>Clears the queue and updates indexes</h1>
     */
    @Override
    public void clear() {
        array = (E[]) new Object[capacity];
        front = rear;
    }

    // Getters for test debugging
    public E[] getArray() {
        return array;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    @Override
    public String toString() {
        return " CircularQueue= " + Arrays.toString(array) +
                "\ncapacity=" + capacity +
                "\nfront=" + front +
                "\nrear=" + rear;
    }
}