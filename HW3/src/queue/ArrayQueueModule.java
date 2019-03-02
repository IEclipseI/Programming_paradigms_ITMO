package queue;

import java.util.ArrayDeque;

public class ArrayQueueModule {
    private static int size;
    private static Object[] elements = new Object[10];
    private static int headNumber = 0;

    //PRE: element!= null
    //POST: size = size' + 1;elements = elements'[] + element;
    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity(size + 1);
        elements[(headNumber + size) % elements.length] = element;
        size++;
    }

    private static void ensureCapacity(int capacity) {
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
    public static Object dequeue() {
        assert size > 0;
        Object head = elements[headNumber];
        headNumber = (headNumber + 1) % elements.length;
        size--;
        return head;
    }

    //PRE: size>0
    //POST: RES=head of queue
    public static Object element() {
        assert size > 0;
        return elements[headNumber];
    }

    //PRE: true
    //POST: RES= size
    public static int size() {
        return size;
    }
    //PRE: true
    //POST: size = 0; foreach i [0, elements.length) elements[i] == null; headNumber == 0
    public static void clear() {
        elements = new Object[10];
        size = 0;
        headNumber = 0;
    }
    ArrayDeque
    //PRE: true
    //POST: RES=array presentation of queue, forech i,
    public static Object[] toArray() {
        Object[] objects = new Object[size];
        for (int i = 0; i < size; i++) {
            objects[i] = elements[(headNumber + i) % elements.length];
        }
        return objects;
    }
    //PRE:true
    //POST: RES = (size == 0)
    public static boolean isEmpty() {
        return size == 0;
    }
}
