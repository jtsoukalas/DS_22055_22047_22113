package gr.hua.dit.ds.circularQueue;

import java.util.NoSuchElementException;

public class CircularQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 64;

    private E[] array;
    private int f, r;

    public CircularQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Wrong capacity, please provide positive number");
        }
        this.array = (E[]) new Object[capacity];
        f = 0;
        r = 0;

    }

    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Push a new element into the queue
     *
     * @param elem the element
     */
    @Override
    public void push(E elem) {
        if (size() >= array.length - 1) {
            doubleCapacity();
        }
        array[r] = elem;
        r = (r + 1) % array.length;
    }

    private void doubleCapacity() {
        E[] newArray = (E[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    /**
     * Pop an element from the queue
     *
     * @return the first object in the queue
     */

    /*
            1. Keep elem (array[f])
            2. delete array[f]
            2. f update
            4. check for halfCapacity
            5. return elem
    */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Circular queue is empty!\n");
        }

        E result = first();
        array[f] = null;
        f = (f + 1) % array.length;
        if (size() <= array.length / 4) {
            halfCapacity();
        }
        return result;
    }

    /**
     * Downsizes
     */
    private void halfCapacity() {
        //Checking if the amount of elements is less than the half capacity of the array
        if (size() > array.length / 2) {       //TODO talk about it
            return;
        }

        E[] newArray = (E[]) new Object[array.length / 2];

        //Copying the elements from old array and swifting them to the start of new array
        int current = f;
        for (int i = 0; i < size(); i++) {
            newArray[i] = array[current];
            current = (current + 1) % array.length;
        }

        //Connecting newArray and updating the indexes
        array = newArray;
        f = 0;
        r = size();
    }

    /**
     * Return the first element of the queue
     *
     * @return the first element of the queue
     */
    @Override
    public E first() {
        return array[f];
    }

    /**
     * Check if a queue is empty
     *
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return f == r;
    }

    /**
     * Get the size of the queue
     *
     * @return the size of the queue
     */
    @Override
    public int size() {
        return (r - f + array.length) % array.length;
    }

    /**
     * Clear the queue
     */
    @Override
    public void clear() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        r = f;
    }
}