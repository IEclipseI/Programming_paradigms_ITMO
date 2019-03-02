package queue;

public class ArrayQueueADT {
    private int size;
    private Object[] elements = new Object[10];
    private int headNumber = 0;
    //PRE: element!= null && queue!=null
    //POST: size = size' + 1;elements = elements'[] + element;
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue, queue.size + 1);
        queue.elements[(queue.headNumber + queue.size) % queue.elements.length] = element;
        queue.size++;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity <= queue.elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        for (int i = 0; i < queue.size; i++) {
            newElements[i] = queue.elements[(queue.headNumber + i) % queue.elements.length];
        }
        queue.elements = newElements;
        queue.headNumber = 0;
    }
    //PRE: size> 0&& queue!=null
    //POST: RES=head of queue'; size = size' - 1; headNumber = (headNumber' + 1)%elements.length
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object head = queue.elements[queue.headNumber];
        queue.headNumber = (queue.headNumber + 1) % queue.elements.length;
        queue.size--;
        return head;
    }
    //PRE: size>0&& queue!=null
    //POST: RES=head of queue
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[queue.headNumber];
    }
    //PRE: queue!=null
    //POST: RES= size
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }
    //PRE: queue!=null
    //POST: size = 0; foreach i [0, elements.length) elements[i] == null; headNumber == 0
    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[10];
        queue.size = 0;
        queue.headNumber = 0;
    }
    //PRE: queue!=null
    //POST: RES = (size == 0)
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }
    //PRE: queue!=null
    //POST: RES=array presentation of queue, forech i,z
    public static Object[]  toArray(ArrayQueueADT queueADT) {
        Object[] objects = new Object[queueADT.size];
        for (int i = 0; i < queueADT.size; i++) {
            objects[i] = queueADT.elements[(queueADT.headNumber + i)% queueADT.elements.length];
        }
        return objects;
    }
}
