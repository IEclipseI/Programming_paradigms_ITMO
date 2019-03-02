package queue;

public class ArrayQueueTest1 {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        ArrayQueue queue1 = new ArrayQueue();
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i * 2);
            if (i % 2 == 0) {
                queue1.enqueue(queue.dequeue());
            }
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue() + "   " + queue1.dequeue());
        }

    }
}