package queue;

public class ArrayQueueADTTest1 {
    public static void main(String[] args) {
        ArrayQueueADT queueADT = new ArrayQueueADT();
        for (int i = 0; i < 100; i++) {
            ArrayQueueADT.enqueue(queueADT, i * 2);
            if(i%2==0) {
                ArrayQueueADT.dequeue(queueADT);
            }
        }
        while (!ArrayQueueADT.isEmpty(queueADT)) {
            System.out.println(ArrayQueueADT.dequeue(queueADT));
        }

    }
}
