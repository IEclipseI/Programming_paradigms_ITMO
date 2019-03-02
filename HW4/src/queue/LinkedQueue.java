package queue;

public class LinkedQueue extends AbstractQueue{
    private Node head;
    private Node tail;

    public void enqueueImpl(Object element) {
        assert element != null;
        Node tmp = tail;
        tail = new Node(element, null);
        if (size == 0) {
            head = tail;
        } else {
            tmp.next = tail;
        }
    }

    public Object dequeueImpl() {
        assert size > 0;
        Object result = head.value;
        head = head.next;
        return result;
    }

    public Object elementImpl() {
        assert size > 0;

        return head.value;
    }

    public void clearImpl() {
        size = 0;
        head = null;
        tail = null;
    }

    public class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;
            this.value = value;
            this.next = next;
        }
    }


}