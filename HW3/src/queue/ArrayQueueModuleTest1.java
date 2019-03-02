package queue;

public class ArrayQueueModuleTest1 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            ArrayQueueModule.enqueue(i * 2);
            if (i % 2 == 0) {
                ArrayQueueModule.dequeue();
            }
        }
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.dequeue());
        }
    }
}