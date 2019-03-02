package queue;

import java.util.ArrayDeque;

public class ArrayQueue extends AbstractQueue {
    private Object[] elements = new Object[10];
    private int headNumber = 0;

    public void enqueueImpl(Object element) {
        ensureCapacity(size + 1);
        elements[(headNumber + size) % elements.length] = element;
    }

    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        Object[] newElements = toArray(2 * capacity);
        elements = newElements;
        headNumber = 0;
    }

    public Object dequeueImpl() {
        Object head = elements[headNumber];
        headNumber = (headNumber + 1) % elements.length;
        return head;
    }

    public Object elementImpl() {
        return elements[headNumber];
    }

    public void clearImpl() {
        elements = new Object[10];
        size = 0;
        headNumber = 0;
    }
}
