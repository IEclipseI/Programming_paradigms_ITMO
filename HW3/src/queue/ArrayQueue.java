package queue;

import java.util.Deque;
import java.util.Queue;

public class ArrayQueue {
    private int size;
    private Object[] elements = new Object[10];
    private int headNumber = 0;

    //PRE: element!= null
    //POST: size = size' + 1;elements = elements'[] + element;
    public void enqueue(Object element) {
        ensureCapacity(size + 1);
        elements[(headNumber + size) % elements.length] = element;
        size++;
    }

    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(headNumber + i) % elements.length];
        }
        elements = newElements;
        headNumber = 0;
    }

    //PRE: size> 0
    //POST: RES=head of queue'; size = size' - 1; headNumber = (headNumber' + 1)%elements.length
    public Object dequeue() {
        Object head = elements[headNumber];
        headNumber = (headNumber + 1) % elements.length;
        size--;
        return head;
    }

    //PRE: size>0
    //POST: RES=head of queue
    public Object element() {
        return elements[headNumber];
    }

    //PRE: true
    //POST: RES= size
    public int size() {
        return size;
    }

    //PRE: true
    //POST: size = 0; foreach i [0, elements.length) elements[i] == null; headNumber == 0
    public void clear() {
        elements = new Object[10];
        size = 0;
        headNumber = 0;
    }
    //PRE: true
    //POST: RES=array presentation of queue, forech i,
    public Object[]  toArray() {
        Object[] objects = new Object[size];
        for (int i = 0; i < size; i++) {
            objects[i] = elements[(headNumber + i)% elements.length];
        }
        return objects;
    }
    //PRE:true
    //POST: RES = (size == 0)
    public boolean isEmpty() {
        return size == 0;
    }
}
