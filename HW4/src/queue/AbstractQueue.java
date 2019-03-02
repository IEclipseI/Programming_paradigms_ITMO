package queue;


public abstract class AbstractQueue implements Queue {
    int size = 0;

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    protected abstract Object elementImpl();

    public void clear() {
        clearImpl();
    }

    protected abstract void clearImpl();

    public Object dequeue() {
        assert size > 0;
        size--;
        return dequeueImpl();
    }

    protected abstract Object dequeueImpl();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        return toArray(size);
    }

    protected Object[] toArray(int size) {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            Object tmp = dequeue();
            array[i] = tmp;
            enqueue(tmp);
        }
        return array;
    }
}
