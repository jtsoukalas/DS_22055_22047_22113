package gr.hua.dit.ds.circularQueue;

import java.util.NoSuchElementException;

public class CircularQueue<E> implements Queue<E> {
    private static int DEFAULT_CAPACITY = 64;

    private E[] array;
    private int capacity;
    private int front, rear;

    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularQueue(int capacity) {

        array = (E[]) new Object[capacity];
        front = 0;
        rear = 0;
        this.capacity = capacity;
    }

    @Override
    public void push(E elem) {
        if (size() == capacity - 1) {
            doubleCapacity();
        }

        array[rear] = elem;
        rear = (rear + 1) % capacity;
    }

    private void doubleCapacity() {     //Minor Changes
        E[] newArray = (E[]) new Object[capacity * 2];
        int current = front;

        for (int i = 0; i < size(); i++) {
            newArray[i] = array[current];
            current = (current + 1) % capacity;
        }
        array = newArray;
        front = 0;
        rear = size();
        capacity = capacity * 2;    //!
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E temp = first();
        array[front] = null;
        front = (front + 1) % capacity;

        if (size() <= capacity / 4) {
            halfCapacity();
        }
        return temp;
    }

    private void halfCapacity() {
        //Checking if the amount of elements is less than the half capacity of the array
        if (size() > capacity / 2) {       //TODO talk about it
            return;
        }

        E[] newArray = (E[]) new Object[array.length / 2];

        //Copying the elements from old array and swifting them to the start of new array
        int current = front;
        for (int i = 0; i < size(); i++) {
            newArray[i] = array[current];
            current = (current + 1) % capacity;
        }

        //Connecting newArray and updating the indexes
        rear = size();          // δίνουμε το σωστό size οχι αυτό που επηρεαζεται απο το λαθος capacity
        capacity /= 2;
        array = newArray;
        front = 0;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[front];
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public int size() {
        return (rear - front + capacity) % capacity;
    }

    @Override
    public void clear() {
        array = (E[]) new Object[capacity];
        front = rear;
    }
}