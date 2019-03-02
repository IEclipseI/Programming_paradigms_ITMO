package queue;

public interface Queue {
    //Pre: element != null
    //Post: element added to the multiset
    public void enqueue(Object element);
    //Pre: size of  multiset > 0;
    //Post: RES: the oldest element of the multiset
    public Object element();
    //Pre: true
    //Post: clears the multiset
    public void clear();
    //Pre: size of  multiset > 0;
    //Post: RES: the oldest element, and it deleted from the multiset
    public Object dequeue();
    //Pre: true
    //Post: RES: the size of multiset
    public int size();
    //Pre: true
    //Post:
    public boolean isEmpty();
    //Pre: true
    //Post: RES: returns an Object array[], which includes all elements of multiset and foreach 0=<i<j< size(): array[i] older than array[j]
    public Object[] toArray();
}